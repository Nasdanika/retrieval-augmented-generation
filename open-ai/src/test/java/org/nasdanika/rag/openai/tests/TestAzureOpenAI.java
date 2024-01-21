package org.nasdanika.rag.openai.tests;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.commons.codec.binary.Hex;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.models.pdf.Document;
import org.nasdanika.models.pdf.util.PdfTextResourceFactory;
import org.nasdanika.rag.core.ArrayListZipEntryStore;
import org.nasdanika.rag.core.PdfTextSplitter;
import org.nasdanika.rag.core.PdfTextSplitter.Chunk;
import org.nasdanika.rag.core.Store.SearchResult;
import org.nasdanika.rag.core.Store;
import org.nasdanika.rag.core.StringDoubleVectorKeyExtractor;
import org.nasdanika.rag.openai.OpenAIEmbeddingsKeyExtractor;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.ai.openai.models.ChatChoice;
import com.azure.ai.openai.models.ChatCompletions;
import com.azure.ai.openai.models.ChatCompletionsOptions;
import com.azure.ai.openai.models.ChatRequestAssistantMessage;
import com.azure.ai.openai.models.ChatRequestMessage;
import com.azure.ai.openai.models.ChatRequestSystemMessage;
import com.azure.ai.openai.models.ChatRequestUserMessage;
import com.azure.ai.openai.models.ChatResponseMessage;
import com.azure.ai.openai.models.CompletionsUsage;
import com.azure.core.credential.KeyCredential;
import com.google.common.io.Files;
import com.knuddels.jtokkit.Encodings;
import com.knuddels.jtokkit.api.Encoding;
import com.knuddels.jtokkit.api.EncodingRegistry;
import com.knuddels.jtokkit.api.EncodingType;

public class TestAzureOpenAI {
		
	private OpenAIClient buildChatCompletionsClient() {
		String key = System.getenv("OPENAI_API_KEY");
		OpenAIClient client = new OpenAIClientBuilder()
			    .credential(new KeyCredential(key))
			    .endpoint("https://api.openai.com/v1/chat/completions")
			    .buildClient();
		return client;
	}
			
	@Test
	public void testOpenAIChatCompletions() throws Exception {
        String deploymentOrModelId = "gpt-3.5-turbo";

        OpenAIClient client = buildChatCompletionsClient();

        List<ChatRequestMessage> chatMessages = new ArrayList<>();
        chatMessages.add(new ChatRequestSystemMessage("You are a helpful assistant. You will talk like a pirate."));
        chatMessages.add(new ChatRequestUserMessage("Can you help me?"));
        chatMessages.add(new ChatRequestAssistantMessage("Of course, me hearty! What can I do for ye?"));
        chatMessages.add(new ChatRequestUserMessage("What's the best way to train a parrot?"));

        ChatCompletions chatCompletions = client.getChatCompletions(deploymentOrModelId, new ChatCompletionsOptions(chatMessages));

        System.out.printf("Model ID=%s is created at %s.%n", chatCompletions.getId(), chatCompletions.getCreatedAt());
        for (ChatChoice choice : chatCompletions.getChoices()) {
            ChatResponseMessage message = choice.getMessage();
            System.out.printf("Index: %d, Chat Role: %s.%n", choice.getIndex(), message.getRole());
            System.out.println("Message:");
            System.out.println(message.getContent());
        }

        System.out.println();
        CompletionsUsage usage = chatCompletions.getUsage();
        System.out.printf("Usage: number of prompt token is %d, "
                + "number of completion token is %d, and number of total tokens in request and response is %d.%n",
            usage.getPromptTokens(), usage.getCompletionTokens(), usage.getTotalTokens());
		
	}
	
	private static final String CONTEXT = """
			Context document 1: Teplizumab traces its roots to a New Jersey drug company called Ortho Pharmaceutical. There, scientists generated an early version of the antibody, dubbed OKT3. 
			Originally sourced from mice, the molecule was able to bind to the surface of T cells and limit their cell-killing potential. 
			In 1986, it was approved to help prevent organ rejection after kidney transplants, making it the first therapeutic antibody allowed for human use.
			""";
	
