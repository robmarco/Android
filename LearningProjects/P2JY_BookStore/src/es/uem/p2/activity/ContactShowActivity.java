package es.uem.p2.activity;

import es.uem.p2.db.DBContactData;
import es.uem.p2.model.Contact;
import es.uem.p2_roberto_marco.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ContactShowActivity extends Activity implements OnClickListener {

	private TextView mTvName;
	private TextView mTvPhone;
	private Button mBtnEdit;
	private Button mBtnRemove;
	private Contact mContact;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact_show);
		
		initiateViewElements();
		configureActionBar();
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			mContact = extras.getParcelable("Contact");
			mTvName.setText(mContact.getName());
			mTvPhone.setText(mContact.getPhone());			
		}
		
		mBtnEdit.setOnClickListener(this);
		mBtnRemove.setOnClickListener(this);
	}

	private void initiateViewElements() {
		mTvName = (TextView) findViewById(R.id.tvNameShow);
		mTvPhone = (TextView) findViewById(R.id.tvPhoneShow);
		mBtnEdit = (Button) findViewById(R.id.btEditContact);
		mBtnRemove = (Button) findViewById(R.id.btRemoveContact);		
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
		DBContactData mDBContact = null;
		Intent intent;
		
		switch (v.getId()) {
		case R.id.btEditContact:
			intent = new Intent(this, ContactEditActivity.class);
			intent.putExtra("Contact", mContact);
			startActivity(intent);
			break;
			
		case R.id.btRemoveContact:
			mDBContact = new DBContactData(this);
			mDBContact.delete(mContact);
			intent = new Intent(this, MainActivity.class);
			intent.putExtra("Fragment", "Contact");
			startActivity(intent);
			break;

		default:
			break;
		}
		
	}

}
