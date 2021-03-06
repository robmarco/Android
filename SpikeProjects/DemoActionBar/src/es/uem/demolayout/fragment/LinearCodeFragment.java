package es.uem.demolayout.fragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import es.uem.demolayout.R;
import es.uem.demolayout.util.Common;

/**
 * Ejemplo de un fragment cuyo layout está definido mediante código
 * 
 * @author jyaguez
 * 
 */
public class LinearCodeFragment extends AbstractFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Creo el linear layout que contendrá todos los elementos.
		LinearLayout rootView = new LinearLayout(getActivity());

		// Defino los atributos de la vista android:layout_width y
		// android:layout_height
		rootView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));

		// Padding, como solo se la medida en dpi, la tengo que transformar en
		// pixels.
		// (dpi es una medida que hace referencia a la densidad de pantalla del
		// dispositivo)
		int padding = (int) Common.getInstance().dipToPixels(getActivity(), 20);

		rootView.setPadding(padding, padding, padding, padding);

		// Orientación de la vista android:orientation
		rootView.setOrientation(LinearLayout.VERTICAL);

		// Creo el título
		TextView tvTitle = new TextView(getActivity());

		// Como el TextView está contenido en un LinearLayout, sus layoutParams
		// pertenecen
		// a esa clase, por tanto LinearLayout.LayoutParams.
		tvTitle.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT));

		// Estilo del texto. En este caso cojo uno que nos proporciona android
		tvTitle.setTextAppearance(getActivity(),
				android.R.style.TextAppearance_Large);

		// Quiero centrar el contenido del TextView, android:gravity
		tvTitle.setGravity(Gravity.CENTER);

		// El texto del textview.
		tvTitle.setText(R.string.linear_code_title);

		// Creo el botón.
		Button btnOk = new Button(getActivity());

		// asigno un identificador único en la vista que lo contiene.
		// me interesa para gestionar el onClick
		btnOk.setId(R.id.btn_accept);

		// Params para el botón.
		LinearLayout.LayoutParams btnParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);

		// Quiero que esté centrado horizontalmetne, android:layout_gravity
		btnParams.gravity = Gravity.CENTER_HORIZONTAL;

		// Margen respecto de la vista superior
		btnParams.setMargins(0, padding, 0, 0);

		// asigno los params creados.
		btnOk.setLayoutParams(btnParams);

		// Texto del botón, en xml android:text
		btnOk.setText(R.string.btn_accept);

		// asigno el onclick, en xlm android:onClick
		btnOk.setOnClickListener(this);

		// Agrego los elementos a la vista raíz. Como la orientación
		// es vertical, se añaden de forma ordenada.
		rootView.addView(tvTitle);
		rootView.addView(btnOk);

		// Devuelvo el linear que he creado
		return rootView;
	}

}
