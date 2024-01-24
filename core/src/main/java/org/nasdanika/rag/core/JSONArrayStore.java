package org.nasdanika.rag.core;

import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.Map.Entry;

import org.json.JSONArray;

/**
 * Entry store loaded from and stored to a zip archive. 
 * Value is a zip entry comment - 64 k characters limit. 
 * This class leaves distance computation as well as in-memory storage to subclasses
 * @param <D>
 */
public abstract class JSONArrayStore<D> extends AbstractEntryStore<Object, Object, D> {
	
	protected JSONArray data;
	
	public JSONArrayStore(JSONArray data) {
		this.data = data;
	}
	
	public JSONArray getData() {
		return data;
	}

//	@Override
//	public Collection<Entry<Object, Object>> getEntries() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
