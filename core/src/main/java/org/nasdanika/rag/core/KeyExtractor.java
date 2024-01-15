package org.nasdanika.rag.core;

import java.util.function.BiFunction;
import java.util.function.Function;

import org.nasdanika.common.ProgressMonitor;

/**
 * Extracts key (e.g. a vector) from value (e.g. String)
 * @param <V> value type
 * @param <K> key type
 */
public interface KeyExtractor<V,K> {
	
	K extract(V value, ProgressMonitor progressMonitor);
	
	static <V,K> KeyExtractor<V,K> from(Function<V,K> function) {
		return (value, progressMonitor) -> function.apply(value);
	}
	
	static <V,K> KeyExtractor<V,K> from(BiFunction<V, ProgressMonitor, K> biFunction) {
		return biFunction::apply;
	}
	
	default <L> KeyExtractor<V,L> then(Function<K,L> after) {
		return (value, progressMonitor) -> after.apply(extract(value, progressMonitor));
	}
	
	default <L> KeyExtractor<V,L> then(BiFunction<K, ProgressMonitor, L> after) {
		return (value, progressMonitor) -> after.apply(extract(value, progressMonitor), progressMonitor);
	}
	
	default <U> KeyExtractor<U,K> before(Function<U,V> before) {
		return (value, progressMonitor) -> extract(before.apply(value), progressMonitor);
	}
	
	default <U> KeyExtractor<U,K> before(BiFunction<U, ProgressMonitor, V> before) {
		return (value, progressMonitor) -> extract(before.apply(value, progressMonitor), progressMonitor);
	}

}
