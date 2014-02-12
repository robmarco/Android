package com.example.broadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if (Intent.ACTION_POWER_CONNECTED.equals(action)) {
			Intent iActivity = new Intent(context,MainActivity.class);
			iActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(iActivity);
			Toast.makeText(context, "Conectado", Toast.LENGTH_SHORT).show();
		}
	}
}
