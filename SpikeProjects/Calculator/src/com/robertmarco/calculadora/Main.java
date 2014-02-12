package com.robertmarco.calculadora;

import java.util.ArrayList;

import com.robertmarco.calculadora.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Main extends Activity {
    static final ArrayList<String> PILAOPERACIONES = new ArrayList<String>();
    static final ArrayList<String> PILANUMEROS = new ArrayList<String>();
	
	int resOp = 0;
    int antesDeOperar = 0;
    String ultimaOperacion = "";

    ArrayList<String> pilaOp = new ArrayList<String>();
    ArrayList<Integer> pilaNum = new ArrayList<Integer>();
    boolean borrarPantalla = false;
    boolean ultimaTeclaOp = false;

    EditText mRresultado;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //Declaracion de variables
        Button bt_1 		= (Button)findViewById(R.id.bt_1);
        Button bt_2 		= (Button)findViewById(R.id.bt_2);
        Button bt_3 		= (Button)findViewById(R.id.bt_3);
        Button bt_4 		= (Button)findViewById(R.id.bt_4);
        Button bt_5 		= (Button)findViewById(R.id.bt_5);
        Button bt_6 		= (Button)findViewById(R.id.bt_6);
        Button bt_7 		= (Button)findViewById(R.id.bt_7);
        Button bt_8 		= (Button)findViewById(R.id.bt_8);
        Button bt_9 		= (Button)findViewById(R.id.bt_9);
        Button bt_0			= (Button)findViewById(R.id.bt_0);
        Button bt_coma 		= (Button)findViewById(R.id.bt_coma);
        Button bt_mas		= (Button)findViewById(R.id.bt_mas);
        Button bt_menos 	= (Button)findViewById(R.id.bt_menos);
        Button bt_por 		= (Button)findViewById(R.id.bt_por);
        Button bt_entre 	= (Button)findViewById(R.id.bt_entre);
        Button bt_igual 	= (Button)findViewById(R.id.bt_igual);

        //Asignamos listeners
        bt_1.setOnClickListener(oclBtNumerico);
        bt_2.setOnClickListener(oclBtNumerico);
        bt_3.setOnClickListener(oclBtNumerico);
        bt_4.setOnClickListener(oclBtNumerico);
        bt_5.setOnClickListener(oclBtNumerico);
        bt_6.setOnClickListener(oclBtNumerico);
        bt_7.setOnClickListener(oclBtNumerico);
        bt_8.setOnClickListener(oclBtNumerico);
        bt_9.setOnClickListener(oclBtNumerico);
        bt_0.setOnClickListener(oclBtNumerico);
        bt_coma.setOnClickListener(oclBtNumerico);

        bt_mas.setOnClickListener(oclBtOperaciones);
        bt_menos.setOnClickListener(oclBtOperaciones);
        bt_por.setOnClickListener(oclBtOperaciones);
        bt_entre.setOnClickListener(oclBtOperaciones);
        bt_igual.setOnClickListener(oclBtOperaciones);

        mRresultado = (EditText)findViewById(R.id.et_resultado);

    }

    //listeners
    OnClickListener oclBtNumerico = new OnClickListener(){
		@Override
		public void onClick(View v) {
			if(borrarPantalla){
				mRresultado.setText("");
				borrarPantalla = false;
			}

			String tmp = ((Button)v).getText().toString();
			String tmp2 = mRresultado.getText().toString();
			mRresultado.setText(tmp2 + tmp);

			ultimaTeclaOp = false;
		}
    };

    OnClickListener oclBtOperaciones = new OnClickListener(){
		@Override
		public void onClick(View v) {
			borrarPantalla = true;
			String tipoOp = ((Button)v).getText().toString();

			pilaNum.add(Integer.parseInt(mRresultado.getText().toString()));

			if(tipoOp.equals("+")){
				if(ultimaTeclaOp){
					try{
						pilaOp.remove(0);
					}catch(Exception e){}
				}
				pilaOp.add("+");
				
			} else if (tipoOp.equals("-")) {
				if(ultimaTeclaOp){
					try{
						pilaOp.remove(0);
					}catch(Exception e){}
				}
				pilaOp.add("-");
				
			} else if (tipoOp.equals("*")) {
				if(ultimaTeclaOp){
					try{
						pilaOp.remove(0);
					}catch(Exception e){}
				}
				pilaOp.add("*");
				
			} else if (tipoOp.equals("/")) {
				if(ultimaTeclaOp){
					try{
						pilaOp.remove(0);
					}catch(Exception e){}
				}
				pilaOp.add("/");
			}

			procesarPila();

			if(tipoOp.equals("=")){
				mRresultado.setText(pilaNum.get(0) + "");
				pilaNum.clear();
				pilaOp.clear();
			}

			ultimaTeclaOp = true;
		}
    };

    public void procesarPila(){
    	if(pilaOp.size() >= 1 && pilaNum.size() >= 2){
    		if(pilaOp.get(0).equals("+")){
    			int tmp = pilaNum.get(0) + pilaNum.get(1);
    			pilaNum.set(0, tmp);
    			pilaNum.remove(1);
    		}
    		
    		else if(pilaOp.get(0).equals("-")){
    			int tmp = pilaNum.get(0) - pilaNum.get(1);
    			pilaNum.set(0, tmp);
    			pilaNum.remove(1);
    		}
    		
    		else if(pilaOp.get(0).equals("/")){
    			int tmp = pilaNum.get(0) / pilaNum.get(1);
    			pilaNum.set(0, tmp);
    			pilaNum.remove(1);
    		}
    		
    		else if(pilaOp.get(0).equals("*")){
    			int tmp = pilaNum.get(0) * pilaNum.get(1);
    			pilaNum.set(0, tmp);
    			pilaNum.remove(1);
    		}
    	}
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
    	outState.putStringArrayList("Operaciones", pilaOp);
    	outState.putIntegerArrayList("Numeros", pilaNum);
    	super.onSaveInstanceState(outState);
    }
    
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
    	super.onRestoreInstanceState(savedInstanceState);
    	
    	pilaOp = savedInstanceState.getStringArrayList("Operaciones");
    	pilaNum = savedInstanceState.getIntegerArrayList("Numeros");
    }

}