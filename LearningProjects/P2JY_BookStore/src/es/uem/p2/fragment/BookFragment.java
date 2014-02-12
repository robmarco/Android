package es.uem.p2.fragment;

import es.uem.p2.activity.BookCreateActivity;
import es.uem.p2.activity.BookShowActivity;
import es.uem.p2.adapter.BookAdapter;
import es.uem.p2.db.DBBookData;
import es.uem.p2.model.Book;
import es.uem.p2_roberto_marco.R;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class BookFragment extends AbstractFragment {
	
	private Context mContext;
	private ListView mList;
	private DBBookData mDBBookData;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
	
		View rootView = inflater.inflate(R.layout.book_fragment, container, false);
		setHasOptionsMenu(true);

		mContext = rootView.getContext();
		mList = (ListView) rootView.findViewById(R.id.bookList);
		
		// Antes de llamar al adaptador, primero deberia comprobar que la lista de elementos
		// no fuese nula. En caso de ser nula, cargaria una lista vacia o vista diferente (sin elementos)
		mDBBookData = new DBBookData(mContext);
		
		mList.setAdapter(new BookAdapter(mContext, mDBBookData.getAll()));
		
		mList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				
				Intent intent = new Intent(mContext, BookShowActivity.class);
				intent.putExtra("Book", ((Book) parent.getAdapter().getItem(position)));
				startActivity(intent);
				
			}
		});
		
		return rootView;
	}
	
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.drawer_list_menu, menu);	
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.book_add:
			Intent intent = new Intent(mContext,BookCreateActivity.class);
			startActivity(intent);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}		
	}
	
	
		
}
