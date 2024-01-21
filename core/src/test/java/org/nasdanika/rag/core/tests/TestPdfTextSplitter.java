package org.nasdanika.rag.core.tests;

import java.io.File;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.jupiter.api.Test;
import org.nasdanika.models.pdf.Document;
import org.nasdanika.models.pdf.util.PdfTextResourceFactory;
import org.nasdanika.rag.core.PdfTextSplitter;
import org.nasdanika.rag.core.PdfTextSplitter.Chunk;

import com.knuddels.jtokkit.Encodings;
import com.knuddels.jtokkit.api.Encoding;
import com.knuddels.jtokkit.api.EncodingRegistry;
import com.knuddels.jtokkit.api.EncodingType;

public class TestPdfTextSplitter {
	
	private static final File TEST = new File("C:\\Users\\Pavel\\Downloads\\togaf-standard-10th-edition-fc-evaluation-bundle-2023-04\\C220-Part1e.pdf");
	
	@Test
	public void testPdfTextSplitter() throws Exception {
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
		for (EObject root: pdfTextResource.getContents()) {
			Document modelDocument = (Document) root;
			List<Chunk> chunks = pdfTextSplitter.split(modelDocument);
			System.out.println(chunks.size());
			for (Chunk chunk: chunks) {
				System.out.println("--- " + chunk.getText().length() + " " + chunk.size() + "/" + chunk.overlap() + " " + chunk.getSources().size());
				System.out.println(chunk.getText());
			}
		}
	}

}
