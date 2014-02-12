package es.uem.p2.activity;

import es.uem.p2.adapter.MainListAdapter;
import es.uem.p2.fragment.BookFragment;
import es.uem.p2.fragment.ContactFragment;
import es.uem.p2.fragment.LoanFragment;
import es.uem.p2.fragment.MainFragment;
import es.uem.p2_roberto_marco.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * @author Roberto Marco
 *
 */
public class MainActivity extends Activity implements OnItemClickListener {
	
	private CharSequence mTitle;		// Título del fragment que se esta visualizando
	private ListView mDrawerList; 		// ListView del menu lateral (id en activity_main.xml)
	private DrawerLayout mDrawerLayout;	// Layout del menu lateral (id en activity_main.xml)

	// Toggle para abrir/cerrar el menu lateral
	private ActionBarDrawerToggle mDrawerToggle; 	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
				
		initiateViewElements();
		configureActionBar();
				
		mDrawerList.setAdapter(new MainListAdapter(this, 
				getResources().obtainTypedArray(R.array.drawer_menu_items)));
		
		mDrawerList.setOnItemClickListener(this);
		
		int position = getIntent().getIntExtra("Fragment", -1);
		selectItem(position);
	}

	private void initiateViewElements() {
		mDrawerList = (ListView) findViewById(R.id.left_drawer);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);		
	}
	
	/* 
	 * Configuro el ActionBar - Asociamos al Drawer el boton de Inicio.
	 * Cuando se haga clic en el boton de inicio se desplegara el drawable
	 * -- > Mas info en DrawerToggle
	 * 
	 * */
	private void configureActionBar() {
		
		/* 
		 * Con el mDrawerToggle se puede modificar el contenido del ActionBar cuando
		 * el drawer se hace visible. Por ejemplo: cambiar el titulo o eliminar un 
		 * elemento del menu.
		 * 
		 */
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		
		mTitle = getTitle();
		
		mDrawerToggle = new ActionBarDrawerToggle(
				this, 
				mDrawerLayout, 
				R.drawable.ic_drawer, 
				R.string.drawer_open, 
				R.string.drawer_close
				){
						
			@Override
			public void onDrawerOpened(View drawerView) {

			}
			
			@Override
			public void onDrawerClosed(View drawerView) {
				/*
				 * Se sobrecargan los metodos para modificar el comportamiento cuando
				 * se abre el drawer y cuando se cierra.
				 * 
				 */
				getActionBar().setTitle(mTitle);
				
			}
		};
		
		// Establece el DrawerToggle como Listener del Drawer
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_action_bar, menu);
		return super.onCreateOptionsMenu(menu);
	}

	/** Metodo para que boton de home/up abra/cierre el menu lateral (drawer) */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true; 
		}
		// Aqui podria seguir atendiendo a las opciones del menu ActionBar
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sincroniza el estado del menu lateral despues de onRestoreInstanceState
		mDrawerToggle.syncState();
	}
	
	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
		MainListAdapter adapter = (MainListAdapter) adapterView.getAdapter();
		
		selectItem(adapter.getItem(position));		
	}
	
	private void selectItem(int position) {
		Fragment fragment = null;
		
		switch (position) {
				
		case R.string.book_fragment_list:
			fragment = new BookFragment();
			setTitle("Libros");
			break;
		
		case R.string.contact_fragment_list: 
			fragment = new ContactFragment();
			setTitle("Contactos");
			break;
		
		case R.string.loan_fragment_list:
			fragment = new LoanFragment();
			setTitle("Préstamos");
			break;
		
		default: 
			fragment = new MainFragment();
			break;
		}
		
		if (fragment != null) {
			FragmentTransaction transaction = getFragmentManager().beginTransaction();
			transaction.replace(R.id.content_frame, fragment);
			transaction.commit();
		}
		
		mDrawerLayout.closeDrawer(mDrawerList);		
		
	}
	
	
	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

}
