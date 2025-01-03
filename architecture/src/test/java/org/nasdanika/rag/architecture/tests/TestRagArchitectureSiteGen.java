package org.nasdanika.rag.architecture.tests;

import java.io.File;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.junit.jupiter.api.Test;
import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.capability.ServiceCapabilityFactory.Requirement;
import org.nasdanika.capability.emf.ResourceSetRequirement;
import org.nasdanika.common.Context;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.ExecutionException;
import org.nasdanika.common.MutableContext;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.bootstrap.Theme;
import org.nasdanika.models.app.gen.AppSiteGenerator;
import org.nasdanika.models.app.graph.emf.EObjectReflectiveProcessorFactoryProvider;
import org.nasdanika.models.architecture.processors.doc.ArchitectureHtmlAppGenerator;
import org.nasdanika.models.architecture.processors.doc.ArchitectureNodeProcessorFactory;

public class TestRagArchitectureSiteGen {
		
	@Test
	public void testGenerateRagArchitectureSite() throws Exception {
		CapabilityLoader capabilityLoader = new CapabilityLoader();
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		Requirement<ResourceSetRequirement, ResourceSet> requirement = ServiceCapabilityFactory.createRequirement(ResourceSet.class);		
		ResourceSet resourceSet = capabilityLoader.loadOne(requirement, progressMonitor);
				
		File ragArchitectureDiagramFile = new File("architecture.drawio").getCanonicalFile();
		Resource ragArchitectureResource = resourceSet.getResource(URI.createFileURI(ragArchitectureDiagramFile.getAbsolutePath()), true);
		
		// Generating an action model
		MutableContext context = Context.EMPTY_CONTEXT.fork();
		
		Consumer<Diagnostic> diagnosticConsumer = d -> d.dump(System.out, 0);		
		
		File actionModelsDir = new File("target\\action-models\\");
		actionModelsDir.mkdirs();
								
		File output = new File(actionModelsDir, "architecture.xmi");
				
		ArchitectureHtmlAppGenerator htmlAppGenerator = new ArchitectureHtmlAppGenerator(
				ragArchitectureResource.getContents().get(0),
				new ArchitectureNodeProcessorFactory(context, null)) {
			

			@Override
			protected org.nasdanika.models.app.graph.emf.EObjectReflectiveProcessorFactoryProvider createReflectiveFactoryProvider(Object reflectiveFactory) {
				return new EObjectReflectiveProcessorFactoryProvider(reflectiveFactory) {
					
					@Override
					protected boolean isCompactPath(org.nasdanika.graph.processor.ProcessorConfig config) {
						return true;
					
					}					
					
				};				
			}
			
		};
		
		htmlAppGenerator.generateHtmlAppModel(
				diagnosticConsumer, 
				output,
				progressMonitor);
				
		// Generating a web site
		String rootActionResource = "actions.yml";
		URI rootActionURI = URI.createFileURI(new File(rootActionResource).getAbsolutePath());//.appendFragment("/");
		
		String siteMapDomain = "https://rag.nasdanika.org";		
		AppSiteGenerator actionSiteGenerator = new AppSiteGenerator() {
			
			protected boolean isDeleteOutputPath(String path) {
				return !"CNAME".equals(path) && !path.startsWith("model/");		
			};
			
			protected Context createContext(ProgressMonitor progressMonitor) {
				return context;
			};
			
		};		
		
		Map<String, Collection<String>> errors = actionSiteGenerator.generate(
				rootActionURI, 
				Theme.Cerulean.pageTemplateCdnURI, 
				siteMapDomain, 
				new File("../docs"), // Publishing to the repository's docs directory for GitHub pages 
				new File("target/architecture-doc-site-work-dir"), 
				true);
				
		int errorCount = 0;
		for (Entry<String, Collection<String>> ee: errors.entrySet()) {
			System.err.println(ee.getKey());
			for (String error: ee.getValue()) {
				System.err.println("\t" + error);
				++errorCount;
			}
		}
		
		System.out.println("There are " + errorCount + " site errors");
		
		if (errors.size() != 11) {
			throw new ExecutionException("There are problems with pages: " + errorCount);
		}		
		
	}
	
}
