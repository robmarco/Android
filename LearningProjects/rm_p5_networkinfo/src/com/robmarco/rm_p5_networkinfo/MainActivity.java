package com.robmarco.rm_p5_networkinfo;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		
		//Obtenet información de la red activa
		NetworkInfo activeNetwork = connectivity.getActiveNetworkInfo();
		int networkType = activeNetwork.getType();
		
		switch (networkType) {
			case (ConnectivityManager.TYPE_MOBILE) : break;
			case (ConnectivityManager.TYPE_WIFI) : break;
			default: break;
		}

		// Obtener información de la red móvil.
		int network = ConnectivityManager.TYPE_MOBILE;
		NetworkInfo mobileNetwork = connectivity.getNetworkInfo(network);
		NetworkInfo.State state = mobileNetwork.getState();
		NetworkInfo.DetailedState detailedState = mobileNetwork.getDetailedState();


		// Añadir un boton a la aplicación para apagar y encender la WIFI (refrescar) 
		WifiManager wifiManager = (WifiManager)this.getSystemService(Context.WIFI_SERVICE);
		wifiManager.setWifiEnabled(true);

		String estado = "Unknown";
		switch (wifiManager.getWifiState()){
			case 0: estado = " Disabling"; break;
			case 1: estado = " Disabled"; break;
			case 2: estado = " Enabling"; break;
			case 3: estado = " Enabled"; break;
		}
		
		TextView tvWifi = (TextView) findViewById(R.id.wifi);
		tvWifi.setText(estado);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
