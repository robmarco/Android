package es.uem.demolayout.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

public class Common {
	private static Common mInstance;
	
	static {
		mInstance = new Common();
	}
	
	public static Common getInstance() {
		return mInstance;
	}
	
	private Common() {}
	
	/**
	 * Método para convertir dip a pixels.
	 * @param context Contexto 
	 * @param dipValue valor en dip
	 * @return valor en pixels
	 */
	public float dipToPixels(Context context, float dipValue) {
	    DisplayMetrics metrics = context.getResources().getDisplayMetrics();
	    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics);
	}

}
