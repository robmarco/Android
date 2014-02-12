package es.uem.p2.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "p2RobertoMarco";
	
	/*
	 * Se hacen public static final las variables con algunos campos de las 
	 * tablas, puesto que luego son usadas en DBContactSource / DBBookSource.
	 * Lo ideal seria tal vez meterlas todos en una clase de DBConstants 
	 */
	
	// Nombre de las tablas (publicos para poder verlos desde DB-X-Source)
	public static final String TABLE_CONTACTS = "contacts";
	public static final String TABLE_BOOKS = "books";
	
	// Campos comunes
	public static final String KEY_ID = "id";
	public static final String KEY_CREATED_AT="created_at";
			
	// CONTACTS - Columnas de la tabla de contactos
	public static final String KEY_NAME = "name";
	public static final String KEY_PHONE = "phone";	
	
	// BOOKS - Columnas de la tabla de libros
	public static final String KEY_TITLE = "title";
	public static final String KEY_AUTHOR = "author";
	
	private static final String CREATE_TABLE_CONTACTS = "CREATE TABLE " 
			+ TABLE_CONTACTS + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ KEY_NAME + " TEXT," + KEY_PHONE + " TEXT," + KEY_CREATED_AT
			+ " DATETIME" + ")";
	
	private static final String CREATE_TABLE_BOOKS = "CREATE TABLE " 
			+ TABLE_BOOKS + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ KEY_TITLE + " TEXT," + KEY_AUTHOR + " TEXT," + KEY_CREATED_AT
			+ " DATETIME" + ")";
	
	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_CONTACTS);
		db.execSQL(CREATE_TABLE_BOOKS);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKS);
		
		// Crear de nuevo las tablas
		onCreate(db);
	}

}
