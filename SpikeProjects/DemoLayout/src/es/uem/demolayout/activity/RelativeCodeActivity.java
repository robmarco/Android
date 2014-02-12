package es.uem.demolayout.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import es.uem.demolayout.R;
import es.uem.demolayout.util.Common;

/**
 * Ejemplo de una actividad cuyo layout está definido mediante código.
 * El objetivo de crear un Relative en código, es para que se vea la direncia
 * entre los params de las vistas que pertenecen a un LienarLayout y los que pertencen
 * a un RelativeLayout.
 * @author jyaguez
 *
 */
public class RelativeCodeActivity extends InputNameActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//La vista ahora no es un fichero xml, es una vista, que devuelve
		// el método.
		setContentView(getContentView());
	}
	
	/**
	 * Método que crea mediante código un linear layout
	 * @return la vista creada.
	 */
	private View getContentView() {
		
		//Creo el relatvieLayout que contendrá todos los elementos.
		RelativeLayout rootView = new RelativeLayout(this);
		
		//Defino los atributos de la vista android:layout_width y android:layout_height
		rootView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		
		//Padding, como solo se la medida en dpi, la tengo que transformar en pixels.
		//(dpi es una medida que hace referencia a la densidad de pantalla del dispositivo)
		int padding = (int) Common.getInstance().dipToPixels(this, 20);
		
		rootView.setPadding(padding, padding, padding, padding);
		
		//Creo el título
		TextView tvTitle = new TextView(this);
		tvTitle.setId(R.id.text_title);
		
		//Como el TextView está contenido en un relatvieLayout, sus layoutParams pertenecen
		//a esa clase, por tanto RelatvieLayout.LayoutParams.
		tvTitle.setLayoutParams(new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
		
		//Estilo del texto. En este caso cojo uno que nos proporciona android
		tvTitle.setTextAppearance(this, android.R.style.TextAppearance_Large);
		
		//Quiero centrar el contenido del TextView, android:gravity
		tvTitle.setGravity(Gravity.CENTER);
		
		//El texto del textview.
		tvTitle.setText(R.string.relative_code_title);
		
		//Creo un EditText.
		EditText edtName = new EditText(this);
		
		edtName.setId(R.id.edt_name);
		
		//Params del editText
		RelativeLayout.LayoutParams edtParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		
		edtParams.addRule(RelativeLayout.BELOW, R.id.text_title);
		
		edtName.setLayoutParams(edtParams);
		
		//hitn para el editText android:hint
		edtName.setHint(R.string.relative_code_edt_hint);
		
		//Creo el botón.
		Button btnOk = new Button(this);
		
		//asigno un identificador único en la vista que lo contiene.
		//me interesa para gestionar el onClick
		btnOk.setId(R.id.btn_accept);
		
		//Params para el botón.
		RelativeLayout.LayoutParams btnParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		
		//android:layout_below
		btnParams.addRule(RelativeLayout.BELOW, R.id.edt_name);
		
		
		//Quiero que esté centrado horizontalmetne, android:layout_centerHorizontal
		btnParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		
		//Margen respecto de la vista superior
		btnParams.setMargins(0, padding, 0, 0);
		
		//asigno los params creados.
		btnOk.setLayoutParams(btnParams);
		
		//Texto del botón, en xml android:text
		btnOk.setText(R.string.btn_accept);
		
		//asigno el onclick, en xlm android:onClick
		btnOk.setOnClickListener(this);
		
		//Agrego los elementos a la vista raíz. Al ser
		// un RelativeLayout, el orden no define la posición en pantalla 
		// en los ejes (x, y) si no únicamente en el eje z, que en este caso
		// no me importa.
		rootView.addView(btnOk);
		rootView.addView(edtName);
		rootView.addView(tvTitle);
		
		//Devuelvo el linear que he creado
		return rootView;
	}

	@Override
	protected EditText getEditTextName() {
		return (EditText) findViewById(R.id.edt_name);
	}

}
