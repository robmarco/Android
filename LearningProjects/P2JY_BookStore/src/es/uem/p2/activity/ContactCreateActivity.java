package es.uem.p2.activity;

import es.uem.p2.db.DBContactData;
import es.uem.p2.model.Contact;
import es.uem.p2.util.Validation;
import es.uem.p2_roberto_marco.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ContactCreateActivity extends Activity implements OnClickListener {

	private TextView mTvName;
	private TextView mTvPhone;
	private Button mBtnAdd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact_create);
		
		initiateViewElements();
		mBtnAdd.setOnClickListener(this);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);		
	}

	private void initiateViewElements() {
		mTvName = (TextView) findViewById(R.id.etName);
		mTvPhone = (TextView) findViewById(R.id.etPhone);
		mBtnAdd = (Button) findViewById(R.id.btAddContact);		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btAddContact:
			DBContactData mDBContact = null;
			Contact contact = null;
			
			if (Validation.validText(this, mTvName) && Validation.validText(this, mTvPhone)) {
				contact = new Contact();
				contact.setName(mTvName.getText().toString());
				contact.setPhone(mTvPhone.getText().toString());
				
				mDBContact = new DBContactData(this);
				mDBContact.insert(contact);
				
				Intent intent = new Intent(this, ContactShowActivity.class);
				intent.putExtra("Contact", contact);
				startActivity(intent);
			}
			
			break;

		default:
			break;
		}
	}
}
