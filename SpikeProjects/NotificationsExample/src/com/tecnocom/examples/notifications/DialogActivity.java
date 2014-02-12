package com.tecnocom.examples.notifications;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DialogActivity extends Activity{

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customdialog);
        
    	TextView text = (TextView) findViewById(R.id.text);
    	text.setText("Hello, this is a custom dialog!");
    	ImageView image = (ImageView) findViewById(R.id.image);
    	image.setImageResource(R.drawable.icon);

        //set up button
        Button button = (Button) findViewById(R.id.Button01);
        button.setOnClickListener(new OnClickListener() {
        @Override
            public void onClick(View v) {
                finish();
            }
        });
        
    }
}
