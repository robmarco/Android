package es.uem.demolayout.activity;

import es.uem.demolayout.R;
import es.uem.demolayout.util.Constant;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Activity que se lanzará cuando se pulse sobre la notificación
 * @author jyaguez
 *
 */
public class NotificationMessageActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Inflo la vista desde el xml.
		setContentView(R.layout.activity_notification_message);
		
		//Recupero los extras que se han pasado en el intent
		Bundle extras = getIntent().getExtras();
		
		//No puede darse que sea null, porque obligo al usuario a introducir
		//texto, pero es una buena práctica.
		if(extras != null) {
			//Instancia del textView para mostrar el texto.
			TextView text = (TextView) findViewById(R.id.text);
			
			//de esta forma se recuperan las cadenas formateadas.
			//es como utilizar String.format().
			text.setText(getString(R.string.notification_template, 
					extras.getCharSequence(Constant.EXTRA_NAME)));
		}
	}

}
