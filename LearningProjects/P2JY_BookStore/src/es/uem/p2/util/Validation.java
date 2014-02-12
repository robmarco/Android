package es.uem.p2.util;

import android.content.Context;
import android.widget.TextView;
import es.uem.p2_roberto_marco.R;

public class Validation {

	public static final boolean validText(Context context, TextView tv) {
		String text = tv.getText().toString();
		if (text.trim().isEmpty()) {
			tv.setError(context.getText(R.string.required_field));
			return false;
		}		
		return true;
	}
}
