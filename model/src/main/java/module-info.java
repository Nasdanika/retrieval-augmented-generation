import org.nasdanika.capability.CapabilityFactory;
import org.nasdanika.rag.model.util.RagEPackageResourceSetCapabilityFactory;

module org.nasdanika.rag.model {
	exports org.nasdanika.rag.model;
	exports org.nasdanika.rag.model.impl;
	exports org.nasdanika.rag.model.util;
	
	requires transitive org.eclipse.emf.ecore;
	requires transitive org.eclipse.emf.common;
	requires transitive org.nasdanika.ncore;
	
	provides CapabilityFactory with RagEPackageResourceSetCapabilityFactory;
	
}