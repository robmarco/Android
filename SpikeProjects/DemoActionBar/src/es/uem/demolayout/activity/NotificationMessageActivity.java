package es.uem.demolayout.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.TextView;
import es.uem.demolayout.R;

/**
 * Activity que se lanzará cuando se pulse sobre la notificación
 * @author jyaguez
 *
 */
public class NotificationMessageActivity extends Activity {
	private static int COUNTER = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Inflo la vista desde el xml.
		setContentView(R.layout.activity_notification_message);
		
		//Habilito el botón para la navegación ancestral en el actionBar.
		//Tiene otro icono porque esta activiad no tiene NavigationDrawer. 
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		
		//Instancia del textView para mostrar el texto.
		TextView text = (TextView) findViewById(R.id.text);
		
		//de esta forma se recuperan las cadenas formateadas.
		//es como utilizar String.format().
		text.setText(getString(R.string.notification_template, COUNTER++));
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    // Controlo que se ha pulsado sobre el botón "back" (flecha hacia atrás e icono de la app)
	    case android.R.id.home:
	    	
	    	//Navego a la actividad padre.
	    	//Flujo de navegación definido en el manifest
			// <activity
			// 		android:name="es.uem.demolayout.activity.NotificationMessageActivity">
			// 		<meta-data
			// 			android:name="android.support.PARENT_ACTIVITY"
			//			android:value="es.uem.demolayout.activity.MainActivity" />
			// </activity>
	        NavUtils.navigateUpFromSameTask(this);
	        return true;
	    }
	    
	    return super.onOptionsItemSelected(item);
	}
}
