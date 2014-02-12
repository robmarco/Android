package com.robmarco.activity;

import java.util.ArrayList;
import java.util.List;

import com.robmarco.adapter.BookAdapter;
import com.robmarco.db.DBBookData;
import com.robmarco.model.Book;
import com.robmarco.rm_p4_libros.R;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;

public class SearchActivity extends Activity {

	ListView mListSearch;
	BookAdapter mBookAdapter;
	List<Book> mListItems = null;
	TextView mTvSearch;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);		
		
		mListSearch = (ListView) findViewById(R.id.lvSearch);
		mTvSearch = (TextView) findViewById(R.id.tvSearch);
		
		mListItems = new ArrayList<Book>();		
		handleIntent(getIntent());
				
		mBookAdapter = new BookAdapter(this, mListItems);		
		mListSearch.setAdapter(mBookAdapter);
		
		mListSearch.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int pos,
					long id) {
				Intent intent = new Intent(getApplicationContext(),ShowBookActivity.class);
				intent.putExtra("Book", (Book) parent.getAdapter().getItem(pos));
				startActivity(intent);		
				
			}
			
		});
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);		
		handleIntent(intent);
	}
	
	private List<Book> handleIntent(Intent intent) {
		if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
			String query = intent.getStringExtra(SearchManager.QUERY);
			
			DBBookData mDBBookData = new DBBookData(this);
			mTvSearch.setText(query);
			mListItems = mDBBookData.selectWhere(query);			
		}
		return mListItems;
	}

}
