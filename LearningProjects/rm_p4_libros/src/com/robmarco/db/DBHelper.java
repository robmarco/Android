package com.robmarco.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	public static final String TITLE 	= "title";
	public static final String AUTHOR 	= "author";
	public static final String ISBN 	= "isbn";
	public static final String TABLE 	= "books";
	public static final String KEY_ID 	= "_id";
	
	private static final int VERSION = 1;
	private static final String DATABASE_NAME = "books.db";
	
	
	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "CREATE TABLE " + TABLE + " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
					+ TITLE + " TEXT, " + AUTHOR + " TEXT, " + ISBN +" TEXT);";
		//CREATE TABLE books (_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, author TEXT, isbn TEXT);
		db.execSQL(sql);		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE);
		//DROP TABLE IF EXISTS books
		onCreate(db);
		
	}

}
