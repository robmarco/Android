package com.robmarco.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
	
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
	

	/**
	 * Implemented Methods for Parcelable
	 * @see android.os.Parcelable 
	 **/	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeString(title);
		dest.writeString(author);
		dest.writeString(isbn);
	}
	
	private Book(Parcel in) {
        id 		= in.readInt();
        title 	= in.readString();
        author 	= in.readString();
        isbn 	= in.readString();
    }
	
	public static final Parcelable.Creator<Book> CREATOR
			= new Parcelable.Creator<Book>() {
		public Book createFromParcel(Parcel in) {
			return new Book(in);
		}

		public Book[] newArray(int size) {
			return new Book[size];
		}
	};

}
