package org.nasdanika.rag.playground.tests;

import java.text.BreakIterator;
import java.util.Locale;

import org.junit.jupiter.api.Test;

public class TestMisc {
	
	@Test
	public void testBreakIterator() {
		BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);
		String source = "This is a test. This is a T.L.A. test. Now with a Dr. in it.";
		iterator.setText(source);
		int start = iterator.first();
		for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator.next()) {
			System.out.println(source.substring(start, end) + "*");
		}
	}

}
