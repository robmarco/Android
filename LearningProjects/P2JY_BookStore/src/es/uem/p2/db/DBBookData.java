package es.uem.p2.db;

import java.util.ArrayList;
import java.util.List;
import es.uem.p2.model.Book;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

public class DBBookData extends DBDataSource {

	public DBBookData(Context context) {
		super(context);
	}
	
	public List<Book> getAll() {
		List<Book> listBooks = null;
		
		try {
			
			open();			
			
			String orderBy = DBHelper.KEY_TITLE + " DESC";
			
			Cursor c = getDB().query(DBHelper.TABLE_BOOKS, null, null, null, 
					null, null, orderBy);
			
			if (c.getCount() > 0) {
				listBooks = new ArrayList<Book>();				
				while(c.moveToNext()) {
					listBooks.add(CursorToBook(c));
				}
			}
			
			close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return listBooks;
	}

	public boolean insert(Book book) {
		boolean inserted = false;
		
		try {
			open();
			
			ContentValues contentValues = new ContentValues();
			contentValues.put(DBHelper.KEY_TITLE, book.getTitle());
			contentValues.put(DBHelper.KEY_AUTHOR, book.getAuthor());
			
			long id = getDB().insert(DBHelper.TABLE_BOOKS, null, contentValues);
			
			inserted = -1 != id;			
			
			close();		
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}	
		
		return inserted;
	}
	
	public boolean delete(Book book) {
		boolean deleted = false;
		
		try {
			open();
			String whereClause = DBHelper.KEY_ID + " = " + book.getId();
			deleted = getDB().delete(DBHelper.TABLE_BOOKS, whereClause, null) > 0;			
			close();			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return deleted;
	}
	
	public boolean update(Book book) {
		boolean updated = false;
		
		try {
			open();
			
			ContentValues cv = new ContentValues();
			cv.put(DBHelper.KEY_TITLE, book.getTitle());
			cv.put(DBHelper.KEY_AUTHOR, book.getAuthor());
					
			updated = getDB().update(DBHelper.TABLE_BOOKS, cv, DBHelper.KEY_ID + " = ?",
		            new String[] { String.valueOf(book.getId()) }) > 0;
		            
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return updated;
	}
	
	private Book CursorToBook(Cursor c) {
		Book book = new Book();
		
		book.setId(c.getInt(c.getColumnIndex(DBHelper.KEY_ID)));
		book.setTitle(c.getString(c.getColumnIndex(DBHelper.KEY_TITLE)));
		book.setAuthor(c.getString(c.getColumnIndex(DBHelper.KEY_AUTHOR)));		
		
		return book;
	}

}
