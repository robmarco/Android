package es.uem.demolayout.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import es.uem.demolayout.R;
import es.uem.demolayout.activity.NotificationMessageActivity;
import es.uem.demolayout.util.NotificationHelper;

/**
 * Clase abstracta de la que heredan todos los fragments que generan la notificación ya que el comportamiento
 * será igual, botón y EditText, de esta forma no duplico código.
 * @author jyaguez
 *
 */
public abstract class AbstractFragment extends Fragment implements OnClickListener {
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_accept:
			
			Intent i = new Intent(getActivity(), NotificationMessageActivity.class);
			
			NotificationHelper.getInstance().createNotificationMessage(getActivity(), getString(R.string.notification_message), 
					getString(R.string.app_name), (int) System.currentTimeMillis(), R.drawable.ic_launcher, i);
			
			break;

		default:
			break;
		}
		
	}

}