	@Test
	public void testOpenAIPromptWithChatCompletions() throws Exception {
        String deploymentOrModelId = "gpt-3.5-turbo";

        OpenAIClient client = buildChatCompletionsClient();

        List<ChatRequestMessage> chatMessages = new ArrayList<>();
        chatMessages.add(new ChatRequestSystemMessage("You are a helpful assistant. Your goal is to answer the following question, using the associated texts as context, as truthfully as you can."));
        chatMessages.add(new ChatRequestUserMessage(CONTEXT));
        chatMessages.add(new ChatRequestUserMessage("Question: What was OKT3 originally sourced from?"));

        ChatCompletions chatCompletions = client.getChatCompletions(deploymentOrModelId, new ChatCompletionsOptions(chatMessages));

        System.out.printf("Model ID=%s is created at %s.%n", chatCompletions.getId(), chatCompletions.getCreatedAt());
        for (ChatChoice choice : chatCompletions.getChoices()) {
            ChatResponseMessage message = choice.getMessage();
            System.out.printf("Index: %d, Chat Role: %s.%n", choice.getIndex(), message.getRole());
            System.out.println("Message:");
            System.out.println(message.getContent());
        }

        System.out.println();
        CompletionsUsage usage = chatCompletions.getUsage();
        System.out.printf("Usage: number of prompt token is %d, "
                + "number of completion token is %d, and number of total tokens in request and response is %d.%n",
            usage.getPromptTokens(), usage.getCompletionTokens(), usage.getTotalTokens());
		
	}	
	
	private OpenAIClient buildEmbeddingsClient() {
		String key = System.getenv("OPENAI_API_KEY");
		OpenAIClient client = new OpenAIClientBuilder()
			    .credential(new KeyCredential(key))
			    .endpoint("https://api.openai.com/v1/chat/embeddings")
			    .buildClient();
		return client;
	}
	
	@Test
	public void testOpenAIEmbeddings() throws Exception {
		String model = "text-embedding-ada-002";
		OpenAIEmbeddingsKeyExtractor keyExtractor = new OpenAIEmbeddingsKeyExtractor(buildEmbeddingsClient(), model, null, null);
		
		List<Double> embedding = keyExtractor.asStringDoubleVectorKeyExtractor().extract("Hello world!", new PrintStreamProgressMonitor());		
		System.out.println(embedding.size());
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try (DataOutputStream dos = new DataOutputStream(new GZIPOutputStream(baos))) {
			for (Double d: embedding) {
				dos.writeDouble(d);
			}
		}
		baos.close();
		byte[] bytes = baos.toByteArray();
		String digest = Hex.encodeHexString(MessageDigest.getInstance("SHA-256").digest(bytes));
		Files.write(bytes, new File("target/" + digest + ".gz"));
	}	
	
	@Test
	public void testStoringOpenAIEmbeddings() throws Exception {
		String model = "text-embedding-ada-002";
		OpenAIEmbeddingsKeyExtractor keyExtractor = new OpenAIEmbeddingsKeyExtractor(buildEmbeddingsClient(), model, null, null);
		
		List<Double> embedding = keyExtractor.asStringDoubleVectorKeyExtractor().extract("Hello world!", new PrintStreamProgressMonitor());		
		System.out.println(embedding.size());
		
		ArrayListZipEntryStore store = new ArrayListZipEntryStore();
		PrintStreamProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		
		Function<List<Double>, byte[]> keyEncoder = dList -> {
			ByteBuffer buf = ByteBuffer.allocate(dList.size() * Double.BYTES);
			dList.forEach(buf::putDouble);
			return buf.array();
		};
		
		Function<List<String>, String> valueEncoder = vList -> {
			JSONArray jsonArray = new JSONArray();
			vList.forEach(jsonArray::put);
			return jsonArray.toString();
		};
		
		Store<List<Double>, List<String>, Integer> adapter = store.adapt(keyEncoder, valueEncoder, null, null); // Don't need decoders - not reading this store
		
		adapter.add(embedding, Collections.singletonList("@myVal"), progressMonitor);
		
		String fileName = "target/my-embeddings-store.zip";
		store.save(new ZipOutputStream(new FileOutputStream(fileName)));
		
		store = new ArrayListZipEntryStore(new ZipFile(fileName));
		for (Entry<byte[], String> result: store.getEntries()) {
			System.out.println(result.getValue() + " "  + result.getKey().length);
		}
	}	
	
