package es.uem.p2.adapter;

import java.util.List;

import es.uem.p2.model.Contact;
import es.uem.p2_roberto_marco.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ContactAdapter extends BaseAdapter{

	private Context mContext;
	private List<Contact> mItems;
	
	public ContactAdapter(Context ctxt, List<Contact> items) {
		super();
		mContext = ctxt;
		mItems = items;
	}
	
	@Override
	public int getCount() {
		return mItems == null ? 0 : mItems.size();
	}

	@Override
	public Contact getItem(int position) {
		return mItems == null ? null : mItems.get(position);  
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Contact item = mItems.get(position);		
		Holder holder = null;
		
		if (convertView == null) {
			holder = new Holder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_adapter_contact_item, 
					parent, false);
			
			holder.name = (TextView) convertView.findViewById(R.id.contactName);
			holder.phone = (TextView) convertView.findViewById(R.id.contactPhone);
			
			convertView.setTag(holder);
			
		} else {
			holder = (Holder) convertView.getTag();
		}
		
		holder.name.setText(item.getName());
		holder.phone.setText(item.getPhone());
		
		return convertView;		
	}
	
	class Holder {
		TextView name;
		TextView phone;
	}
}
