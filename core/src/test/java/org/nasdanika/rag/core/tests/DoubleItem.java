package org.nasdanika.rag.core.tests;

import com.github.jelmerk.knn.Item;

@SuppressWarnings("serial")
public class DoubleItem implements Item<Double, Double> {
	
	private double value;

	public DoubleItem(double value) {
		this.value = value;
	}

	@Override
	public Double id() {
		return value;
	}

	@Override
	public Double vector() {
		return value;
	}

	@Override
	public int dimensions() {
		return 10;
	}
	
}