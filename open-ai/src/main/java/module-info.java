module org.nasdanika.rag.openai {

	requires transitive com.azure.ai.openai;
	requires transitive com.azure.core;
	requires transitive org.nasdanika.models.rag.core;
	requires org.apache.commons.codec;

	exports org.nasdanika.rag.openai;	
	
}
