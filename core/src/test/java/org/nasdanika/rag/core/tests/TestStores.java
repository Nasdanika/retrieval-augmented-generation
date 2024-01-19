package org.nasdanika.rag.core.tests;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.junit.jupiter.api.Test;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.rag.core.ArrayListZipEntryStore;
import org.nasdanika.rag.core.IndexStore;
import org.nasdanika.rag.core.IndexStore.IndexItem;
import org.nasdanika.rag.core.Store.SearchResult;

import com.github.jelmerk.knn.DistanceFunction;
import com.github.jelmerk.knn.Index;
import com.github.jelmerk.knn.bruteforce.BruteForceIndex;
import com.github.jelmerk.knn.hnsw.HnswIndex;

public class TestStores {
	
	/**
	 * A basic test to see how indexing works
	 * @throws Exception
	 */
	@Test
	public void testArrayListZipEntryStore() throws Exception {
		ArrayListZipEntryStore store = new ArrayListZipEntryStore();
		PrintStreamProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		byte[] mamaKey = "Mama".getBytes();
		String mamaValue = "Mama Value (Comment)";
		store.add(mamaKey, mamaValue, progressMonitor);
		store.add("Papa".getBytes(), "Papa Value", progressMonitor);
		
		String fileName = "target/my-store.zip";
		store.save(new ZipOutputStream(new FileOutputStream(fileName)));
		
		store = new ArrayListZipEntryStore(new ZipFile(fileName));
		for (SearchResult<String, Integer> result: store.findNearest(mamaKey, 5)) {
			System.out.println(result.getValue() + " "  + result.getDistance());
		}

		store = new ArrayListZipEntryStore(new ZipInputStream(new FileInputStream(fileName)));
		for (SearchResult<String, Integer> result: store.findNearest(mamaKey, 5)) {
			System.out.println(result.getValue() + " "  + result.getDistance());
		}		
		
	}
	
	@Test
	public void testHnswIndexStore() throws Exception {
		DistanceFunction<Integer, Integer> distanceFunction = (a,b) -> Math.abs(a-b);
		
	    HnswIndex<Integer, Integer,IndexStore.IndexItem<Integer, Integer>, Integer> hnswIndex = HnswIndex
	            .newBuilder(1, distanceFunction, 1000)
	            .withM(16)
	            .withEf(200)
	            .withEfConstruction(200)
	            .build();
	    
//	    Index<Integer, Integer, IndexItem<Integer, Integer>, Integer> exactIndex = hnswIndex.asExactIndex();

	    IndexStore<Integer, Integer, Integer> store = new IndexStore<Integer, Integer, Integer>(hnswIndex);
	    ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
	    
		for (int i = 0; i < 950; ++i) {
			store.add(i, i, progressMonitor);
		}
		
		store.add(812, 1812, progressMonitor);		
		store.add(813, 2813, progressMonitor);		
		
		for (int i = 0; i < 10; ++i) {
			int next = i * 90;
			System.out.println(next);
			List<SearchResult<Integer, Integer>> approximateResults = store.findNearest(next, 10);
			for (SearchResult<Integer, Integer> ar: approximateResults) {
				System.out.println("\t" + ar.getValue() + " " + ar.getDistance());
			}			
		}
		
	}		
	
	@Test
	public void testBrueForceIndexStore() throws Exception {
		DistanceFunction<Integer, Integer> distanceFunction = (a,b) -> Math.abs(a-b);
	    BruteForceIndex<Integer, Integer,IndexStore.IndexItem<Integer, Integer>, Integer> bruteForceIndex = BruteForceIndex.newBuilder(1, distanceFunction).build();	    
	    IndexStore<Integer, Integer, Integer> store = new IndexStore<Integer, Integer, Integer>(bruteForceIndex);
	    ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
	    
		for (int i = 0; i < 950; ++i) {
			store.add(i, i, progressMonitor);
		}
		
		store.add(812, 1812, progressMonitor);		
		store.add(813, 2813, progressMonitor);		
		
		for (int i = 0; i < 10; ++i) {
			int next = i * 90;
			System.out.println(next);
			List<SearchResult<Integer, Integer>> approximateResults = store.findNearest(next, 10);
			for (SearchResult<Integer, Integer> ar: approximateResults) {
				System.out.println("\t" + ar.getValue() + " " + ar.getDistance());
			}			
		}
		
	}		
	
}
