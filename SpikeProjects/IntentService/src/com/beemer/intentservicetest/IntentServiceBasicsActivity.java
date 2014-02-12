package com.beemer.intentservicetest;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class IntentServiceBasicsActivity extends Activity {
    /** Called when the activity is first created. */
    
    private ResponseReceiver receiver;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        IntentFilter filter = new IntentFilter(ResponseReceiver.ACTION_RESP);
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        receiver = new ResponseReceiver();
        registerReceiver(receiver, filter);
    }
    
    @Override
    public void onDestroy() {
        this.unregisterReceiver(receiver);
        super.onDestroy();
    }

    public void myServiceButtonClickHandler(View target) {
        // Lanzar un intent service para el trabajo asíncrono

        EditText input = (EditText) findViewById(R.id.txt_input);
        String strInputMsg = input.getText().toString();

        Intent msgIntent = new Intent(this, SimpleIntentService.class);
        msgIntent.putExtra(SimpleIntentService.PARAM_IN_MSG, strInputMsg);
        startService(msgIntent);

    }

    public void myBadButtonClickHandler(View target) {

        // Hacemos montones de trabajo en el hilo principal
        EditText input = (EditText) findViewById(R.id.txt_input);
        String strInputMsg = input.getText().toString();

        SystemClock.sleep(10000); // 10 segundos de trabajo hacen que no responda la aplicación. Usuario enfadado.

        TextView result = (TextView) findViewById(R.id.txt_result);
        result.setText(strInputMsg + " " + DateFormat.format("MM/dd/yy h:mmaa", System.currentTimeMillis()));
    }

    public class ResponseReceiver extends BroadcastReceiver {
        public static final String ACTION_RESP = "com.mamlambo.intent.action.MESSAGE_PROCESSED";
        @Override
        public void onReceive(Context context, Intent intent) {
           
            // Update UI con lo recibido desde el intent service.
           TextView result = (TextView) findViewById(R.id.txt_result);
           String text = intent.getStringExtra(SimpleIntentService.PARAM_OUT_MSG);
           result.setText(text);
        }
        
    }
    
}