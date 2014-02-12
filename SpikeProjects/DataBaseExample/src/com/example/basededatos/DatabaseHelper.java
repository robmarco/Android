package com.example.basededatos;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	public static final String TITLE = "title";
	public static final String AUTHOR = "author";
	public static final String ISBN = "isbn";
	
	private static final String DATABASE_NAME = "library.db";
	private static final String TABLE = "books";
	
	public DatabaseHelper(Context context){
		//public SQLiteOpenHelper (Context context, String name, SQLiteDatabase.CursorFactory factory, int version) 
		super(context, DATABASE_NAME, null, 1);
	}
	
	//Create database
	@Override
	public void onCreate(SQLiteDatabase db){
		String sql = "CREATE TABLE " + TABLE + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " + TITLE + " TEXT, " + AUTHOR + " TEXT, " + ISBN +" TEXT);";
		//CREATE TABLE books (_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, author TEXT, isbn TEXT);
		db.execSQL(sql);
	}
	
	//Called on version upgrade
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
		db.execSQL("DROP TABLE IF EXISTS " + TABLE);
		//DROP TABLE IF EXISTS books
		onCreate(db);
	}
	
	public void insertBook(SQLiteDatabase db, String title, String author, String isbn){
		ContentValues cv = new ContentValues();
		cv.put(TITLE, title);
		cv.put(AUTHOR, author);
		cv.put(ISBN, isbn);

		db.insert(TABLE, TITLE, cv);
	}
	
	public void updateBook(SQLiteDatabase db, String title, String author, String isbn, int key){
		ContentValues cv = new ContentValues();
		cv.put(TITLE, title);
		cv.put(AUTHOR, author);
		cv.put(ISBN, isbn);
		
		String whereClause = "";
		String[] whereArgs;
		
		if(key == 0){
			whereClause = TITLE + "=?";
			whereArgs = new String[]{title};
		}else if(key == 1){
			whereClause = AUTHOR + "=?";
			whereArgs = new String[]{author};
		}else{
			whereClause = ISBN + "=?";
			whereArgs = new String[]{isbn};
		}
		
		db.update(TABLE, cv, whereClause, whereArgs);
	}
	
	public void deleteBook(SQLiteDatabase db, String valor, int key){
		String whereClause = "";
		String[] whereArgs = new String[]{valor};
		
		if(key == 0){
			whereClause = TITLE + "=?";
		}else if(key == 1){
			whereClause = AUTHOR + "=?";
		}else{
			whereClause = ISBN + "=?";
		}
		
		db.delete(TABLE, whereClause, whereArgs);
	}
	
	public void deleteDataBase(SQLiteDatabase db){
		db.delete(TABLE, null, null);
	}
	
	public ArrayList<String[]> selectAll(SQLiteDatabase db){
		ArrayList<String[]> tmp = new ArrayList<String[]>();
		
		String[] columnas = new String[]{
				TITLE,
				AUTHOR,
				ISBN
		};
		
		Cursor c;
		c = db.query(TABLE, columnas, null, null, null, null, null);
		
		if(c.moveToFirst()){
			do{
				String[] libro = new String[3];
				libro[0] = c.getString(c.getColumnIndex(TITLE));
				libro[1] = c.getString(c.getColumnIndex(AUTHOR));
				libro[2] = c.getString(c.getColumnIndex(ISBN));
				
				tmp.add(libro);
			}while(c.moveToNext());
		}
		return tmp;
	}
	
	
	
}
