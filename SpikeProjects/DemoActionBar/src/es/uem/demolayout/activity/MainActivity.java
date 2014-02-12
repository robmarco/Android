package es.uem.demolayout.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import es.uem.demolayout.R;
import es.uem.demolayout.adapter.AdapterMainList;
import es.uem.demolayout.fragment.LinearCodeFragment;
import es.uem.demolayout.fragment.LinearXMLFragment;
import es.uem.demolayout.fragment.RelativeCodeFragment;

/**
 * Esta clase es la que se utiliza como launcher. Es decir la actividad
 * que se ejecuta cuando se pulsa el icono de nuestra app en la lista 
 * de aplicaciones del teléfono.
 * @author jyaguez
 *
 */
public class MainActivity extends Activity implements OnItemClickListener {
	
	/**Mi menú lateral es un ListView en el layout xml*/
	private ListView mListView;
	
	/**Vista que contiene (como hijos), el menú lateral y el FrameLayout*/
	private DrawerLayout mDrawerLayout;
	
	/**Toggle para abrir/cerrar el menú lateral*/
	private ActionBarDrawerToggle mDrawerToggle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Indico que el layout será el que está en layout/activity_main.
		setContentView(R.layout.activity_main);
		
		//Recupero las instancias de las vistas
		instantiateViews();
		
		//Configuro el ActionBar
		configureActionBar();
		
		//El adapter contiene la lista de objetos para el menú, en este caso son Strings
		//definidos el el fichero values/arrays
		mListView.setAdapter(new AdapterMainList(this, 
				getResources().obtainTypedArray(R.array.main_list_items)));
		
		//Controlo sobre que elemento se pulsa en el menú.
		mListView.setOnItemClickListener(this);
		
	}
	
	private void instantiateViews() {
		//obtengo la instancia de mi listView, que será el menú lateral
		//aunque podría ser cualquier otra vista
		mListView = (ListView) findViewById(R.id.menu);
		
		//instancia de la vista contenedora (Esta es la vista root de la actividad)
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
	}
	
	private void configureActionBar() {

		//Inicializo el toggle para del menú. Para ello
		// creo una clase anónima y sobreescribo algunos métdos
		//para mostrarlos, aunque no tienen código ni es obligatorio
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {
			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
				//Código que queremos que se ejecute cuando se cierre el menú
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				//Código que queremos que se ejecute cuando se abra el menú
			}
		};

		mDrawerLayout.setDrawerListener(mDrawerToggle);

		// Indicamos que queremos que se muestre el icono de la app
		// en el ActionBar y con el drawable que hemos definido
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		//Indicamos que queremos que sea pulsable (por defecto lo es para versiones
		//superiores a API 14)
		getActionBar().setHomeButtonEnabled(true);
	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// sincroniza el estado del toggle
		mDrawerToggle.syncState();
	}

	@Override
	//A este métdo se le llama, cuando se cambia la orientación de la pantalla
	//en la atividad.
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		//notificamos que se ha producido un cambio en la configuración
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	//Método al que se llama cuando se pulsa un botón del ActionBar
	public boolean onOptionsItemSelected(MenuItem item) {
		// Paso el evento al toggle del ActionBar, si devuelve true
		//es que se ha pulsado sobre su icono y enonces se abre el menú lateral.
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		
		//Si el botón sobre el que se ha pulsado no es el toggle
		//entonces tendríamos que controlarlo aquí
		
		//Son los elementos que se han inflado al menú del action bar
		//en el método onCreateOptionsMenu
		
		//Pare este ejemplo controlamos que se pulse sobre el botón de 
		//settings.
		switch (item.getItemId()) {
		case R.id.action_settings:
			
			//Muestra un texto flotante en la pantalla, este tipo de elemento
			//se llama toast.
			Toast.makeText(this, "Has pulsado sobre settings", Toast.LENGTH_LONG).show();
			break;

		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	//Se llama para inflar los botones del action bar
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		
		//referencia al fichero menu/main.xml
	    inflater.inflate(R.menu.main, menu);
	    
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
		//Obtengo el adapter del ListView.
		AdapterMainList adapter = (AdapterMainList) adapterView.getAdapter();
		Fragment content = null;
		
		//Obtengo el identificador del recurso que corresponde a la
		//posición pulsada sobre la lista.
		switch (adapter.getItem(position)) {
		
		case R.string.linear_xml_sample:
			content = new LinearXMLFragment();
			break;
			
		case R.string.linear_code_sample:
			content = new LinearCodeFragment();
			break;
			
		case R.string.relative_code_sample:
			content = new RelativeCodeFragment();
			break;

		default:
			break;
		}
		
		
		//Si i == null, entonces no se ha pulsado sobre un elemento 
		//que quiero controlar, (no debería darse el caso)
		if(content != null) {
			
			//Cuando he pulsado sobre el elemento que quiero controlar, reemplazo
			//el contenido de la actividad
			FragmentTransaction ft = getFragmentManager().beginTransaction();
			ft.replace(R.id.content_frame, content);
			
			//Confirmo la accion. (Si no llamo a commit, no se hace nada)
			ft.commit();
		}
		
		//Cierro el menú.
		mDrawerLayout.closeDrawer(mListView);
	}
	
	
}
