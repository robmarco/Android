package es.uem.demolayout;

import android.app.Application;

/**
 * Esta clase respresenta nuestra aplicación, su contexto es válido mientras
 * la aplicación este ejecutando.
 * 
 * especificamos esta clase en nuestro archivo Manifest en el elemento
 * {@code <application  android:name="es.uem.demolayout.App">}. De esta forma
 * ligamos nuestra app con esta clase.
 * @author jyaguez
 *
 */
public class App extends Application {
	
	/**
	 * Igual que las actividades la aplicacion tiene su ciclo de vida.
	 * Cuando se crea se notifica en este método.
	 */
	@Override
	public void onCreate() {
		//Lo priemro que hay que hacer es llamar al onCreate de la clase padre.
		super.onCreate();
		
		//Nuestro código iría aquí.
	}

}
