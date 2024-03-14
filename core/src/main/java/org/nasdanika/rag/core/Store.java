package org.nasdanika.rag.core;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.nasdanika.common.ProgressMonitor;

/**
 * Abstraction of stores which can find values by key similarity/distance
 * @param <K> Key type, e.g. String
 * @param <V> Value type, e.g. double[] or List<Double>
 * @param <D> Distance type, e.g. Double. Can be Void or Boolean for key types which don't support distance/similarity computation, only equality.   
 */
public interface Store<K,V,D> {
	
	interface SearchResult<V,D> extends Comparable<SearchResult<V,D>> {
		
		V getValue();
		
		D getDistance();
		
	}
	
	void add(K key, V value, ProgressMonitor progressMonitor);
	
	default void add(V value, KeyExtractor<V, K> keyExtractor, ProgressMonitor progressMonitor) {
		add(keyExtractor.extract(value, progressMonitor), value, progressMonitor);
	}
	
	default void addAll(Iterable<V> values, KeyExtractor<V, K> keyExtractor, ProgressMonitor progressMonitor) {
		addAll(StreamSupport.stream(values.spliterator(), false), keyExtractor, progressMonitor); // Implementations may customize parallel behavior
	}
	
	default void addAll(Stream<V> values, KeyExtractor<V, K> keyExtractor, ProgressMonitor progressMonitor) {
		values.forEach(v -> add(v, keyExtractor, progressMonitor));
	}
	
//	default void addAll(Iterable<V> values, Executor executor, KeyExtractor<V, K> keyExtractor, ProgressMonitor progressMonitor) {
//		throw new UnsupportedOperationException("TODO");
//	}
	
	/**
	 * Returns values with distances for nearest keys. Ordered by distance.
	 * @param key
	 * @param limit maximum number of elements to return
	 * @return
	 */
	List<SearchResult<V,D>> findNearest(K key, int limit);
	
	default <L,U,E> Store<L,U,E> adapt(
			Function<L,K> keyEncoder, 
			Function<U,V> valueEncoder, 
			Function<V,U> valueDecoder, 
			Function<D,E> distanceDecoder) {
		
		return new Store<L,U,E>() {
			
			class SearchResultAdapter implements SearchResult<U,E> {
				
				private SearchResult<V, D> target;

				SearchResultAdapter(SearchResult<V,D> target) {
					this.target = target;
				}

				@Override
				public int compareTo(SearchResult<U, E> o) {
					if (o == this) {
						return 0;
					}
					
					if (SearchResultAdapter.class.isInstance(o)) {
						return target.compareTo(((SearchResultAdapter) o).target);
					}
					throw new IllegalArgumentException();
				}

				@Override
				public U getValue() {
					return valueDecoder.apply(target.getValue());
				}

				@Override
				public E getDistance() {
					return distanceDecoder.apply(target.getDistance());
				}
				
			}

			@Override
			public void add(L key, U value, ProgressMonitor progressMonitor) {
				Store.this.add(keyEncoder.apply(key), valueEncoder.apply(value), progressMonitor);				
			}

			@Override
			public List<SearchResult<U, E>> findNearest(L key, int limit) {
				return Store.this
						.findNearest(keyEncoder.apply(key), limit)
						.stream()
						.map(sr -> (SearchResult<U,E>) new SearchResultAdapter(sr))
						.toList();
			}
			
		};		
		
	}
	
}
