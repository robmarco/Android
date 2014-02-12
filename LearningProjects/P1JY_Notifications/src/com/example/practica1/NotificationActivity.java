package com.example.practica1;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/*
 *	@author Roberto Marco 
 */

public class NotificationActivity extends Activity {
	
	private Button mBtnAgain;
	private TextView mTxtResult;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notification);
		
		mBtnAgain = (Button) findViewById(R.id.btnAgain);
		mBtnAgain.setOnClickListener(onClickListener);
		
		Bundle extras = getIntent().getExtras();
		
		if (extras != null ) {
			mTxtResult = (TextView) findViewById(R.id.txtResult);
			mTxtResult.setText(extras.getString(MainActivity.EXTRA_MESSAGE));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.notification, menu);
		return true;
	}
	
	private View.OnClickListener onClickListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			switch (v.getId()) {
			case R.id.btnAgain:
				if (!mTxtResult.getText().toString().isEmpty()) {
					
					Intent intent = new Intent(getApplicationContext(), NotificationActivity.class);
					
					if (!mTxtResult.getText().toString().isEmpty()) {
						intent.putExtra(MainActivity.EXTRA_MESSAGE, "" + mTxtResult.getText());
					}
					
					startActivity(intent);					
				}				
			}
			
		}
	};

}
