package com.example.practica1;


import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.app.NotificationManager;
import android.app.PendingIntent;

/*
 *	@author Roberto Marco 
 */
public class MainActivity extends Activity {
	
	public static final String EXTRA_MESSAGE = "com.example.practica1.MESSAGE";
	private EditText mTxtName;
	private Button mBtnNotification, mBtnResult;
	private TextView mTvLearn;
	private int numMsg;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mTxtName = (EditText) findViewById(R.id.txtName);
		mBtnNotification = (Button) findViewById(R.id.btnMain);
		mBtnResult = (Button) findViewById(R.id.btnNext);
		mTvLearn = (TextView) findViewById(R.id.tvLearn);
		
		mTxtName.addTextChangedListener(textWatcher);
		mBtnNotification.setOnClickListener(onClickListener);
		mBtnResult.setOnClickListener(onClickListener);
		mTvLearn.setOnClickListener(onClickListener);
	}

	private TextWatcher textWatcher = new TextWatcher() {
		
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
		}
		
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			mBtnNotification.setEnabled(!mTxtName.getText().toString().trim().isEmpty());
		}
		
		@Override
		public void afterTextChanged(Editable s) {
		}
	};
	
	private View.OnClickListener onClickListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
				
				// Lanzar Notificacion
				case R.id.btnMain:				
					
					NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MainActivity.this)
						.setSmallIcon(R.drawable.ic_launcher)
						.setContentTitle("Lanzada por " + mTxtName.getText())
						.setContentText("Número de notificaciones");
					
					Intent resultIntent = new Intent(MainActivity.this, NotificationActivity.class);
                    resultIntent.putExtra(EXTRA_MESSAGE, "" + mTxtName.getText());

                    // Crea una BackStack
                    TaskStackBuilder stackBuilder = TaskStackBuilder.create(MainActivity.this);
                    stackBuilder.addParentStack(NotificationActivity.class);
                    stackBuilder.addNextIntent(resultIntent);

                    /* 
                     * FLAG_CANCEL_CURRENT -  if the described PendingIntent already exists, the current one is canceled before generating a new one.
                     * FLAG_NO_CREATE - if the described PendingIntent does not already exist, then simply return null instead of creating it.
                     * FLAG_ONE_SHOT -  this PendingIntent can only be used once. If set, after send() is called on it, it will be automatically 
                     *					canceled for you and any future attempt to send through it will fail.
                     * FLAG_UPDATE_CURRENT - if the described PendingIntent already exists, then keep it but its replace its extra data with what 
                     * 					is in this new Intent. 
                     */
                    PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

                    mBuilder.setContentIntent(resultPendingIntent);
                    mBuilder.setAutoCancel(true);
                    mBuilder.setNumber(++numMsg);

                    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    notificationManager.notify(0, mBuilder.build());
                    
				break;
				
				// Actividad con Resultado
				case R.id.btnNext:					
					if (mTxtName.getText().toString().trim().isEmpty()) {
						mTxtName.setError(getText(R.string.ed_error));
					} else {
						Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
						intent.putExtra(EXTRA_MESSAGE, "" + mTxtName.getText());
						startActivity(intent);
					}					
				break;
				
				case R.id.tvLearn:
					Toast.makeText(getApplicationContext(), "Activities, Resources y Layouts", Toast.LENGTH_SHORT).show();
				break;
			}
			
		}
	};
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
