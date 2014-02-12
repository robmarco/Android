package es.uem.demolayout.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import es.uem.demolayout.R;

/**
 * Ejemplo de un fragment cuyo layout está definido en el fichero
 * xml layout/activity_linear_xml.xml
 * @author jyaguez
 *
 */
public class LinearXMLFragment extends AbstractFragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.activity_linear_xml, container, false);
		rootView.findViewById(R.id.btn_accept).setOnClickListener(this);
		return rootView;
	}

}
