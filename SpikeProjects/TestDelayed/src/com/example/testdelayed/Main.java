package com.example.testdelayed;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class Main extends Activity {
    private int counter = 0;
    TextView tv;
    Handler mHandler;
    Runnable twsInquiry;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        tv = (TextView) findViewById(R.id.textView1);
        mHandler = new Handler();
        twsInquiry = new Runnable() {
            @Override
            public void run() {
                tv.setText("" + counter);
                counter++;
            }
        };

    }

    public void onClickBt(View v) {
    	//Si ya había un mensaje de este handler, borra la cola de mensajes de ese handler y lanza uno nuevo
        mHandler.removeCallbacks(twsInquiry);
        mHandler.postDelayed(twsInquiry, 5000);

    }

}
