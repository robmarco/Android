package com.beemer.widgettest;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class AppWidget extends AppWidgetProvider {
	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
	// TODO Update the Widget UI.
   		final int N = appWidgetIds.length;

   		// Este bucle lo realizamos para cada widget de este tipo
        for (int i=0; i<N; i++) {
            int appWidgetId = appWidgetIds[i];
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widgetlayout);

            // Creamos un intent para lanzar la activity
            Intent intent = new Intent(context, WidgetTestActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            views.setOnClickPendingIntent(R.id.widget_image, pendingIntent);

            // Le decimos al AppWidgetManager que haga un update en el widget actual
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
	}

	@Override
    public void onReceive(Context context, Intent intent) {
    	final String action = intent.getAction();
        super.onReceive(context, intent);
    }	
	
    //runs when all of the first instance of the widget are placed on the home screen
    @Override
    public void onEnabled(Context context) {
	    super.onEnabled(context);
	    
    }
}


