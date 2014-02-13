package com.robmarco.contentprovider;

import com.robmarco.db.DBHelper;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class BookProvider extends ContentProvider {

	private static final int ALLROWS = 1;
	private static final int ONEROW = 2;
	private DBHelper mDBHelper;
	
	private static final String mUri = "com.robmarco.contentprovider.test";
	public static final Uri CONTENT_URI = Uri.parse(mUri);
	private static final UriMatcher sUriMatcher;
		
	static {
		sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		sUriMatcher.addURI(mUri, "books", ALLROWS);
		sUriMatcher.addURI(mUri, "book/#", ONEROW);
	}
	
	
	@Override
	public boolean onCreate() {
		mDBHelper = new DBHelper(getContext());
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
	
		String where = selection;
		if (sUriMatcher.match(uri) == ONEROW) {
			where = DBHelper.KEY_ID + "=" + uri.getLastPathSegment();
		}
		
		SQLiteDatabase db = mDBHelper.getWritableDatabase();		
		Cursor c = db.query(DBHelper.TABLE, projection, where, selectionArgs, null, null, sortOrder);		
		return c;
	}
	
	@Override
	public String getType(Uri uri) {
	
		int match = sUriMatcher.match(uri);
		
		switch (match) {
		case ALLROWS:		
			return "vnd.android.cursor.dir/vnd.robmarco.contentprovider.book";
		case ONEROW:
			return "vnd.android.cursor.item/vnd.robmarco.contentprovider.book";
		default:
			return null;			
		}
	}

	@Override
	public
	Uri insert(Uri uri, ContentValues values) {
		
		long regId = 1;
		SQLiteDatabase db = mDBHelper.getWritableDatabase();		
		regId = db.insert(DBHelper.TABLE, null, values);		
		Uri newUri = ContentUris.withAppendedId(CONTENT_URI, regId);		
		
		return newUri;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {

		int cont;
		String where = selection;
		
		// Si es una consulta a un ID concreto, construimos el WHERE
		if (sUriMatcher.match(uri) == ONEROW) {
			where = DBHelper.KEY_ID + "=" + uri.getLastPathSegment();
		}
		
		SQLiteDatabase db = mDBHelper.getWritableDatabase();		
		cont = db.update(DBHelper.TABLE, values, where, selectionArgs);
		
		return cont;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		
		int cont;
		String where = selection;
				
		// Si es una consulta a un ID concreto, construimos el WHERE
		if (sUriMatcher.match(uri) == ONEROW) {
			where = DBHelper.TITLE + "=" + uri.getLastPathSegment();
		}
		
		SQLiteDatabase db = mDBHelper.getWritableDatabase();
		cont = db.delete(DBHelper.TABLE, where, selectionArgs);
		
		return cont;
	}


}
