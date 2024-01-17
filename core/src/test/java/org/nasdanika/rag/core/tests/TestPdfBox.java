package org.nasdanika.rag.core.tests;


import java.io.File;
import java.util.List;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import com.knuddels.jtokkit.Encodings;
import com.knuddels.jtokkit.api.Encoding;
import com.knuddels.jtokkit.api.EncodingRegistry;
import com.knuddels.jtokkit.api.EncodingType;

public class TestPdfBox {
	
	/**
	 * A basic test to see how indexing works
	 * @throws Exception
	 */
	@Test
	public void testReadPdf() throws Exception {
		
		PDDocument document = Loader.loadPDF(new File("test.pdf"));		
		
        PDFTextStripperByArea stripper = new PDFTextStripperByArea();
        stripper.setSortByPosition(true);
        PDFTextStripper tStripper = new PDFTextStripper();

        tStripper.setArticleStart("<article>" + System.lineSeparator());
        tStripper.setArticleEnd("</article>" + System.lineSeparator());
        
        tStripper.setParagraphStart("<paragraph>" + System.lineSeparator());
        tStripper.setParagraphEnd("</paragraph>" + System.lineSeparator());
        
        
        String pdfFileInText = tStripper.getText(document);
        System.out.println(pdfFileInText);
        System.out.println("==== " + pdfFileInText.length());
        
        EncodingRegistry registry = Encodings.newDefaultEncodingRegistry();
        Encoding enc = registry.getEncoding(EncodingType.CL100K_BASE);
        List<Integer> encoded = enc.encode(pdfFileInText);
        System.out.println("==== " + encoded.size());
                
        String decoded = enc.decode(encoded);
        System.out.println(decoded);
        
        
        Document parsed = Jsoup.parse("<document>" + pdfFileInText + "</document>");
        dump(parsed, 0);
        
        // decoded = "This is a sample sentence."

        // Or get the tokenizer based on the model type
//        Encoding secondEnc = registry.getEncodingForModel(ModelType.TEXT_EMBEDDING_ADA_002);        
        
	}	
	
	protected void dump(Element element, int indent) {
		for (int i = 0; i < indent; ++i) {
			System.out.print("\t");
		}
        System.out.print(element.tagName());
        if ("paragraph".equals(element.tagName())) {
        	System.out.print(": " + element.text());
        }
        System.out.println();
        for (Element child: element.children()) {
        	dump(child, indent + 1);
        }		
	}
	
//	@Test
//	public void testPdf2Html() throws Exception {
//		try (PDDocument pdf = PDDocument.load(new File("test.pdf"))) {	
//			PDFDomTree parser = new PDFDomTree();
//			try (Writer output = new FileWriter("target/test-pdf.html")) {
//				parser.writeText(pdf, output);
//			}
//		}		
//	}
	
}
