package com.capgemini.componentes;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class Main extends Activity {
	boolean cambiar = true;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button boton = (Button)findViewById(R.id.bt_1);
        boton.setOnClickListener(oclMostrarMensaje);
        
        Button btDialogo = (Button)findViewById(R.id.bt_2);
        btDialogo.setOnClickListener(oclMostrarDialogo);
        
        registerForContextMenu(findViewById(R.id.rl_1));
        
    }
    
    OnClickListener oclMostrarMensaje = new OnClickListener(){
		@Override
		public void onClick(View arg0) {
			Toast msg = Toast.makeText(getApplicationContext(), "Muy bien, has mostrado un mensaje", Toast.LENGTH_SHORT);
			msg.show();
			
			//Ahora cambiamos la imagen por defecto
			ImageView ivImagen = (ImageView)findViewById(R.id.iv_1);
			ivImagen.setImageResource(cambiar ? R.drawable.capgemini : R.drawable.ic_launcher);
			cambiar = !cambiar;
		}
    };
    
    OnClickListener oclMostrarDialogo = new OnClickListener(){
		@Override
		public void onClick(View v) {
			EditText etEscrito = (EditText)findViewById(R.id.et_1);	
			String contenido = etEscrito.getText().toString();
			
			AlertDialog dlg = new AlertDialog.Builder(Main.this).create();
			dlg.setTitle("Pregunta");
			dlg.setMessage("Has escrito: " + contenido + ". ¿Hemos acertado?");
			dlg.setIcon(R.drawable.ic_launcher);
			dlg.setButton("Sí", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(getApplicationContext(), "¡Bien!", Toast.LENGTH_SHORT).show();
				}
			});
			dlg.show();
		}
    };
    
    //Registramos el menú
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mimenu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_salir:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
  //Contextual
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mimenu2, menu);
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.menu_mensaje:
                //editNote(info.id);
            	Toast.makeText(getApplicationContext(), "Me voy a mi casa", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}