package com.beemer.intentservicetest;

import com.beemer.intentservicetest.IntentServiceBasicsActivity.ResponseReceiver;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;
import android.text.format.DateFormat;
import android.util.Log;

public class SimpleIntentService extends IntentService {
    public static final String PARAM_IN_MSG = "imsg";
    public static final String PARAM_OUT_MSG = "omsg";

    // El constructor debe ser así
    public SimpleIntentService() {
        super("SimpleIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    	// Aquí recibimos el intent que hemos mandado
        String msg = intent.getStringExtra(PARAM_IN_MSG);
        SystemClock.sleep(10000); // 10 seconds
        String resultTxt = msg + " "
            + DateFormat.format("MM/dd/yy h:mmaa", System.currentTimeMillis());
        Log.v("SimpleIntentService", "Handling msg: " + resultTxt);

        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction(ResponseReceiver.ACTION_RESP);
        broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
        broadcastIntent.putExtra(PARAM_OUT_MSG, resultTxt);
        sendBroadcast(broadcastIntent);
    }

}