	@Test
	public void testStoringPdfTextEmbeddings() throws Exception {
		// Key extractor
		String model = "text-embedding-ada-002";
		StringDoubleVectorKeyExtractor keyExtractor = new OpenAIEmbeddingsKeyExtractor(buildEmbeddingsClient(), model, null, null).asStringDoubleVectorKeyExtractor();
		
		// Store
		ArrayListZipEntryStore store = new ArrayListZipEntryStore();
		
		Function<List<Double>, byte[]> keyEncoder = dList -> {
			ByteBuffer buf = ByteBuffer.allocate(dList.size() * Double.BYTES);
			dList.forEach(buf::putDouble);
			return buf.array();
		};
		
		Function<List<String>, String> valueEncoder = vList -> {
			JSONArray jsonArray = new JSONArray();
			vList.forEach(jsonArray::put);
			return jsonArray.toString();
		};
		
		Store<List<Double>, List<String>, Integer> adapter = store.adapt(keyEncoder, valueEncoder, null, null); // Don't need decoders - not reading this store

		// Splitter
		EncodingRegistry registry = Encodings.newDefaultEncodingRegistry();
		Encoding enc = registry.getEncoding(EncodingType.CL100K_BASE);
		
		PdfTextSplitter pdfTextSplitter = new PdfTextSplitter(
				1000, 
				50, 
				20, 
				s -> enc
					.encode(s)
					.stream()
					.map(token -> enc.decode(Collections.singletonList(token)))
					.toList());
		
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("pdf", new PdfTextResourceFactory());
		File test = new File("test.pdf").getCanonicalFile();		
		URI resourceURI = URI.createFileURI(test.getCanonicalFile().getAbsolutePath());
//		URI resourceURI = URI.createFileURI(TEST.getCanonicalFile().getAbsolutePath());
		Resource pdfTextResource = resourceSet.getResource(resourceURI, true);
		PrintStreamProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		for (EObject root: pdfTextResource.getContents()) {
			Document modelDocument = (Document) root;
			List<Chunk> chunks = pdfTextSplitter.split(modelDocument);
			System.out.println(chunks.size());
			for (Chunk chunk: chunks) {
				List<EObject> sources = chunk.getSources();
				if (!sources.isEmpty()) {
					List<Double> embedding = keyExtractor.extract("Hello world!", progressMonitor);
					List<String> range = new ArrayList<>();					
					EObject start = sources.get(0);
					range.add(pdfTextResource.getURIFragment(start));
					if (sources.size() > 1) {
						EObject end = sources.get(sources.size() - 1);
						range.add(pdfTextResource.getURIFragment(end));
					}					
					adapter.add(embedding, range, progressMonitor);
				}
			}
		}
		
		String fileName = "target/test-pdf-embeddings.zip";
		store.save(new ZipOutputStream(new FileOutputStream(fileName)));
		
		store = new ArrayListZipEntryStore(new ZipFile(fileName));
	}
	
	/**
	 * A basic test to see how indexing works
	 * @throws Exception
	 */
	@Test
	public void testLoadPdfEmbeddingsStore() throws Exception {
		String fileName = "target/test-pdf-embeddings.zip";
		ArrayListZipEntryStore store = new ArrayListZipEntryStore(new ZipInputStream(new FileInputStream(fileName)));
		for (Entry<byte[], String> entry: store.getEntries()) {
			System.out.println(entry.getValue() + " " + entry.getKey().length);
		}
		
	}
	
}
