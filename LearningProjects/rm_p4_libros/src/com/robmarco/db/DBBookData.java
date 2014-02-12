package com.robmarco.db;

import java.util.ArrayList;
import java.util.List;
import com.robmarco.model.Book;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBBookData {
	
	private DBHelper mDBHelper;
	private SQLiteDatabase mDB;
	
	public DBBookData(Context context) {
		mDBHelper = new DBHelper(context);
	}
	
	public void openDB() throws SQLException {
		mDB = mDBHelper.getWritableDatabase();
	}
	
	public void closeDB() {
		mDB = null;
		mDBHelper.close();
	}
	
	public SQLiteDatabase getDB() {
		return mDB;
	}
	
	public boolean insertBook(Book book) {
		boolean inserted = false;
		
		try {
			openDB();
			
			ContentValues cv = new ContentValues();
			cv.put(DBHelper.TITLE, book.getTitle());
			cv.put(DBHelper.AUTHOR, book.getAuthor());
			cv.put(DBHelper.ISBN, book.getIsbn());
			
			long id = getDB().insert(DBHelper.TABLE, null, cv);
			
			inserted = -1 != id;
			
			closeDB();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return inserted;		
	}
	
	public boolean updateBook(Book book) {
		boolean updated = false;
		
		try {
			openDB();
			
			ContentValues cv = new ContentValues();
			cv.put(DBHelper.TITLE, book.getTitle());
			cv.put(DBHelper.AUTHOR, book.getAuthor());
			cv.put(DBHelper.ISBN, book.getIsbn());
			
			//getDB().update(table, values, whereClause, whereArgs)
			int rows = getDB().update(DBHelper.TABLE, cv, DBHelper.KEY_ID + "= ?", 
					new String[] { String.valueOf(book.getId())});
			
			updated = rows > 0;
			
			closeDB();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return updated;
		
	}
	
	public boolean deleteBook(Book book) {
		boolean deleted = false;
		
		try {
			openDB();
			
			//getDB().delete(table, whereClause, whereArgs)
			int rows = getDB().delete(DBHelper.TABLE, DBHelper.KEY_ID + "= ?", 
					new String[] {String.valueOf(book.getId())});
			
			deleted = rows > 0;
			
			closeDB();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return deleted;
	}
	
	public List<Book> selectAll() {
		List<Book> listBooks = null;

		try {
			openDB();
			String orderBy = DBHelper.KEY_ID + " DESC";
			
			//getDB().query(table, columns, selection, selectionArgs, groupBy, having, orderBy)
			Cursor c = getDB().query(DBHelper.TABLE, null, null, null, 
					null, null, orderBy);
			
			if (c.getCount() > 0) {
				listBooks = new ArrayList<Book>();				
				while(c.moveToNext()) {
					listBooks.add(cursorToBook(c));
				}
			}
			
			closeDB();			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return listBooks;
	}
	
	public List<Book> selectWhere(String query) {
		List<Book> listBooks = null;

		try {
			openDB();
			String orderBy = DBHelper.KEY_ID + " DESC";
			
			//getDB().query(table, columns, selection, selectionArgs, groupBy, having, orderBy)
			String table = DBHelper.TABLE;
			String selection = DBHelper.TITLE + " LIKE ? OR " + DBHelper.AUTHOR + " LIKE ? OR " + DBHelper.ISBN + " LIKE ?";
			String[] args = {"%" + query + "%", "%" + query + "%", "%" + query + "%" };
			
			Cursor c = getDB().query(table, null, selection, args, null, null, orderBy);
			
			if (c.getCount() > 0) {
				listBooks = new ArrayList<Book>();				
				while(c.moveToNext()) {
					listBooks.add(cursorToBook(c));
				}
			}
			
			closeDB();			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return listBooks;
	}
	
	
	private Book cursorToBook(Cursor c) {
		Book book = new Book();
		
		book.setId(c.getInt(c.getColumnIndex(DBHelper.KEY_ID)));
		book.setTitle(c.getString(c.getColumnIndex(DBHelper.TITLE)));
		book.setAuthor(c.getString(c.getColumnIndex(DBHelper.AUTHOR)));
		book.setIsbn(c.getString(c.getColumnIndex(DBHelper.ISBN)));		
		
		return book;
	}
	
}
