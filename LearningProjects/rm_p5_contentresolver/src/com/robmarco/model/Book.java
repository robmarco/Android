package com.robmarco.model;

public class Book {
	
	private int id;
	private String title;
	private String author;
	private String isbn;
	
	public Book() {}
	
	public Book(String t, String a, String i) {
		setTitle(t);
		setAuthor(a);
		setIsbn(i);
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
