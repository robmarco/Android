package es.uem.p2.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBDataSource {

	private Context mContext;
	private DBHelper mDBHelper;
	private SQLiteDatabase mDB;
	
	public DBDataSource(Context context) {
		mContext = context;
		mDBHelper = new DBHelper(mContext);
	}
	
	/**
	 * Prepara la base de datos en modo escritura
	 * @throws SQLException
	 */
	public void open() throws SQLException {
		mDB = mDBHelper.getWritableDatabase();
	}
	
	/**
	 * Cierra la base de datos
	 */
	public void close() {
		mDB = null;
		mDBHelper.close();
	}
	
	/**
	 * Devuelve la instancia a la base de datos en modo escritura
	 * @return
	 */
	public SQLiteDatabase getDB() {
		return mDB;
	}
}
