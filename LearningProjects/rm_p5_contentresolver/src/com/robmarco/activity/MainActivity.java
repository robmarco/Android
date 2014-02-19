package com.robmarco.activity;

import java.util.List;

import com.robmarco.model.Book;
import com.robmarco.resolver.BookResolver;
import com.robmarco.rm_p5_resolver.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TextView tvBooks = (TextView) findViewById(R.id.tvBooks);
		
		BookResolver bookResolver = new BookResolver(getContentResolver());
		List<Book> bookList = bookResolver.selectAllBooks();

		String texto = "";
		for (int i=0; i < bookList.size(); i++) {
			texto += " " + bookList.get(i).getAuthor() + "\n";
		}
		
		tvBooks.setText(texto);
		
	
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
