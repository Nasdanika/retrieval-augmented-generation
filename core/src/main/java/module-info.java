module org.nasdanika.models.rag.core {
	
	requires transitive org.nasdanika.common;
	requires transitive hnswlib.core;
	requires org.jsoup;
	
	exports org.nasdanika.rag.core;
		
}
