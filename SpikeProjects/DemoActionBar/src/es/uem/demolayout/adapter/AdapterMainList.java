package es.uem.demolayout.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import es.uem.demolayout.activity.MainActivity;

/**
 * Esta clase representa el Adapter para renderizar todos los elementos del 
 * ListView en {@link MainActivity}
 * @author jyaguez
 *
 */
public class AdapterMainList extends BaseAdapter {
	
	/**Lista con los elementos, este caso solo serán cadenas de texto*/
	private TypedArray mItems;
	
	/**Referencia del contexto, para poder inflar las vistas*/
	private Context mContext;
	
	public AdapterMainList(Context ctx, TypedArray items) {
		mItems = items;
		mContext = ctx;
	}

	/**
	 * Este método devuelve el número de elementos que se pintarán
	 * en la lista.
	 */
	@Override
	public int getCount() {
		return mItems == null ? 0 : mItems.length();
	}

	/**
	 * Voy a devolver el identificador del recurso que corresponde 
	 * al string Ejemplo: {@code R.string.linear_xml_sample} 
	 * Está definido en strings.xml.
	 */
	@Override
	public Integer getItem(int idx) {
		return mItems == null ? -1 : mItems.getResourceId(idx, 0);
	}

	@Override
	public long getItemId(int arg0) {
		//de momento no me interesa devolver ningún valor real aquí
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		//Recupero el valor del array en la posción que quiero.
		String item = mItems.getString(position);
		
		//converView puede no ser null ya que 
		//este tipo de vista está pensado para reutilizar las vistas,
		//por lo que no me molesto en inflarla de nuevo.
		if(convertView == null) {
			
			//Así se infla una vista desde un xlm.
			//android.R.layout.simple_list_item_1 es un layout con un TextView
			//que nos ofrece Android para este tipo de cosas.
			convertView = LayoutInflater.from(mContext)
					.inflate(android.R.layout.simple_list_item_1, parent, false);
		}
		
		//en este punto convertView no es null.
		//obtengo la instancia del textView que se que contiene la Vista
		TextView text = (TextView) convertView.findViewById(android.R.id.text1);
		
		//Mediante código también puedo acceder a los atributos
		//de un objeto. En este caso, es como
		//si estubiese haciendo referencia al atributo xml android:text=""
		text.setText(item);
		
		//devuelvo la vista que quiero que se pinte en la 
		//posición "position".
		return convertView;
	}

}
