package com.robmarco.resolver;

import com.robmarco.model.Book;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;

public class BookResolver {

	public static final String URI = "content://com.robmarco.provider.bookprovider/books";
	private static final Uri 	CONTENT_URI = Uri.parse(URI);	
	private static final String KEY_ID 	= "_id";
	private static final String TITLE 	= "title";
	private static final String AUTHOR 	= "author";
	private static final String ISBN 	= "isbn";
	
	private ContentResolver mContentResolver;
	
	public BookResolver(ContentResolver cr) {	
		this.mContentResolver = cr;
	}

	private String[] setProjection() {
		String[] projection = new String[] {
				KEY_ID,
				TITLE,
				AUTHOR,
				ISBN 
		};
		return projection;
	}

	public Cursor query() {
		String[] projection = setProjection();
		Cursor c = mContentResolver.query(CONTENT_URI, projection, null, null, null);
		return c;
		
	}
	
	public Cursor queryID(int id) {
		String[] projection = setProjection();
		Uri uri = ContentUris.withAppendedId(CONTENT_URI, id);
		Cursor c = mContentResolver.query(uri, projection, null, null, null);		
		return c;		
	}

	public Book selectBook(Cursor c) {
		Book book = new Book();
		
		if (c == null || c.getCount() != 1) {
			return null;
		}
		
		c.moveToFirst();
		int colId 		= c.getColumnIndex(KEY_ID);
		int colTitle 	= c.getColumnIndex(TITLE);
		int colAuthor 	= c.getColumnIndex(AUTHOR);
		int colIsbn		= c.getColumnIndex(ISBN);
		
		int id = c.getInt(colId);
		String title = c.getString(colTitle);;
		String author = c.getString(colAuthor);;
		String isbn = c.getString(colIsbn);
		
		book.setId(id);
		book.setTitle(title);
		book.setAuthor(author);
		book.setIsbn(isbn);
		
		c.close();
		
		return book;
	}
	
}
