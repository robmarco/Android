package com.robmarco.activity;

import com.robmarco.db.DBBookData;
import com.robmarco.model.Book;
import com.robmarco.rm_p4_libros.R;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;

public class ShowBookActivity extends Activity implements OnClickListener {

	Book mBook;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_showbook);
		
		TextView tvShowTitle	= (TextView) findViewById(R.id.tvShowTitle);
		TextView tvShowAuthor 	= (TextView) findViewById(R.id.tvShowAuthor);
		TextView tvShowIsbn 	= (TextView) findViewById(R.id.tvShowIsbn);
		Button	 btRemove		= (Button) 	 findViewById(R.id.btRemove);
		
		Bundle extra = new Bundle();
		extra = getIntent().getExtras();
		
		if (extra != null) {
			mBook = extra.getParcelable("Book");
			tvShowTitle.setText(mBook.getTitle());
			tvShowAuthor.setText(mBook.getAuthor());
			tvShowIsbn.setText(mBook.getIsbn());
			
			btRemove.setOnClickListener(this);
		}
		
	}

	@Override
	public void onClick(View v) {
		Intent intent;
		
		switch (v.getId()) {
		case R.id.btRemove:			
			DBBookData mDBBookData = new DBBookData(this);
			mDBBookData.deleteBook(mBook);
			intent = new Intent(this, MainActivity.class);
			intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);			
			startActivity(intent);
			break;
		}
		
	}

}
