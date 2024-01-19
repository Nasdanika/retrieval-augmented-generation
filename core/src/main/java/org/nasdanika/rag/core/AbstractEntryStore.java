package org.nasdanika.rag.core;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.nasdanika.common.ProgressMonitor;

/**
 * 
 * Simple store implementation on top of a collection of entries
 * @param <K>
 * @param <V>
 * @param <D>
 */
public abstract class AbstractEntryStore<K,V,D> implements Store<K,V,D> {
	
	public abstract Collection<Map.Entry<K,V>> getEntries();
	
	protected abstract D distance(K a, K b);
	
	protected abstract int compareDistance(D a, D b);

	@Override
	public void add(K key, V value, ProgressMonitor progressMonitor) {
		getEntries().add(Map.entry(key, value));		
	}
	
	protected boolean isParallelSearch(K key) {
		return true;
	}
	
	protected boolean isParallelAddAll() {
		return false;
	}
	
	@Override
	public void addAll(Iterable<V> values, KeyExtractor<V, K> keyExtractor, ProgressMonitor progressMonitor) {
		addAll(StreamSupport.stream(values.spliterator(), isParallelAddAll()), keyExtractor, progressMonitor);
	}	

	@Override
	public List<SearchResult<V, D>> findNearest(K key, int limit) {
		Stream<Map.Entry<K,V>> entryStream = isParallelSearch(key) ? getEntries().parallelStream() : getEntries().stream();
		
		class SearchResultImpl implements SearchResult<V,D> {
			
			D distance;
			
			V value;
			
			public SearchResultImpl(Map.Entry<K, V> entry) {
				distance = distance(entry.getKey(), key);
				value = entry.getValue(); 
			}
			
			@Override
			public int compareTo(SearchResult<V, D> o) {
				return compareDistance(distance, o.getDistance());
			}
			
			@Override
			public V getValue() {
				return value;
			}
			
			@Override
			public D getDistance() {
				return distance;
			}
			
		}
		
		return entryStream
				.map(e -> (SearchResult<V,D>) new SearchResultImpl(e))
				.limit(limit)
				.toList();
	}

}
