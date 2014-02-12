package es.uem.p2.activity;

import es.uem.p2.db.DBBookData;
import es.uem.p2.model.Book;
import es.uem.p2.util.Validation;
import es.uem.p2_roberto_marco.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class BookEditActivity extends Activity implements OnClickListener {

	private TextView mTvTitle;
	private TextView mTvAuthor;
	private TextView mTvBookCreate;	
	private Button mBtAdd;
	private Book mBook;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book_create);
		
		initiateViewElements();		
		configureActionBar();
		
		mBtAdd.setOnClickListener(this);
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			mBook = extras.getParcelable("Book");
			configureViewElements();
		}
	}

	private void configureViewElements() {
		mTvTitle.setText(mBook.getTitle());
		mTvAuthor.setText(mBook.getAuthor());
		mTvBookCreate.setText(getResources().getString(R.string.btnEditBook));
		mBtAdd.setText(getResources().getString(R.string.btnEditBook));
	}

	private void initiateViewElements() {
		mTvTitle = (TextView) findViewById(R.id.etTitle);
		mTvAuthor = (TextView) findViewById(R.id.etAuthor);
		mTvBookCreate = (TextView) findViewById(R.id.tvBookCreate);
		mBtAdd = (Button) findViewById(R.id.btAddBook);	
	}

	private void configureActionBar() {
		getActionBar().setDisplayHomeAsUpEnabled(true);	
		getActionBar().setHomeButtonEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_action_bar, menu);
		return true;
	}

	@Override
	public void onClick(View v) {		
		
		switch (v.getId()) {
		case R.id.btAddBook:
			DBBookData mDBBook = new DBBookData(this);		
			
			if (Validation.validText(this, mTvTitle) && Validation.validText(this, mTvAuthor)) {
				mBook.setTitle(mTvTitle.getText().toString());
				mBook.setAuthor(mTvAuthor.getText().toString());
				
				mDBBook.update(mBook);
			
				Intent intent = new Intent(this, BookShowActivity.class);
				intent.putExtra("Book", mBook);
				startActivity(intent);			
			}			
			
			break;

		default:
			break;
		}
		
	}
	
}
