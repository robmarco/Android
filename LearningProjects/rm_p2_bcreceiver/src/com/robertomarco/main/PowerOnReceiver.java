package com.robertomarco.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class PowerOnReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if (Intent.ACTION_POWER_CONNECTED.equals(action)) {
			Toast.makeText(context, "Power On - Cerramos app", Toast.LENGTH_SHORT).show();
		}
		
	}

	
}
