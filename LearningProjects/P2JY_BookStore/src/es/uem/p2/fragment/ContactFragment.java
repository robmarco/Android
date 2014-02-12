package es.uem.p2.fragment;

import es.uem.p2.activity.ContactCreateActivity;
import es.uem.p2.activity.ContactShowActivity;
import es.uem.p2.adapter.ContactAdapter;
import es.uem.p2.db.DBContactData;
import es.uem.p2.model.Contact;
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

public class ContactFragment extends AbstractFragment {

	private ListView mList;
	private Context mContext;
	private DBContactData mDBBookData;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
	
		View rootView = inflater.inflate(R.layout.contact_fragment, container, false);	
		setHasOptionsMenu(true);
		
		mContext = rootView.getContext();
		
		mList = (ListView) rootView.findViewById(R.id.contactList);
		
		// Antes de llamar al adaptador, primero deberia comprobar que la lista de elementos
		// no fuese nula. En caso de ser nula, cargaria una lista vacia o vista diferente (sin elementos)		
		mDBBookData = new DBContactData(mContext);
		mList.setAdapter(new ContactAdapter(mContext, mDBBookData.getAll()));
		
		mList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				Intent intent = new Intent(mContext, ContactShowActivity.class);
				intent.putExtra("Contact", ((Contact)parent.getAdapter().getItem(position)));
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
			startActivity(new Intent(mContext, ContactCreateActivity.class));
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
		
	}
	
}
