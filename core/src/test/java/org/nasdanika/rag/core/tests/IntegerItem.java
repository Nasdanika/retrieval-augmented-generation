package org.nasdanika.rag.core.tests;

import com.github.jelmerk.knn.Item;

@SuppressWarnings("serial")
public class IntegerItem implements Item<Integer, Integer> {
	
	private int value;
	private int id;

	public IntegerItem(int id, int value) {
		this.id = id;
		this.value = value;
	}

	public IntegerItem(int value) {
		this(value, value);
	}

	@Override
	public Integer id() {
		return id;
	}

	@Override
	public Integer vector() {
		return value;
	}

	@Override
	public int dimensions() {
		return 10;
	}
	
}