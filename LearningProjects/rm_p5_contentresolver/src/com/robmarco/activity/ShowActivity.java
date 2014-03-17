package com.robmarco.activity;

import com.robmarco.model.Book;
import com.robmarco.rm_p5_resolver.R;
import com.robmarco.rm_p5_resolver.R.layout;
import com.robmarco.rm_p5_resolver.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class ShowActivity extends Activity {

	private Book mBook; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show);
		
		TextView tvTitle = (TextView) findViewById(R.id.tvShowTitle);
		TextView tvAuthor = (TextView) findViewById(R.id.tvShowAuthor);
		TextView tvIsbn = (TextView) findViewById(R.id.tvShowIsbn);
		
		Bundle extra = new Bundle();
		extra = getIntent().getExtras();
		
		if (extra != null) {
			mBook = extra.getParcelable("Book");
			tvTitle.setText(mBook.getTitle());
			tvAuthor.setText(mBook.getAuthor());
			tvIsbn.setText(mBook.getIsbn());
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show, menu);
		return true;
	}

}
