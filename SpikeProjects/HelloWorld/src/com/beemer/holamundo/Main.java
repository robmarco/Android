package com.beemer.holamundo;

import com.beemer.holamundo.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Asignamos UI
        setContentView(R.layout.main);
             
        
        Button miBoton = (Button)findViewById(R.id.bt_miboton);
        
        miBoton.setOnClickListener(oclCambiarVentana);
    }    
    
    //Mi listener
    OnClickListener oclBoton = new OnClickListener(){
		public void onClick(View v) {
			//Cuando pulso el botón me cambia el texto del botón
			((Button)v).setText("Texto cambiado");
			
			//Cuando pulso el botón me cambia la etiqueta
			TextView miTexto = (TextView)findViewById(R.id.tv_texto);
			miTexto.setText("¡Adios Murcia!");
			}
    	
    };
    
    //Listener para cambiar de Activity
    OnClickListener oclCambiarVentana = new OnClickListener(){
		public void onClick(View v) {
			//Enlazo la variable texto al EditText de la vista gráfica
			EditText texto = (EditText)findViewById(R.id.et_mensaje);
			String txt = texto.getText().toString();
			
			//Escribimos en el log lo que hemos recogido del EditText
			Log.v("HM", "Hemos recogido: " + txt);
			
			//Creo una variable tipo intent donde le indico dónde estoy
			//y a dónde quiero ir
			Intent i = new Intent(Main.this, Ventana.class);
			//Le añado a ese intent el valor de una variable
			i.putExtra("id_nombre", txt);
			//Con esta llamada pegamos el salto a la nueva Activity
			startActivity(i);
			

		}
    };
}