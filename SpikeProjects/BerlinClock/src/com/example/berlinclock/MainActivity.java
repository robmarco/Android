
package com.example.berlinclock;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {
    Timer timer;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer = new Timer();
        Calendar c = Calendar.getInstance();

        //Usar el timer para actualizar las luces del reloj cada 2 segundos.
        //UpdateLights() actualiza las luces si se le pasa un byte array que sacaremos de getClock()
        //A getClock() le pasaremos la hora, minutos y segundos actual obtenida del Calendar c.
        // c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), c.get(Calendar.SECOND)
        //!Cuidado con los hilos de ejecución! 
        

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

    protected int[] getClock(final int hours, final int minutes, final int seconds) {
        int[] lights = new int[5];
        
        //Calcular los valores del array a partir de los parámetros de entrada.

        return lights;
    }

    private void updateLights(final int[] lights) {
        findViewById(R.id.sec21).setBackgroundColor(getColor(lights[0], 1));
        for (int i = 0; i < 11; i++) {
            if (i < 4) {
                ((ViewGroup) findViewById(R.id.h5)).getChildAt(i).setBackgroundColor(getColor(lights[1], i + 1));
                ((ViewGroup) findViewById(R.id.h1)).getChildAt(i).setBackgroundColor(getColor(lights[2], i + 1));
                ((ViewGroup) findViewById(R.id.m1)).getChildAt(i).setBackgroundColor(getColor(lights[4], i + 1));
            }
            ((ViewGroup) findViewById(R.id.m5)).getChildAt(i).setBackgroundColor(getColor(lights[3], i + 1));
        }
    }

    private int getColor(final int data, final int level) {
        return data >= level ? Color.RED : Color.GRAY;
    }

}
