package com.robmarco.activity;

import com.robmarco.model.Book;
import com.robmarco.resolver.BookResolver;
import com.robmarco.rm_p5_resolver.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private ListView mListBook;
	private Context mContext;
	private BookResolver bookResolver;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mContext = getApplicationContext();
		String[] columnsBook = new String[] { "title", "author", "isbn" };
		int[] templateBindColumns = new int[] {R.id.tvTitle, R.id.tvAuthor, R.id.tvIsbn};
		
		mListBook = (ListView) findViewById(R.id.lvBooks);
		
		bookResolver = new BookResolver(getContentResolver());
		Cursor cursorBook = bookResolver.query();
				
		ListAdapter adapter = new SimpleCursorAdapter(this, R.layout.two_line_list_item, 
				cursorBook, columnsBook, templateBindColumns);
		
		mListBook.setAdapter(adapter);
		
		mListBook.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int pos,
					long id) {
				
				try {
					Cursor cursorBook = bookResolver.queryID(pos);
					Book book = bookResolver.selectBook(cursorBook);
					
					Intent intent = new Intent(mContext, ShowActivity.class);
					intent.putExtra("Book", book);
					startActivity(intent);
				} catch (Exception e) {
					Log.e("ContentReceiver", e.getMessage());					
				}		
				
				
			}
			
		});
		
	}	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
