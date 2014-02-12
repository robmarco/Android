package com.beemer.examples.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class DownloadService extends Service {
	String download = "http://madrid.universidadeuropea.es/system/resources/W1siZiIsIjIwMTMvMDYvMDEvMTBfMjZfMjJfNDM5X2ZvbGxldG9fYXl1ZGFzX1VFX09fQkFKQS5wZGYiXV0/folleto_ayudas_UE_O_BAJA.pdf";
	// This is the object that receives interactions from clients. See
	// RemoteService for a more complete example.

	IBinder downloadServiceStub = new IDownloadService.Stub() {
		public int getData() {
			return 666;
		}
	};


	@Override
	public IBinder onBind(Intent arg0) {
		return downloadServiceStub;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Toast.makeText(this, "Servicio iniciado", Toast.LENGTH_LONG).show();
		IntentFilter filter;
		filter = new IntentFilter("com.beemer.examples.service.DOWNLOAD");
		this.registerReceiver(mServiceReceiver, filter);

		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		Toast.makeText(this, "Servicio finalizado", Toast.LENGTH_LONG).show();
		try {
			unregisterReceiver(mServiceReceiver);
		} catch (Exception e) {
		}// Para el caso de que el receiver no este registrado. Servicio
			// arrancado con bind
		super.onDestroy();
	}

	// The BroadcastReceivers that listens for events
	private final BroadcastReceiver mServiceReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if ("com.beemer.examples.service.DOWNLOAD".equals(action)) {
				try {
					new BackgroundDownloadTask().execute(new URL(download));
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}
		}
	};

	private class BackgroundDownloadTask extends
			AsyncTask<URL, Integer, Integer> {

		@Override
		//URL... es un array de URL
		protected Integer doInBackground(URL... urls) {

			try {

				URL url = urls[0]; // you can write here any link

				// set the path where we want to save the file
				// in this case, going to save it on the root directory of the
				// sd card.
				File SDCardRoot = Environment.getExternalStorageDirectory();
				// create a new file, specifying the path, and the filename
				// which we want to save the file as.
				File file = new File(SDCardRoot, "ServiceExample.pdf");

				long startTime = System.currentTimeMillis();
				Log.d("DownloadManager", "download begining");
				Log.d("DownloadManager", "download url:" + url);
				Log.d("DownloadManager", "downloaded file name:" + file);

				// create the new connection
				HttpURLConnection urlConnection = (HttpURLConnection) url
						.openConnection();

				// set up some things on the connection
				urlConnection.setRequestMethod("GET");
				urlConnection.setDoOutput(true);

				// and connect!
				urlConnection.connect();

				// this will be used to write the downloaded data into the file
				// we created
				FileOutputStream fileOutput = new FileOutputStream(file);

				// this will be used in reading the data from the internet
				InputStream inputStream = urlConnection.getInputStream();

				// this is the total size of the file
				int totalSize = urlConnection.getContentLength();
				// variable to store total downloaded bytes
				int downloadedSize = 0;

				// create a buffer...
				byte[] buffer = new byte[1024];
				int bufferLength = 0; // used to store a temporary size of the
										// buffer

				// now, read through the input buffer and write the contents to
				// the file
				while ((bufferLength = inputStream.read(buffer)) > 0) {
					// add the data in the buffer to the file in the file output
					// stream (the file on the sd card
					fileOutput.write(buffer, 0, bufferLength);
					// add up the size so we know how much is downloaded
					downloadedSize += bufferLength;
					// this is where you would do something to report the
					// prgress, like this maybe
					publishProgress(downloadedSize, totalSize);
					// updateProgress(downloadedSize, totalSize);

				}
				// close the output stream when done
				fileOutput.close();

				Log.d("DownloadManager",
						"download ready in"
								+ ((System.currentTimeMillis() - startTime) / 1000)
								+ " sec");

				return downloadedSize;

			} catch (IOException e) {
				Log.d("DownloadManager", "Error: " + e);
			}

			return null;
		}

		protected void onProgressUpdate(Integer... progress) {
			Log.d("DownloadManager",
					(int) ((progress[0] / (float) progress[1]) * 100)
							+ "% descargado");

			Intent statusIntent = new Intent();
			statusIntent.setAction("FILE_STATUS_ACTION");
			statusIntent.putExtra("STATUS",
					(int) ((progress[0] / (float) progress[1]) * 100));
			getBaseContext().sendBroadcast(statusIntent);
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

		protected void onPostExecute(Integer result) {
			Log.d("DownloadManager", "Archivo descargado");
			Toast.makeText(getBaseContext(), "Archivo descargado",
					Toast.LENGTH_LONG).show();

			Intent broadcastIntent = new Intent();
			broadcastIntent.setAction("FILE_DOWNLOADED_ACTION");
			getBaseContext().sendBroadcast(broadcastIntent);
		}

	}



}
