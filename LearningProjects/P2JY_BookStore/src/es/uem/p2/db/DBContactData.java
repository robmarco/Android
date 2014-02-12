package es.uem.p2.db;

import java.util.ArrayList;
import java.util.List;

import es.uem.p2.model.Contact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

public class DBContactData extends DBDataSource {

	public DBContactData(Context context) {
		super(context);
	}
	
	/**
	 * Obtiene todos los contactos de la tabla de contactos
	 * @return El listado de contactos o null si esta vacia
	 */
	public List<Contact> getAll() {
		List<Contact> listContacts = null;
		
		try {
			// Abrir la base de datos
			open();
			
			/*	Se ejecuta la consulta
			 * 	 - Query - table, columns, selection, selectionArgs, groupBy, having, orderBy
			 * 	 - Como se van a devolver todos los registros SELECT * FROM TABLE_CONTACTS se ponen a null
			 */
			Cursor c = getDB().query(DBHelper.TABLE_CONTACTS, null, null, 
					null, null, null, null);
			
			// Si hay mas de un registro en la tabla 
			if (c.getCount() > 0) {
				listContacts = new ArrayList<Contact>();
				
				while (c.moveToNext()) {
					listContacts.add(cursorToContact(c));
				}
			}
			// Al terminar, cerrar la base de datos
			close();
		} catch (SQLException e) {
			// Si se produce una excepcion se muestra la traza
			e.printStackTrace();
		} finally {
			// Si se produce una excepcion forzamos a que se cierre la DB
			close();
		}
		
		return listContacts;
	}
	
	/**
	 * Inserta un contacto en la base de datos
	 * @param contact contacto que se quiere insertar
	 * @return false si se ha producido algun error
	 */
	public boolean insert(Contact contact) {
		boolean inserted = false;
		
		if (contact == null) return false;
		
		try {
			open();
			
			/*
			 * No hace falta asignar un identificador, en nuestra db es autoincrementado
			 * cv.put(DBHelper.KEY_ID, 0);
			 */
			ContentValues contentValues = new ContentValues();
			contentValues.put(DBHelper.KEY_NAME, contact.getName());
			contentValues.put(DBHelper.KEY_PHONE, contact.getPhone());
			
			long id = getDB().insert(DBHelper.TABLE_CONTACTS, null, contentValues);
		 
			inserted = -1 != id; // En caso de no haberse insertado, devuelve false
			
			close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return inserted;		
	}
	
	public boolean delete(Contact contact) {
		boolean deleted = false;
		
		try {
			open();
			String whereClause = DBHelper.KEY_ID + " = " + contact.getId();
			deleted = getDB().delete(DBHelper.TABLE_CONTACTS, whereClause, null) > 0;			
			close();			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return deleted;
	}
	
	public boolean update(Contact contact) {
		boolean updated = false;
		
		try {
			open();
			
			ContentValues cv = new ContentValues();
			cv.put(DBHelper.KEY_NAME, contact.getName());
			cv.put(DBHelper.KEY_PHONE, contact.getPhone());
					
			updated = getDB().update(DBHelper.TABLE_CONTACTS, cv, DBHelper.KEY_ID + " = ?",
		            new String[] { String.valueOf(contact.getId()) }) > 0;
		            
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return updated;
	}
	
	public Contact cursorToContact(Cursor c) {
		Contact contact = new Contact();
		
		contact.setId(c.getInt(c.getColumnIndex(DBHelper.KEY_ID)));
		contact.setName(c.getString(c.getColumnIndex(DBHelper.KEY_NAME)));
		contact.setPhone(c.getString(c.getColumnIndex(DBHelper.KEY_PHONE)));
				
		return contact;		
	}

}