package es.uem.p2.activity;

import es.uem.p2.db.DBBookData;
import es.uem.p2.model.Book;
import es.uem.p2.util.Validation;
import es.uem.p2_roberto_marco.R;
import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class BookCreateActivity extends Activity implements OnClickListener {
	
	private TextView mTvTitle;
	private TextView mTvAuthor;
	private Button mBtAdd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.activity_book_create);
		
		initiateViewElements();
		configurateActionBar();
		
		mBtAdd.setOnClickListener(this);
	}

	private void initiateViewElements() {
		mTvTitle = (TextView) findViewById(R.id.etTitle);
		mTvAuthor = (TextView) findViewById(R.id.etAuthor);
		mBtAdd = (Button) findViewById(R.id.btAddBook);
	}

	private void configurateActionBar() {
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btAddBook:
			DBBookData mDBBook = null;
			Book book = null;
			
			if (Validation.validText(this, mTvTitle) && Validation.validText(this, mTvAuthor)) {
				book = new Book();
				book.setTitle(mTvTitle.getText().toString());
				book.setAuthor(mTvAuthor.getText().toString());
				
				mDBBook = new DBBookData(this);
				mDBBook.insert(book);		
				
				Intent intent = new Intent(this, BookShowActivity.class);
				intent.putExtra("Book", book);
				startActivity(intent);
			}
			
			break;

		default:
			break;
		}		
	}

}
