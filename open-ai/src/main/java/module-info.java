module org.nasdanika.rag.openai {

	requires transitive com.azure.ai.openai;
	requires transitive com.azure.core;
	requires transitive org.nasdanika.models.rag.core;

	exports org.nasdanika.rag.openai;	
	
}
