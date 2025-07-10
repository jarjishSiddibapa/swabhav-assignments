package com.aurionpro.model;

public class Book implements Comparable<Book> {
	protected int bookId;
	protected String authorName;
	protected String title;
	protected double price;
	protected boolean isIssued = false;
	
	static int totalBookCount = 0;

	public Book(String authorName, String title, double price) {
		super();
		this.bookId = ++totalBookCount;
		this.authorName = authorName;
		this.title = title;
		this.price = price;
		this.isIssued = false;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isIssued() {
		return isIssued;
	}

	public void setIssued(boolean isIssued) {
		this.isIssued = isIssued;
	}
	
	public static int getTotalBookCount() {
		return totalBookCount;
	}

	public static void setTotalBookCount(int totalBookCount) {
		Book.totalBookCount = totalBookCount;
	}

	public void displayDetails() {
		System.out.println("The details of book id: #" + bookId);
		System.out.println("Author's Name: " + authorName);
		System.out.println("Title: " + title);
		System.out.println("Price: " + price);
		System.out.println();
	}

	@Override
	public int compareTo(Book o) {
		return this.authorName.compareTo(o.authorName);
	}
}
