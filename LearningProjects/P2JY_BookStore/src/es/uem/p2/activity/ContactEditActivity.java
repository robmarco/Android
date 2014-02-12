package es.uem.p2.activity;

import es.uem.p2.db.DBContactData;
import es.uem.p2.model.Contact;
import es.uem.p2.util.Validation;
import es.uem.p2_roberto_marco.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ContactEditActivity extends Activity implements OnClickListener {

	private TextView mTvName;
	private TextView mTvPhone;
	private TextView mTvContactCreate;	
	private Button mBtAdd;
	private Contact mContact;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact_create);
		
		initiateViewElements();
		configureActionBar();
		
		mBtAdd.setOnClickListener(this);
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			mContact = extras.getParcelable("Contact");
			configureViewElements();
		}
		
	}

	private void initiateViewElements() {
		mTvName = (TextView) findViewById(R.id.etName);
		mTvPhone = (TextView) findViewById(R.id.etPhone);
		mTvContactCreate = (TextView) findViewById(R.id.tvContactCreate);
		mBtAdd = (Button) findViewById(R.id.btAddContact);		
	}

	private void configureActionBar() {
		getActionBar().setDisplayHomeAsUpEnabled(true);	
		getActionBar().setHomeButtonEnabled(true);		
	}

	private void configureViewElements() {
		mTvName.setText(mContact.getName());
		mTvPhone.setText(mContact.getPhone());
		mTvContactCreate.setText(getResources().getString(R.string.btnEditContact));
		mBtAdd.setText(getResources().getString(R.string.btnEditContact));		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_action_bar, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btAddContact:
			DBContactData mDBContact = new DBContactData(this);
			
			if (Validation.validText(this, mTvName) && Validation.validText(this, mTvPhone)) {
				mContact.setName(mTvName.getText().toString());
				mContact.setPhone(mTvPhone.getText().toString());
				
				mDBContact.update(mContact);
			
				Intent intent = new Intent(this, ContactShowActivity.class);
				intent.putExtra("Contact", mContact);
				startActivity(intent);		
			}
			break;

		default:
			break;
		}
	}

}
