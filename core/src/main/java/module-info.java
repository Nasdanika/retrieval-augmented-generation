module org.nasdanika.models.rag.core {
	
	requires transitive org.nasdanika.common;
	requires transitive hnswlib.core;
	requires transitive org.nasdanika.models.pdf;
	
	exports org.nasdanika.rag.core;
		
}
