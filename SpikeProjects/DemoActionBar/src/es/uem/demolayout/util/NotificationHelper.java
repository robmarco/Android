package es.uem.demolayout.util;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * Clase que pretende ser de ayuda a la hora de 
 * generar notificaciones. (No se debe utilizar para el ejercicio en casa)
 * @author jyaguez
 *
 */
public class NotificationHelper {
	private static NotificationHelper _instance;
	
	static {
		_instance = new NotificationHelper();
	}
	
	public static NotificationHelper getInstance() {
		return _instance;
	}

	/**
	 * Método que genera una notificación
	 * @param context contexto
	 * @param message Mensaje que se mostrará en la barra de notificaciones
	 * @param title Título en la barra de notificaciones
	 * @param notificationId identificador para la notificación
	 * @param resDrawable icono para la notificación.
	 * @param resultIntent intent de destino para cuando se pulsa sobre la notificación.
	 */
	public void createNotificationMessage(Context context, String message, String title, 
			int notificationId, int resDrawable, Intent resultIntent) {
		

		PendingIntent resultPendingIntent = PendingIntent.getActivity(context, 0, resultIntent, 
				PendingIntent.FLAG_UPDATE_CURRENT);
		
		NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
			.setContentIntent(resultPendingIntent)
			.setContentText(message)
			.setDefaults(Notification.DEFAULT_ALL)
			.setContentTitle(title)
			.setAutoCancel(true)
			.setSmallIcon(resDrawable);
		
		NotificationManager mNotificationManager =
		    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		
		mNotificationManager.notify(notificationId, builder.build());
	}
}
