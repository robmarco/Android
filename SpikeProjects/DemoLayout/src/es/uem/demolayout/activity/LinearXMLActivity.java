package es.uem.demolayout.activity;

import android.os.Bundle;
import android.widget.EditText;
import es.uem.demolayout.R;

/**
 * Ejemplo de una actividad cuyo layout está definido en el fichero
 * xml layout/activity_linear_xml.xml
 * @author jyaguez
 *
 */
public class LinearXMLActivity extends InputNameActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_linear_xml);
		
		
	}

	@Override
	protected EditText getEditTextName() {
		return (EditText) findViewById(R.id.name);
	}

}
