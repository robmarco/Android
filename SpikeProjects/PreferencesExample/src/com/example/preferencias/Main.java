package com.example.preferencias;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;
import com.example.preferencias.R;

public class Main extends Activity {
	private static final String NOMBRE = "nombreUsuario";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        SharedPreferences p = PreferenceManager.getDefaultSharedPreferences(Main.this);
        SharedPreferences.Editor edit = p.edit();
        edit.putString(NOMBRE, "abc");
        edit.commit();
        
        String userName = p.getString(NOMBRE, "");
        
        Toast.makeText(getApplicationContext(), userName, Toast.LENGTH_SHORT).show();
    }
}