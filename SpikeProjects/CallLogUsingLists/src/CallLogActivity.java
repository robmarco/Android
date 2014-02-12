import java.util.ArrayList;

import com.beemer.callog.R;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog.Calls;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class CallLogActivity extends Activity {
	private CallAdapter 					mCallsArrayAdapter;
	private ListView 						mCallListView;
	private Cursor							mCursor;
	private int 							mSize;
	private ArrayList<String>				mRowName= new ArrayList<String>();
	private ArrayList<String>				mRowNumber= new ArrayList<String>();
	private ArrayList<Integer>				mRowType= new ArrayList<Integer>();
	private ArrayList<String>				mRowTime= new ArrayList<String>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);

		mCallsArrayAdapter = new CallAdapter(this);

		mCursor = getContentResolver().query(Calls.CONTENT_URI,new String[] {Calls.CACHED_NAME, Calls.NUMBER, Calls.TYPE, Calls.DATE},
					null, null, "date DESC");

		// Find and set up the ListView for Sets
		mCallListView = (ListView) findViewById(R.id.list);
		mCallListView.setAdapter(mCallsArrayAdapter);

		mSize= mCursor.getCount();
    	if (mSize > 0){
    		int nameIndex = mCursor.getColumnIndex(Calls.CACHED_NAME);
    		int numberIndex = mCursor.getColumnIndex(Calls.NUMBER);
    		int typeIndex = mCursor.getColumnIndex(Calls.TYPE);
    		int dateIndex = mCursor.getColumnIndex(Calls.DATE);
    		mCursor.moveToFirst();
	    	do {
	    		mCallsArrayAdapter.add("");
	    		String s = mCursor.getString(nameIndex);
	    		if (s == null) s = "";
	    		mRowName.add(s);
	    		
	    		s = mCursor.getString(numberIndex);
	    		if (s == null) s = "";	    		
	    		mRowNumber.add(s);
	    		
	    		mRowType.add(mCursor.getInt(typeIndex));
	    		
	    		long callDate = mCursor.getLong(dateIndex);
	    		s = DateFormat.getDateFormat(this).format(callDate) + " " +
	    			DateFormat.getTimeFormat(this).format(callDate);
	    		mRowTime.add(s);
	    		
	    	}while(mCursor.moveToNext());
   		}
		if (mCursor != null)
			mCursor.close();
	}
		
    private class CallAdapter extends ArrayAdapter<String>{
        public CallAdapter(Context context) {
            super(context, R.layout.list_item);
        }
    	public View getView(int position, View convertView,ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater layoutInflater = getLayoutInflater();
                convertView = layoutInflater.inflate(R.layout.list_item, parent, false);
            }
            
            
            //ConvertView es el layout o la vista de esa fila en concreto            
    		TextView label1=(TextView)convertView.findViewById(R.id.label1);
    		TextView label2=(TextView)convertView.findViewById(R.id.label2);
       	 	ImageView icon=(ImageView)convertView.findViewById(R.id.icon);
       	 	//TYPE: 1 Accepted or incoming, 2 Outgoing, 3 rejected or missed
    		switch (mRowType.get(position)){
	    		case 1: icon.setImageResource(android.R.drawable.sym_call_incoming);  break;
	    		case 2: icon.setImageResource(android.R.drawable.sym_call_outgoing);  break;
	    		case 3: icon.setImageResource(android.R.drawable.sym_call_missed);  break;
	    		default: icon.setImageResource(android.R.drawable.sym_call_incoming);  break;
    		}

			String name = mRowName.get(position);
			if (!name.equals("")){
				label1.setText(name);
				label2.setText(mRowNumber.get(position));
			}
			else{
				label1.setText(mRowNumber.get(position));
				label2.setText("");
			}

			
			return(convertView);
    	}
	};
    
	
}

