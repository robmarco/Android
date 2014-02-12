package es.uem.p2.adapter;

import java.util.List;

import es.uem.p2.model.Book;
import es.uem.p2_roberto_marco.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class BookAdapter extends BaseAdapter {

	private List<Book> mItems;
	private Context mContext;
	
	public BookAdapter(Context context, List<Book> items) {
		super();
		mContext = context;
		mItems = items;
	}
	
	@Override
	public int getCount() {
		return mItems == null ? 0 : mItems.size();
	}

	@Override
	public Book getItem(int pos) {
		return mItems == null ? null : mItems.get(pos);
	}

	@Override
	public long getItemId(int pos) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Book item = mItems.get(position);		
		Holder holder = null;
		
		if (convertView == null) {
			holder = new Holder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_adapter_book_item, 
					parent, false);
			
			holder.title = (TextView) convertView.findViewById(R.id.bookTitle);
			holder.author = (TextView) convertView.findViewById(R.id.bookAuthor);
			
			convertView.setTag(holder);
			
		} else {
			holder = (Holder) convertView.getTag();
		}
		
		holder.title.setText(item.getTitle());
		holder.author.setText(item.getAuthor());
		
		return convertView;		
	}
	
	class Holder {
		TextView title;
		TextView author;
	}
}
