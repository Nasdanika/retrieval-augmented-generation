package org.nasdanika.rag.core;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Map.Entry;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Entry store loaded from and stored to a zip archive. 
 * Value is a zip entry comment - 64 k characters limit. 
 * This class leaves distance computation as well as in-memory storage to subclasses
 * @param <D>
 */
public abstract class ZipEntryStore<D> extends AbstractEntryStore<byte[], String, D> {
	
	public ZipEntryStore(ZipInputStream in) throws IOException {
	    try (in) {
	        ZipEntry entry;
	        while ((entry = in.getNextEntry()) != null)  {	        	
	        	loadEntry(in, entry);
	        }
	    }
	}

	protected void loadEntry(InputStream in, ZipEntry entry) throws IOException {
		if (!entry.isDirectory()) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try (baos) {
			    int b; 
			    while ((b = in.read()) != -1) {
			        baos.write(b);
			    }
			}
			loadEntry(baos.toByteArray(), getValue(entry));
		}
	}
		
	public ZipEntryStore(ZipFile in) throws IOException {
		try (in) {
			Enumeration<? extends ZipEntry> entries = in.entries();

			while(entries.hasMoreElements()){
			    ZipEntry entry = entries.nextElement();
			    loadEntry(entry.isDirectory() ? null : in.getInputStream(entry) , entry);
			}
		}
	}
	
	public ZipEntryStore() {
		
	}	
	
	protected String getValue(ZipEntry entry) {
		return entry.getName();
	}
	
	protected ZipEntry createZipEntry(byte[] key, String value) {
		return new ZipEntry(value);
	}
	
	protected abstract void loadEntry(byte[] key, String value) throws IOException;
	
	public void save(ZipOutputStream out) throws IOException {
		try (out) {
			for (Entry<byte[], String> entry: getEntries()) {
				out.putNextEntry(createZipEntry(entry.getKey(), entry.getValue()));
				out.write(entry.getKey());
				out.closeEntry();
			}
		}		
	}

}
