package com.example.basededatos;

import java.util.ArrayList;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        DatabaseHelper dbh = new DatabaseHelper(Main.this);
        SQLiteDatabase db = dbh.getWritableDatabase();
        
        //add some registers
        for(int i=0; i<10; i++){
        	dbh.insertBook(db, "title"+i, "author"+i, "isbn"+1);
        }
        db.close();
        
        //Open again in read only...
        db = dbh.getReadableDatabase();
        ArrayList<String[]> lista = new ArrayList<String[]>();
        lista = dbh.selectAll(db);
        
        String result = "";
        for(int i=0; i<lista.size(); i++){
        	result += lista.get(i)[0] + "-" + lista.get(i)[1] + "-" + lista.get(i)[2] + "\n";
        }
        
        ((TextView)findViewById(R.id.tv)).setText(result);
 
    }
}