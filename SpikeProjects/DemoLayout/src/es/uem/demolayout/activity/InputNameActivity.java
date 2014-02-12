package es.uem.demolayout.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import es.uem.demolayout.R;
import es.uem.demolayout.util.Constant;
import es.uem.utils.NotificationHelper;

/**
 * Clase abstracta de la que heredan todas las actividades que generan la notificación ya que el comportamiento
 * será igual, botón y EditText, de esta forma no duplico código.
 * @author jyaguez
 *
 */
public abstract class InputNameActivity extends Activity implements OnClickListener, 
	TextWatcher  {
	
	private EditText mEditText;
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_accept:
			
			//Compruebo que no tenga la instancia del EditText para no volver a 
			//añadir el listener.
			if(mEditText == null) {
				mEditText = getEditTextName();
				
				//Añado el TextWatcher, como la clase implementa la interfaz...
				mEditText.addTextChangedListener(this);
			}
			
			//Recupero el texto del editText.
			CharSequence name = mEditText.getText();
			
			//Compruebo que el usuario haya introducido texto.
			if(TextUtils.isEmpty(name)) { //Si no le muestro un error.
				mEditText.setError(getString(R.string.error_field_name));
				mEditText.addTextChangedListener(this);
			} else { //Si ha introducido texto, genero la notificación
				
				//Creo los extras para pasarle el texto del editText a la actividad de destino.
				Bundle extras = new Bundle();
				extras.putCharSequence(Constant.EXTRA_NAME, name);
				
				Intent i = new Intent(this, NotificationMessageActivity.class);
				i.putExtras(extras); //añado los estras al intent
				
				NotificationHelper.getInstance().createNotificationMessage(this, getString(R.string.notification_message), 
						getString(R.string.app_name), 0, R.drawable.ic_launcher, i);
			}
			
			break;

		default:
			break;
		}
		
	}

	@Override
	public void afterTextChanged(Editable s) {
		
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		//Si está cambiando el texto, seguro que tiene foco.
		View focusedView = getCurrentFocus(); 
		
		//Por si acaso compruebo que la vista sea un editText
		//si es el editTexct, quito el error, porque ya ha introducido texto.
		if(focusedView instanceof EditText) {
			((EditText)focusedView).setError(null);
		}
	}
	
	/**
	 * Debe devolver la instancia del editText en el que el usuario 
	 * introduce el nombre.
	 * @return El editText.
	 */
	protected abstract EditText getEditTextName();
}
