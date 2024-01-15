module org.nasdanika.rag.processors {
		
	requires transitive org.nasdanika.rag.model;
	requires transitive org.nasdanika.models.ecore.graph;
	
//	exports org.nasdanika.rag.processors.doc;
//	opens org.nasdanika.rag.processors.doc; // For loading resources
	
	exports org.nasdanika.rag.processors.ecore;
	opens org.nasdanika.rag.processors.ecore; // For loading resources
	
//	exports org.nasdanika.rag.processors.factories;
	
}
