package com.aurionpro.model;

import java.util.Comparator;

public class BookDescendingTitleComparator implements Comparator <Book>{

	@Override
	public int compare(Book o1, Book o2) {
		return o2.getTitle().compareTo(o1.getTitle());
	}
	
}
