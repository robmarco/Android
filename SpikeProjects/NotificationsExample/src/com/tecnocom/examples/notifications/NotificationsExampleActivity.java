package com.tecnocom.examples.notifications;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class NotificationsExampleActivity extends Activity implements OnClickListener{
	
	static final int DIALOG_EXIT_ID = 0;
	static final int DIALOG_COLOURS_ID = 1;
	
	private String ID;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
    }
    
    @Override
    protected void onResume() {
    	super.onResume();

    }
    
    @Override
    protected Dialog onCreateDialog(int id) {
        Dialog dialog;
        switch(id) {
        case 0:
        	dialog = createExitDialog();
            break;
        case 1:
        	dialog = createListDialog();
            break;          
        default:
            dialog = null;
        }
        
        return dialog;        
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.default_menu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
        case R.id.new_game:
        	Toast.makeText(getApplicationContext(), "A–adir", Toast.LENGTH_SHORT).show();;
            return true;
        case R.id.help:
        	Toast.makeText(getApplicationContext(), "Ayuda", Toast.LENGTH_SHORT).show();
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
    	outState.putString("ID", "1234567");
    	super.onSaveInstanceState(outState);
    }
    
   @Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		ID = savedInstanceState.getString("ID");
	}
    
    private void showCustomDialog(){
    	
        //set up dialog
        Dialog dialog = new Dialog(NotificationsExampleActivity.this);
        dialog.setContentView(R.layout.customdialog);
        dialog.setTitle("This is my custom dialog box");
        dialog.setCancelable(true);
        //there are a lot of settings, for dialog, check them all out!

    	TextView text = (TextView) dialog.findViewById(R.id.text);
    	text.setText("Hello, this is a custom dialog!");
    	ImageView image = (ImageView) dialog.findViewById(R.id.image);
    	image.setImageResource(R.drawable.icon);

        //set up button
        Button button = (Button) dialog.findViewById(R.id.Button01);
        button.setOnClickListener(new OnClickListener() {
        @Override
            public void onClick(View v) {
                finish();
            }
        });
        //now that the dialog is set up, it's time to show it    
        dialog.show();
    }
    	
    
    private Dialog createExitDialog(){
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setMessage("Are you sure you want to exit?")
    	       .setCancelable(false)
    	       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	        	   NotificationsExampleActivity.this.finish();
    	           }
    	       })
    	       .setNegativeButton("No", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	                dialog.cancel();
    	           }
    	       });
    	return builder.create();
    }
    
    
    private Dialog createListDialog(){
    	final CharSequence[] items = {"Red", "Green", "Blue"};

    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setTitle("Pick a color");
    	builder.setItems(items, new DialogInterface.OnClickListener() {
    	    public void onClick(DialogInterface dialog, int item) {
    	        Toast.makeText(getApplicationContext(), items[item], Toast.LENGTH_SHORT).show();
    	    }
    	});
    	return builder.create();
    }
    
    private void showNotification(){
    	String ns = Context.NOTIFICATION_SERVICE;
    	NotificationManager mNotificationManager = (NotificationManager) getSystemService(ns);
    	
    	int icon = R.drawable.icon;        // icon from resources
    	CharSequence tickerText = "Hello";              // ticker-text
    	long when = System.currentTimeMillis();         // notification time
    	Context context = getApplicationContext();      // application Context
    	CharSequence contentTitle = "My notification";  // message title
    	CharSequence contentText = "Hello World!";      // message text

    	Intent notificationIntent = new Intent(this, NotificationsExampleActivity.class);
    	PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

    	// the next two lines initialize the Notification, using the configurations above
    	Notification notification = new Notification(icon, tickerText, when);
    	notification.defaults |= Notification.DEFAULT_SOUND;
    	notification.defaults |= Notification.DEFAULT_LIGHTS;
    	//vibrate?
    	//notification.defaults |= Notification.DEFAULT_VIBRATE;
    	
    	notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
    	
    	mNotificationManager.notify(0, notification);
    }

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.button1:
			showDialog(DIALOG_EXIT_ID);
			break;
		case R.id.button2:
			showDialog(DIALOG_COLOURS_ID);
			break;			
		case R.id.button3:
			showCustomDialog();
			break;
		case R.id.button4:
			showNotification();
			break;		
		case R.id.button5:
			startActivity(new Intent(v.getContext(), DialogActivity.class));
		}		
	}
	
	
}