package com.robmarco.resolver;

import java.util.ArrayList;
import java.util.List;

import com.robmarco.model.Book;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

public class BookResolver {

	private static final String uri = "content://com.robmarco.provider.bookprovider/books";
	private static final Uri 	CONTENT_URI = Uri.parse(uri);	
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

	private Cursor query() {
		String[] projection = setProjection();
		return mContentResolver.query(CONTENT_URI, projection, null, null, null);
	}

	public List<Book> selectAllBooks() {
		
		List<Book> bookList = new ArrayList<Book>();
		Cursor c = query();
		
		if (c.moveToFirst()) {
			String title;
			String author;
			String isbn;
			
			int colTitle 	= c.getColumnIndex(TITLE);
			int colAuthor 	= c.getColumnIndex(AUTHOR);
			int colIsbn		= c.getColumnIndex(ISBN);
			
			do { 
				title 	= c.getString(colTitle);
				author 	= c.getString(colAuthor);
				isbn 	= c.getString(colIsbn);	
				
				bookList.add(new Book(title, author, isbn));				
				
			} while (c.moveToNext());	
		}
		
		return bookList;
	}
	
}
