package org.nasdanika.rag.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * Stores entries in a list. Uses sum of abs element deltas as distance.
 */
public class ArrayListZipEntryStore extends ZipEntryStore<Integer> {

	public ArrayListZipEntryStore() {
		super();
	}

	public ArrayListZipEntryStore(ZipFile in) throws IOException {
		super(in);
	}

	public ArrayListZipEntryStore(ZipInputStream in) throws IOException {
		super(in);
	}
	
	protected List<Entry<byte[], String>> entries;

	@Override
	protected void loadEntry(byte[] key, String value) throws IOException {
		getEntries().add(Map.entry(key, value));		
	}

	@Override
	public Collection<Entry<byte[], String>> getEntries() {
		if (entries == null) {
			 entries = new ArrayList<>();
		}
		return entries;
	}

	/**
	 * Longer vector is greater. For the same size - Manhattan distance.
	 */
	@Override
	protected Integer distance(byte[] a, byte[] b) {
		int ld = a.length - b.length;
		if (ld != 0) {
			return ld;
		}

		int result = 0;
		for (int i = 0; i < a.length; ++i) {
			result += Math.abs(a[i] - b[i]);
		}
		return result;
	}

	@Override
	protected int compareDistance(Integer a, Integer b) {
		if (Objects.equals(a, b)) {
			return 0;
		}
		return a - b;
	}

}
