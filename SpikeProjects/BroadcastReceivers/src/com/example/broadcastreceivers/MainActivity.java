package com.example.broadcastreceivers;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {

	IntentReceiver intentReceiver;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		intentReceiver = new IntentReceiver();
		registerReceiver(intentReceiver, new IntentFilter(Intent.ACTION_POWER_DISCONNECTED));
		
	}
	
	@Override
	protected void onDestroy(){
		super.onDestroy();
		if (intentReceiver != null){
			unregisterReceiver(intentReceiver);
		}
	}

	class IntentReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context arg0, Intent arg1) {
			Toast.makeText(MainActivity.this, "Power Off", Toast.LENGTH_SHORT).show();		
			finish();
		}
	};

}
