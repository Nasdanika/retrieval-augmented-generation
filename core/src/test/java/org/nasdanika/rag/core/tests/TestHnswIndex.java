package org.nasdanika.rag.core.tests;


import java.io.File;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

import com.github.jelmerk.knn.DistanceFunction;
import com.github.jelmerk.knn.Index;
import com.github.jelmerk.knn.SearchResult;
import com.github.jelmerk.knn.hnsw.HnswIndex;

public class TestHnswIndex {
	
	/**
	 * A basic test to see how indexing works
	 * @throws Exception
	 */
	@Test
	public void testIntegerIndex() throws Exception {
		
		DistanceFunction<Integer, Integer> distanceFunction = (a,b) -> Math.abs(a-b);
		
        HnswIndex<Integer, Integer,IntegerItem, Integer> hnswIndex = HnswIndex
                .newBuilder(10, distanceFunction, 1000)
                .withM(16)
                .withEf(200)
                .withEfConstruction(200)
                .build();		
        
        Random random = new SecureRandom();
        
        for (int i = 0; i < 950; ++i) {
        	hnswIndex.add(new IntegerItem(i));
        }
        
    	hnswIndex.add(new IntegerItem(1812, 812));
        
        
        Index<Integer, Integer, IntegerItem, Integer> exactIndex = hnswIndex.asExactIndex();
        
        for (int i = 0; i < 10; ++i) {
        	int next = i * 90;
        	System.out.println(next);
        	List<SearchResult<IntegerItem, Integer>> approximateResults = hnswIndex.findNearest(next, 10);
        	for (SearchResult<IntegerItem, Integer> ar: approximateResults) {
        		System.out.println("\t" + ar.item().id() + " " + ar.distance());
        	}
        	
        	System.out.println("\t----");
        	List<SearchResult<IntegerItem, Integer>> exactResults = exactIndex.findNearest(next, 10);
        	for (SearchResult<IntegerItem, Integer> er: exactResults) {
        		System.out.println("\t" + er.item().id() + " " + er.distance());
        	}
        }
        
        new File("target/tests").mkdirs();
        
        exactIndex.save(new File("target/tests/exact-integer-index.bin"));
        hnswIndex.save(new File("target/tests/hnsw-integer-index.bin"));
        
	}	
	
}
