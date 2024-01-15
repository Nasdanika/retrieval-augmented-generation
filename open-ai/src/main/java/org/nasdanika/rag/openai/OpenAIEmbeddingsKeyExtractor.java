package org.nasdanika.rag.openai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.rag.core.KeyExtractor;
import org.nasdanika.rag.core.StringDoubleVectorKeyExtractor;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.models.EmbeddingItem;
import com.azure.ai.openai.models.Embeddings;
import com.azure.ai.openai.models.EmbeddingsOptions;

/**
 * 
 */
public class OpenAIEmbeddingsKeyExtractor implements KeyExtractor<List<String>, List<List<Double>>> {
	
	private OpenAIClient client;
	private String model;
	private String user;
	private String deploymentOrModelId;

	public OpenAIEmbeddingsKeyExtractor(
			OpenAIClient client,
			String deploymentOrModelId,
			String model, 
			String user) {
		this.client = client;
		this.deploymentOrModelId = deploymentOrModelId;
		this.model = model;
		this.user = user;
	}

	@Override
	public List<List<Double>> extract(List<String> value, ProgressMonitor progressMonitor) {
		EmbeddingsOptions embeddingsOptions = new EmbeddingsOptions(value);
		if (model != null) {
			embeddingsOptions.setModel(model);
		}
		if (user != null) {
			embeddingsOptions.setUser(user);
		}
		Embeddings embeddings = client.getEmbeddings(deploymentOrModelId, embeddingsOptions);
		return embeddings.getData().stream().map(EmbeddingItem::getEmbedding).toList();
	}
	
	public StringDoubleVectorKeyExtractor asSingleton() {		
		return (value, progressMonitor) -> extract(Collections.singletonList(value), progressMonitor).get(0);
	}
	
	public KeyExtractor<Map<String,String>, Map<String,List<Double>>> asMap() {
		return (value, progressMonitor) -> {
			List<String> keys = new ArrayList<>();
			List<String> values = new ArrayList<>();
			value.entrySet().forEach(e -> {
				keys.add(e.getKey());
				values.add(e.getValue());
			});
			
			Map<String, List<Double>> result = new LinkedHashMap<>();
			List<List<Double>> embeddings = extract(values, progressMonitor);
			for (int i = 0; i < embeddings.size(); ++i) {
				result.put(keys.get(i), embeddings.get(i));
			}
			
			return result;
		};
	}

}
