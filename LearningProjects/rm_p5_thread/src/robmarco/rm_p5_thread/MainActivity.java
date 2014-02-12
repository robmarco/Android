package robmarco.rm_p5_thread;

import android.os.Bundle;
//import android.os.Handler;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	//private Handler handler = new Handler();
	private TextView tvHelloWorld;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tvHelloWorld = (TextView) findViewById(R.id.hello_world);
		mainProcessing();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		return true;
	}
	
	private	 void mainProcessing() {
		Thread thread = new Thread(myRunnable, "Background");	
		thread.start();
	}
	
	private Runnable myRunnable = new Runnable () {
		public void run() {
			backgroundThreadProcessing();
		}

		private void backgroundThreadProcessing() {
			tvHelloWorld.setText("¿Por qué falla esto?");
			// probar RunOnUIThread()
			//handler.post(Runnable);
		}
	};

}
