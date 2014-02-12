package es.uem.p2.activity;

import es.uem.p2.db.DBBookData;
import es.uem.p2.model.Book;
import es.uem.p2_roberto_marco.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class BookShowActivity extends Activity implements OnClickListener {
	
	private TextView mTvTitle;
	private TextView mTvAuthor;
	private Button mBtnEdit;
	private Button mBtnRemove;
	private Book mBook;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book_show);
		
		initiateViewElements();
		configurateActionBar();
		
		Bundle extra = getIntent().getExtras();
		
		if (extra != null) {
			mBook = extra.getParcelable("Book");
			
			mTvTitle.setText(mBook.getTitle());
			mTvAuthor.setText(mBook.getAuthor());
		}
		
		mBtnEdit.setOnClickListener(this);
		mBtnRemove.setOnClickListener(this);
		
	}

	private void initiateViewElements() {
		mTvTitle = (TextView) findViewById(R.id.tvTitleShow);
		mTvAuthor = (TextView) findViewById(R.id.tvAuthorShow);
		mBtnEdit = (Button) findViewById(R.id.btEditBook);
		mBtnRemove = (Button) findViewById(R.id.btRemoveBook);
	}

	private void configurateActionBar() {
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
	}

	@Override
	public void onClick(View v) {
		DBBookData mDBBook = new DBBookData(this);
		Intent intent;
		
		switch (v.getId()) {
		case R.id.btEditBook:
			intent = new Intent(this, BookEditActivity.class);
			intent.putExtra("Book", mBook);
			startActivity(intent);
			break;

		case R.id.btRemoveBook:
			mDBBook.delete(mBook);
			finish();
			intent = new Intent(this, MainActivity.class);
			intent.putExtra("Fragment", R.string.book_fragment_list);
			startActivity(intent);
			break;
			
		default:
			break;
		}
		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
				
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			Intent intent = new Intent(this, MainActivity.class);
			int position = R.string.book_fragment_list;
			intent.putExtra("Fragment", position);
			startActivity(intent);
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
}
