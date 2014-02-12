package com.robmarco.adapter;

import java.util.List;

import com.robmarco.model.Book;
import com.robmarco.rm_p4_libros.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class BookAdapter extends BaseAdapter {

	private Context 	mContext;
	private List<Book>	mListBooks;
	
	
	public BookAdapter(Context context, List<Book> listBooks) {
		super();
		mContext 	= context;
		mListBooks 	= listBooks;
	}
	
	@Override
	public int getCount() {
		return mListBooks == null ? 0 : mListBooks.size();
	}

	@Override
	public Book getItem(int pos) {		
		return mListBooks == null ? null : mListBooks.get(pos);
	}

	@Override
	public long getItemId(int pos) {		
		return pos;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Book book = getItem(position);
		Holder holder = null;
		
		if (convertView == null) {
			//We should inflate the list item
			holder = new Holder();
			
			convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
			
			holder.tvTitle 	= (TextView) convertView.findViewById(R.id.tvTitle);
			holder.tvAuthor = (TextView) convertView.findViewById(R.id.tvAuthor);
			holder.tvIsbn	= (TextView) convertView.findViewById(R.id.tvIsbn);
			
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		
		holder.tvTitle.setText(book.getTitle());
		holder.tvAuthor.setText(book.getAuthor());
		holder.tvIsbn.setText(book.getIsbn());
		
		return convertView;
	}
	
	public void refresh(List<Book> newList) {
		this.mListBooks = newList;
		notifyDataSetChanged();
	}
	
	/**
	 * 	Source: StackOverflow - 
	 * 	http://stackoverflow.com/questions/18483351/basic-information-about-holder-in-android
	 * 
	 * 	Your code might call findViewById() frequently during the scrolling of
	 * 	ListView, which can slow down performance. Even when the Adapter 
	 *	returns an inflated view for recycling, you still need to look up
	 * 	the elements and update them. A way around repeated use of findViewById()
	 * 	is to use the "view holder" design pattern.
	 * 
	 * 	A ViewHolder object stores each of the component views inside the tag field of
	 * 	the Layout, so you can immediately access them without the need to look them
	 * 	up repeatedly. First, you need to create a class to hold your exact set of views.				
	**/	
	private class Holder {
		TextView tvTitle;
		TextView tvAuthor;
		TextView tvIsbn;
	}
	
}
