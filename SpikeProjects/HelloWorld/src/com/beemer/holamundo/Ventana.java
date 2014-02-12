package com.beemer.holamundo;

import com.beemer.holamundo.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Ventana extends Activity {
	//FUNCIÓN PARA CREAR ACTIVITY/VENTANA
	@Override
	public void onCreate(Bundle savedInstance){
		super.onCreate(savedInstance);
		
		//Cargar parte gráfica
		setContentView(R.layout.ventana);
		
		//Pulsamos Ctrl + Shift + o para importar de forma automática
		TextView muestratexto = (TextView)findViewById(R.id.tv_mostrarnombre);
		
		//Recuperamos el intent asociado a esta Activity
		Intent i = getIntent();
		//Guardamos en la variable txt el valor del objeto asociado a "id_nombre"
		String txt = i.getStringExtra("id_nombre");
		//Escribo en la parte gráfica el valor del dato recuperado
		muestratexto.setText(txt);
	}
}
