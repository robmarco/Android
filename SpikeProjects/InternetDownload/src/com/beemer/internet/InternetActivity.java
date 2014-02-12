package com.beemer.internet;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ImageView;
import android.widget.Toast;
public class InternetActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);

			//
//		//Obtenet informaci�n de la red activa
//		ConnectivityManager connectivity = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
//		NetworkInfo activeNetwork = connectivity.getActiveNetworkInfo();
//		int networkType = activeNetwork.getType();
//		switch (networkType) {
//			case (ConnectivityManager.TYPE_MOBILE) : break;
//			case (ConnectivityManager.TYPE_WIFI) : break;
//			default: break;
//		}
//
//		// Obtener informaci�n de la red m�vil.
//		int network = ConnectivityManager.TYPE_MOBILE;
//		NetworkInfo mobileNetwork = connectivity.getNetworkInfo(network);
//		NetworkInfo.State state = mobileNetwork.getState();
//		NetworkInfo.DetailedState detailedState = mobileNetwork.getDetailedState();
//

	
		//---download an image---
		//Lo suyo es que este download se haga en un asynctask
		Bitmap bitmap =	DownloadImage("http://madrid.universidadeuropea.es/assets/ue-site-logo-ccc96523735dea3d671229197899a27d.png");
		ImageView img = (ImageView) findViewById(R.id.img); 
		img.setImageBitmap(bitmap);
	}

	private InputStream OpenHttpConnection(String urlString) throws IOException {

		InputStream in = null;
		int response = -1;
		URL url = new URL(urlString);
		URLConnection conn = url.openConnection();

		
		// Esta manera de hacerla no seria totalmente la adecuada 
		// El if puede ser innecesario, porque da igual que sea HTTP y HTTPS
		// Si la peticion entra, pues entra
		if (!(conn instanceof HttpURLConnection))
			throw new IOException("Not an HTTP connection");

		try {
			HttpURLConnection httpConn = (HttpURLConnection) conn;
			httpConn.setAllowUserInteraction(false);
			httpConn.setInstanceFollowRedirects(true);
			httpConn.setRequestMethod("GET");
			httpConn.connect();
			response = httpConn.getResponseCode();
			if (response == HttpURLConnection.HTTP_OK) {
				in = httpConn.getInputStream();
			}
		} catch (Exception ex) {
			throw new IOException("Error connecting");
		}
		return in;
	}

	private Bitmap DownloadImage(String URL) {
		Bitmap bitmap = null;
		InputStream in = null;
		try {
			in = OpenHttpConnection(URL);
			bitmap = BitmapFactory.decodeStream(in);
			in.close();
		} catch (IOException e1) {
			Toast.makeText(this, e1.getLocalizedMessage(), Toast.LENGTH_LONG)
					.show();
			e1.printStackTrace();
		}
		return bitmap;
	}

}