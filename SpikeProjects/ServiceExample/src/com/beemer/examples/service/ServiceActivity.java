package com.beemer.examples.service;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ServiceActivity extends Activity{


	
	static final int PROGRESS_DIALOG = 0;
	ProgressDialog progressDialog;	
	IntentFilter intentFilter;
	IntentFilter statusFilter;
	IDownloadService downloadService = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		intentFilter = new IntentFilter();
		intentFilter.addAction("FILE_DOWNLOADED_ACTION");
		registerReceiver(intentReceiver, intentFilter);
		
		statusFilter = new IntentFilter();
		statusFilter.addAction("FILE_STATUS_ACTION");
		registerReceiver(statusReceiver, statusFilter);
		
		Button btn_start = (Button) findViewById(R.id.btn_start);
		btn_start.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				startService(new Intent(getBaseContext(), DownloadService.class));
			}
		});
		
		Button btn_stop = (Button) findViewById(R.id.btn_stop);
		btn_stop.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				stopService(new Intent(getBaseContext(), DownloadService.class));
			}
		});
		
		Button btn_bind = (Button) findViewById(R.id.btn_bind);
		btn_bind.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				bindService(new Intent(getBaseContext(), DownloadService.class), mConnection, Context.BIND_AUTO_CREATE);
			}
		});

		Button btn_unbind = (Button) findViewById(R.id.btn_unbind);
		btn_unbind.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				unbindService(mConnection);
			}
		});

		Button btn_download = (Button) findViewById(R.id.btn_download);
		btn_download.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent i = new Intent();
				i.setAction("com.beemer.examples.service.DOWNLOAD");
				sendBroadcast(i);
				showDialog(PROGRESS_DIALOG);
			}
		});

	}
	
	private ServiceConnection mConnection = new ServiceConnection() {
	    public void onServiceConnected(ComponentName className, IBinder service) {
	        // This is called when the connection with the service has been
	        // established, giving us the service object we can use to
	        // interact with the service.  Because we have bound to a explicit
	        // service that we know is running in our own process, we can
	        // cast its IBinder to a concrete class and directly access it.
	        downloadService = IDownloadService.Stub.asInterface(service);
	        int data = 0;
	        try {
	        	data = downloadService.getData();
	        }catch (RemoteException re){}
	        // Tell the user about this for our demo.
	        Toast.makeText(ServiceActivity.this, "Binding service connectado: " +  data,
	                Toast.LENGTH_SHORT).show();
	    }

	    public void onServiceDisconnected(ComponentName className) {
	        // This is called when the connection with the service has been
	        // unexpectedly disconnected -- that is, its process crashed.
	        // Because it is running in our same process, we should never
	        // see this happen.
	        Toast.makeText(ServiceActivity.this, "Binding service desconnectado",
	                Toast.LENGTH_SHORT).show();
	    }
	};
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		this.unregisterReceiver(statusReceiver);
		this.unregisterReceiver(intentReceiver);
	}	
	
	private BroadcastReceiver intentReceiver = new BroadcastReceiver(){
		@Override
		public void onReceive(Context arg0, Intent arg1) {
			Toast.makeText(getBaseContext(), "Archivo descargado", Toast.LENGTH_LONG).show();			
            dismissDialog(PROGRESS_DIALOG);
		}
	};
	
	private BroadcastReceiver statusReceiver = new BroadcastReceiver(){
		@Override
		public void onReceive(Context arg0, Intent arg1) {
			int total = arg1.getExtras().getInt("STATUS");
			progressDialog.setProgress(total);
            if (total >= 100){
                dismissDialog(PROGRESS_DIALOG);
            }
		}
	};
	
    protected Dialog onCreateDialog(int id) {
        switch(id) {
        case PROGRESS_DIALOG:
            progressDialog = new ProgressDialog(ServiceActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setMessage("Loading...");
            return progressDialog;
        default:
            return null;
        }
    }
    
    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        switch(id) {
        case PROGRESS_DIALOG:
            progressDialog.setProgress(0);
        }
    }
    
}

