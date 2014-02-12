package com.robmarco.activity;

import com.robmarco.adapter.BookAdapter;
import com.robmarco.db.DBBookData;
import com.robmarco.model.Book;
import com.robmarco.rm_p5_contentprov.R;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;

public class MainActivity extends Activity implements OnClickListener {

	private ListView mListBooks; 
	private EditText mEtTitle;
	private EditText mEtAuthor;
	private EditText mEtIsbn;
	private BookAdapter mBookAdapter; 
	private Context mContext;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mContext = getApplicationContext();
		
		DBBookData mBookData = new DBBookData(mContext);	
		
		Button btAddBook = (Button) findViewById(R.id.btSend);
		mListBooks = (ListView) findViewById(R.id.lvBooks);
		mEtTitle = (EditText) findViewById(R.id.etTitle);
		mEtAuthor = (EditText) findViewById(R.id.etAuthor);
		mEtIsbn = (EditText) findViewById(R.id.etIsbn);
		
		btAddBook.setOnClickListener(this);
		
		mBookAdapter = new BookAdapter(this, mBookData.selectAll());
		mListBooks.setAdapter(mBookAdapter);
		
		mListBooks.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int pos,
					long id) {
				
				Intent intent = new Intent(mContext,ShowBookActivity.class);
				intent.putExtra("Book", (Book) parent.getAdapter().getItem(pos));
				startActivity(intent);				
			}
			
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.		
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main, menu);

	    // Associate searchable configuration with the SearchView
	    SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);	    
	    SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
	    searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_search:
			onSearchRequested();
			return true;
		default:
			return false;
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btSend:
			DBBookData mDBBook = new DBBookData(this);
			Book book = new Book();
			
			addBookToList(mDBBook, book);			
			clearBookForm();
			
			break;
		}
		
	}
	
	private void addBookToList(DBBookData mDBBook, Book book) {
		if (!mEtTitle.getText().toString().isEmpty() && !mEtIsbn.getText().toString().isEmpty()
				&& !mEtAuthor.getText().toString().isEmpty()) {
			
			book.setTitle(mEtTitle.getText().toString());
			book.setAuthor(mEtAuthor.getText().toString());
			book.setIsbn(mEtIsbn.getText().toString());
			
			if (mDBBook.insertBook(book))
				Toast.makeText(this, "Libro insertado correctamente", Toast.LENGTH_SHORT).show();
			
			mBookAdapter.refresh(mDBBook.selectAll());
			
		} else {
			Toast.makeText(this, "Libro no insertado", Toast.LENGTH_SHORT).show();
		}
	}

	private void clearBookForm() {
		mEtTitle.setText("");
		mEtAuthor.setText("");
		mEtIsbn.setText("");
		
	}
	
}
