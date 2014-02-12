package com.robertomarco.rm_practica1;

import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	private Button 	btnCC,
					btnVC,
					btnHL,
					btnLL,
					btnURL,
					btnAC,
					btnAM;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("info", "Lanzando onCreate");
		setContentView(R.layout.activity_main);
		
		initiateViewElements();
		
		btnCC.setOnClickListener(this);
		btnVC.setOnClickListener(this);
		btnHL.setOnClickListener(this);
		btnLL.setOnClickListener(this);
		btnURL.setOnClickListener(this);
		btnAC.setOnClickListener(this);
		btnAM.setOnClickListener(this);
		
	}

	private void initiateViewElements() {
		btnCC = (Button) findViewById(R.id.btCogerContact);
		btnVC = (Button) findViewById(R.id.btVerContact);
		btnHL = (Button) findViewById(R.id.btLlamar);
		btnLL = (Button) findViewById(R.id.btLogLlamadas);
		btnURL = (Button) findViewById(R.id.btAbrirURL);
		btnAC = (Button) findViewById(R.id.btAbrirActividad);
		btnAM = (Button) findViewById(R.id.btAbrirMaps);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		Log.i("info", "Lanzando onStart");
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Log.i("info", "Lanzando onResume");
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		Log.i("info", "Lanzando onPause");
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		Log.i("info", "Lanzando onStop");
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		Log.i("info", "Lanzando onRestart");
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i("info", "Lanzando onDestroy");
	}

	@Override
	public void onClick(View v) {
		Intent i;
		Uri uri;
		
		switch (v.getId()) {
		case R.id.btCogerContact:
			//Coger un contacto
			i = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
			startActivity(i); 
			break;

		case R.id.btVerContact:
			//Ver un contacto
			i = new Intent(Intent.ACTION_VIEW, ContactsContract.Contacts.CONTENT_URI);
			startActivity(i); 
			break;
		
		case R.id.btLlamar:
			//Hacer una llamada
			uri = Uri.parse("tel:9959140951");
			i = new Intent(Intent.ACTION_DIAL, uri);  
			startActivity(i); 
			break;
			
		case R.id.btLogLlamadas:
			//Ver log de llamadas
			i = new Intent(Intent.ACTION_VIEW, CallLog.Calls.CONTENT_URI);
			startActivity(i); 
			break;
			
		case R.id.btAbrirURL:
			//Abrir una URL
			uri = Uri.parse("http://www.google.com");
			i  = new Intent(Intent.ACTION_VIEW,uri);
			startActivity(i);
			break;
			
		case R.id.btAbrirActividad:
			// Abrir una segunda actividad en mi app
			startActivity(new Intent(this, OtherActivity.class));
			break;
			
		case R.id.btAbrirMaps:
			//Abrir Maps
			Intent map = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("geo: 40.4331, -3.6806525"));	
			startActivity(map);
			break;
		}
		
	}

}
