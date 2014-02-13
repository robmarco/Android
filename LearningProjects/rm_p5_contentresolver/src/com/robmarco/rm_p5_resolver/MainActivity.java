package com.robmarco.rm_p5_resolver;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	public static final String KEY_ID 	= "_id";
	public static final String TITLE 	= "title";
	public static final String AUTHOR 	= "author";
	public static final String ISBN 	= "isbn";
	
	private static final String uri = "com.robmarco.contentprovider";
	public static final Uri CONTENT_URI = Uri.parse(uri);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TextView tvBooks = (TextView) findViewById(R.id.tvBooks);
		
		String[] projection = new String[] {
			KEY_ID,
			TITLE,
			AUTHOR,
			ISBN 
		};
		
		ContentResolver cr = getContentResolver();
		
		Cursor c = cr.query(CONTENT_URI, projection, null, null, null);
		
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
				
				tvBooks.setText(title + " - " + author + " - " + isbn + "\n");				
			} while (c.moveToNext());
			
			
			
			
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
