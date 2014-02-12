package es.uem.demolayout.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import es.uem.demolayout.R;
import es.uem.demolayout.adapter.AdapterMainList;

/**
 * Esta clase es la que se utiliza como launcher. Es decir la actividad
 * que se ejecuta cuando se pulsa el icono de nuestra app en la lista 
 * de aplicaciones del teléfono.
 * @author jyaguez
 *
 */
public class MainActivity extends Activity implements OnItemClickListener {
	
	/**Esto es una referencia a un listView
	 * que está en el xml asociado a esta actividad, aún sin instanciar*/
	private ListView mListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Indico que el layout será el que está en layout/activity_main. (Así se referencian
		//los recursos xlm en el código java).
		setContentView(R.layout.activity_main);
		
		//obtengo la instancia de mi listView. (Referencia por id)
		mListView = (ListView) findViewById(R.id.list);
		
		//Indico el Adapter que servirá para pintar la vista.
		mListView.setAdapter(new AdapterMainList(this, 
				getResources().obtainTypedArray(R.array.main_list_items)));
		
		//OnItemClickListener es la interfaz donde un listview indica que
		//se ha pulsado sobre uno de sus elementos. Como mi actividad
		//implementa la interfaz, paso mi referencia.
		mListView.setOnItemClickListener(this);
		
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
		//Obtengo el adapter del ListView.
		AdapterMainList adapter = (AdapterMainList) adapterView.getAdapter();
		Intent i = null;
		
		//Obtengo el identificador del recurso que corresponde a la
		//posición pulsada sobre la lista.
		switch (adapter.getItem(position)) {
		
		case R.string.linear_xml_sample:
			i = new Intent(this, LinearXMLActivity.class);
			break;
			
		case R.string.linear_code_sample:
			i = new Intent(this, LinearCodeActivity.class);
			break;
			
		case R.string.relative_code_sample:
			i = new Intent(this, RelativeCodeActivity.class);
			break;

		default:
			break;
		}
		
		
		//Si i == null, entonces no se ha pulsado sobre un elemento 
		//que quiero controlar, (no debería darse el caso)
		if(i != null) {
			
			//para poder iniciar una actividad es neceario
			//que esté declarada en el Manifest.
			startActivity(i);
		}
	}
	
}
