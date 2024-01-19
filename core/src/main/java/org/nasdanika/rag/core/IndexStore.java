package org.nasdanika.rag.core;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.nasdanika.common.ProgressMonitor;

import com.github.jelmerk.knn.Index;
import com.github.jelmerk.knn.Item;
import com.github.jelmerk.knn.hnsw.HnswIndex;

public class IndexStore<K, V, D> implements Store<K, V, D> {
	
	protected Index<V, K, IndexItem<V, K>, D> index;

	public IndexStore(Index<V, K, IndexItem<V,K>, D> index) {
		this.index = index;		
	}
	
	public IndexStore(
			HnswIndex<V, K, IndexItem<V,K>, D> index, 
			Iterable<Map.Entry<K, V>> entries,
			ProgressMonitor progressMonitor) {
		this(index);
		if (entries != null) {
			entries.forEach(entry -> add(entry.getKey(), entry.getValue(), progressMonitor));
		}
	}
	
	public static class IndexItem<V,K> implements Item<V,K> {
		
		private V value;
		private K key;
		int dimensions;

		public IndexItem(V value, K key) {
			this.value = value;
			this.key = key;
			
			if (key.getClass().isArray()) {
				dimensions = Array.getLength(key);
			} else if (key instanceof Collection) {
				dimensions = ((Collection<?>) key).size();
			} else {
				dimensions = 1;
			}
		}

		@Override
		public V id() {
			return value;
		}

		@Override
		public K vector() {
			return key;
		}

		@Override
		public int dimensions() {
			return dimensions;
		}
		
	}
	
	protected IndexItem<V,K> createItem(K key, V value) {
		return new IndexItem<V,K>(value, key);
	}

	@Override
	public void add(K key, V value, ProgressMonitor progressMonitor) {
		index.add(createItem(key, value));
	}

	@Override
	public List<SearchResult<V, D>> findNearest(K key, int limit) {
		
		class SearchResultImpl implements SearchResult<V,D> {
			
			com.github.jelmerk.knn.SearchResult<IndexItem<V,K>,D> target;
			
			public SearchResultImpl(com.github.jelmerk.knn.SearchResult<IndexItem<V,K>,D> sr) {
				target = sr;
			}
			
			@Override
			public int compareTo(SearchResult<V, D> o) {
				return target.compareTo(((SearchResultImpl) o).target);
			}
			
			@Override
			public V getValue() {
				return target.item().id();
			}
			
			@Override
			public D getDistance() {
				return target.distance();
			}
			
		}
		
		return index.findNearest(key, limit).stream().map(sr -> (SearchResult<V,D>) new SearchResultImpl(sr)).toList();
	}

}
