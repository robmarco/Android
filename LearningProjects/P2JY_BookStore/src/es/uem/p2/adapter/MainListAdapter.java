package es.uem.p2.adapter;

import es.uem.p2_roberto_marco.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MainListAdapter extends BaseAdapter {

	private TypedArray mItems;
	private Context mContext;
	
	public MainListAdapter(Context ct, TypedArray items) {
		super();
		mContext = ct;
		mItems = items;
	}
	
	/** Devuelve el numero de elementos que se mostraran en la lista */
	@Override
	public int getCount() {		
		return mItems == null ? 0 : mItems.length() ;
	}

	/** Devolvera el identificador del recurso al que hacer referencia en la lista 
	 *  	Por ejemplo: R.string.book_fragment */
	@Override
	public Integer getItem(int idx) {
		
		return mItems == null ? -1 : mItems.getResourceId(idx, 0);
	}

	@Override
	public long getItemId(int id) {		
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		String item = mItems.getString(position);
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).
					inflate(R.layout.drawer_list_item, parent, false);
		}
		
		TextView text = (TextView) convertView.findViewById(R.id.tvLeftDrawer);
		text.setText(item);
		
		return convertView;
	}

}
