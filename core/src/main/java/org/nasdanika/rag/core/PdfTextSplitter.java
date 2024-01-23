package org.nasdanika.rag.core;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.models.pdf.Document;
import org.nasdanika.models.pdf.Paragraph;

/**
 * Extracts text from PDF and splits into chunks.
 * This class tries to keep paragraphs together and split them into sentences if keeping together is not possible.
 */
public class PdfTextSplitter {
	
	public interface Chunk {
		
		String getText();
		
		List<EObject> getSources();
		
		int size();
		
		int overlap();
		
	}
	
	private int size;
	private int overlap;
	private int tolerance;
	private Function<String, List<String>> tokenizer;

	/**
	 * 
	 * @param size Chunk size in tokens
	 * @param overlap Chunk overlap in tokens.
	 * @param tolerance Size tolerance to allow keep paragraphs and sentences together if possible
	 * @param tokenCounter
	 */
	public PdfTextSplitter(
			int size, 
			int overlap,
			int tolerance,
			Function<String, List<String>> tokenizer) {
		this.size = size;
		this.overlap = overlap;
		this.tolerance = tolerance;
		this.tokenizer = tokenizer;
	}
	
	protected List<String> splitIntoSentences(String text) {
		BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);
		iterator.setText(text);
		int start = iterator.first();
		List<String> ret = new ArrayList<>();
		for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator.next()) {
			ret.add(text.substring(start,end));
		}				
		return ret;
	}
	
	protected List<String> splitIntoWords(String text) {
		String[] words = text.split("\\s+");
		return Arrays.asList(words);
	}
	
	protected String getWordSeparator() {
		return " ";	
	}
	
	protected String getLineSeparator() {
		return System.lineSeparator();
	}
	
	protected String getParagraphSeparator() {
		return getLineSeparator() + getLineSeparator();
	}
	
	private static record WordRecord(int id, String text, List<String> tokens, Paragraph paragraph) {} 
	private static record SentenceRecord(int id, String text, int size, Paragraph paragraph, List<WordRecord> words) {}
	private static record ParagraphRecord(int id, String text, int size, Paragraph paragraph, List<SentenceRecord> sentences) {}	
	
	private class ChunkImpl implements Chunk {
		
		/**
		 * Creates a new chunk and adds overlap
		 * @param paragraphs A list of paragraphs
		 * @param paragraph current paragraph index
		 * @param sentence current sentence index
		 */
		ChunkImpl(
				List<ParagraphRecord> paragraphs, 
				int paragraph, 
				int sentence, 
				int word,
				int token) {
			
			int remaining = overlap;
			List<ChunkImpl> chunks = new ArrayList<>();
			P: for (; paragraph >= 0; --paragraph) {
				ParagraphRecord p = paragraphs.get(paragraph);
				if (sentence == -1) {
					// Entire paragraph was added
					if (p.size() < remaining) {
						ChunkImpl pChunk = new ChunkImpl(null, -1, -1, -1, -1);
						pChunk.add(p);
						pChunk.add(getParagraphSeparator(), null);
						chunks.add(pChunk);
						remaining -= pChunk.size();
						if (remaining <= tolerance) {
							break;
						}
						continue;
					}
					
					// Doesn't fit, setting the sentence index to the last sentence
					sentence = p.sentences().size() - 1;					
				}
				
				for (; sentence >= 0; --sentence) {
					SentenceRecord s = p.sentences().get(sentence);
					if (word == -1) {
						// Entire sentence was added
						if (s.size() < remaining) {
							ChunkImpl sChunk = new ChunkImpl(null, -1, -1, -1, -1);
							sChunk.add(s);
							chunks.add(sChunk);
							remaining -= sChunk.size();
							if (remaining <= tolerance) {
								break P;
							}
							continue;
						}
						
						// Doesn't fit, setting the word index to the last word
						word = s.words().size() - 1;					
					}
					
					for (; word >= 0; --word) {
						WordRecord w = s.words().get(word);
						if (token == -1) {
							// Entire word was added
							if (w.tokens().size() < remaining) {
								ChunkImpl wChunk = new ChunkImpl(null, -1, -1, -1, -1);
								wChunk.add(w);
								wChunk.add(getWordSeparator(), w.paragraph());
								chunks.add(wChunk);
								remaining -= wChunk.size();
								if (remaining <= tolerance) {
									break P;
								}
								continue;
							}
							
							// Doesn't fit, setting the token index to the last token
							token = w.tokens().size() - 1;					
						}
						for (; token >= 0; --token) {
							ChunkImpl tChunk = new ChunkImpl(null, -1, -1, -1, -1);
							tChunk.add(w.tokens().get(token), w.paragraph());
							chunks.add(tChunk);
							remaining -= tChunk.size();
							if (remaining <= tolerance) {
								break P;
							}
						}
					}
				}
				
			}
			
			Collections.reverse(chunks);
			for (ChunkImpl oc: chunks) {
				add(oc);
			}
			chunkOverlap = size;
		}
		
		private StringBuilder textBuilder = new StringBuilder();
		private int size;
		
		public int size() {
			return size;
		}
		
		public String getText() {
			return textBuilder.toString();
		}
		
		void add(String text, int size, EObject source) {
			textBuilder.append(text);
			this.size += size;
			if (this.size > PdfTextSplitter.this.size) {
				throw new IllegalStateException("Chunk size exceeded: " + this.size);
			}
			sources.add(source);
		}
		
		void add(String text, EObject source) {
			add(text, tokenizer.apply(text).size(), source);
		}
		
		void add(ParagraphRecord paragraph) {
			if (!sourceRecords.add(paragraph.id())) {
				throw new IllegalStateException("Duplicate source paragraph: " + paragraph);
			}
			if (size > 0) {
				add(getParagraphSeparator(), paragraph.paragraph());
			}
			add(paragraph.text(), paragraph.size(), paragraph.paragraph());
			add(getParagraphSeparator(), tokenizer.apply(getParagraphSeparator()).size(), paragraph.paragraph());
		}
		
		void add(SentenceRecord sentence) {
			if (!sourceRecords.add(sentence.id())) {
				throw new IllegalStateException("Duplicate source sentence: " + sentence);
			}
			add(sentence.text(), sentence.size(), sentence.paragraph());
		}
		
		void add(WordRecord word) {
			if (!sourceRecords.add(word.id())) {
				throw new IllegalStateException("Duplicate source word: " + word);
			}
			if (size > 0) {
				add(getWordSeparator(), null);
			}
			add(word.text(), word.tokens().size(), word.paragraph());
		}
		
		boolean isFull() {
			return size > PdfTextSplitter.this.size - tolerance;
		}
		
		void add(ChunkImpl chunk) {
			add(chunk.getText(), chunk.size(), null);
			sources.addAll(chunk.getSources());
			for (Integer id: chunk.sourceRecords) {
				if (!sourceRecords.add(id)) {
					throw new IllegalStateException("Duplicate source record in chunk: " + chunk);
				}				
			}
		}
		
		private List<EObject> sources = new ArrayList<>();
		private Set<Integer> sourceRecords = new HashSet<>();

		@Override
		public List<EObject> getSources() {
			return sources.stream().filter(Objects::nonNull).distinct().toList();
		}
		
		private int chunkOverlap;

		@Override
		public int overlap() {
			return chunkOverlap;
		}
		
	}	
	
	/**
	 * Splits docuent into chunks. 
	 * @param document
	 * @return
	 */
	public List<Chunk> split(Document document) {
		int[] counter = { 0 };
		List<ParagraphRecord> paragraphs = document
				.getPages()
				.stream()
				.flatMap(p -> p.getArticles().stream())
				.flatMap(a -> a.getParagraphs().stream())
				.map(p -> {
					String text = p.getText(getLineSeparator(), getWordSeparator());
					return new ParagraphRecord(
							counter[0]++,
							text, 
							tokenizer.apply(text).size(), 
							p,
							splitIntoSentences(text)
								.stream()
								.map(s -> new SentenceRecord(
										counter[0]++,
										s, 
										tokenizer.apply(s).size(),
										p,
										splitIntoWords(s)
											.stream()
											.map(w -> new WordRecord(
													counter[0]++, 
													w, 
													tokenizer.apply(w), 
													p))
											.toList()))
								.toList());
				})
				.toList();		
		
		LinkedList<Chunk> chunks = new LinkedList<>();
		chunks.add(new ChunkImpl(paragraphs, -1, -1, -1, -1));
		for (int i = 0; i < paragraphs.size(); ++i) {
			ChunkImpl chunk = (ChunkImpl) chunks.getLast();
			ParagraphRecord paragraph = paragraphs.get(i);
			if (paragraph.size() + chunk.size() < size) {
				// Paragraph fits into the chunk
				chunk.add(paragraph);
			} else {
				// The paragraph is too big to fit into the chunk.
				// Close the chunk if its size is within tolerance
				if (chunk.isFull()) {
					chunk = new ChunkImpl(paragraphs, i - 1, -1, -1, -1);
					chunks.add(chunk);
					if (paragraph.size() + chunk.size() < size) {
						// Paragraph fits into the chunk
						chunk.add(paragraph);
						continue;
					}
				}
				
				// There is not enough space in the chunk for the entire paragraph - break down into sentences.
				for (int j = 0; j < paragraph.sentences().size(); ++j) {
					SentenceRecord sentence = paragraph.sentences().get(j);
					if (sentence.size() + chunk.size() < size) {
						chunk.add(sentence);
					} else {
						if (chunk.isFull()) {
							chunk = new ChunkImpl(paragraphs, i, j - 1, -1, -1);
							chunks.add(chunk);
							if (sentence.size() + chunk.size() < size) {
								chunk.add(sentence);
								continue;
							}
						}
						
						int wordSeparatorSize = tokenizer.apply(getWordSeparator()).size();
						for (int k = 0; k < sentence.words().size(); ++k) {
							WordRecord word = sentence.words().get(k);
							if (word.tokens().size() + chunk.size() + wordSeparatorSize < size) {
								chunk.add(word);
								chunk.add(getWordSeparator(), word.paragraph());
							} else {
								if (chunk.isFull()) {
									chunk = new ChunkImpl(paragraphs, i, j, k - 1, -1);
									chunks.add(chunk);
									if (word.tokens().size() + chunk.size() + wordSeparatorSize < size) {
										chunk.add(word);
										chunk.add(getWordSeparator(), word.paragraph());
										continue;
									}
								}
								
								// Breaking the word into tokens and adding individual tokens
								int w = 0;
								for (String token: word.tokens()) {
									chunk.add(token, 1, word.paragraph());									
									if (chunk.isFull()) {
										chunk = new ChunkImpl(paragraphs, i, j, k, w);
										chunks.add(chunk);
										continue;
									}
									++w;
								}
							}
						}
						
					}
				}
			}
		}
				
		return chunks;
	}
	
}
