package com.example.robospicetest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.octo.android.robospice.JacksonGoogleHttpClientSpiceService;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

public class MainActivity extends Activity {
	private final SpiceManager spiceManager = new SpiceManager(JacksonGoogleHttpClientSpiceService.class);
	private String lastRqstCacheKey;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// Try http://www.xmlfiles.com/examples/plant_catalog.xml
		MainActivity.this.setProgressBarIndeterminateVisibility(true);
		XmlRequest xmlRequest = new XmlRequest();
		lastRqstCacheKey = xmlRequest.createCacheKey();
		spiceManager.execute(xmlRequest, lastRqstCacheKey, DurationInMillis.ONE_MINUTE, new ListXmlRequestRequestListener());
	}

	@Override
	protected void onStart() {
		spiceManager.start(this);
		super.onStart();
	}

	@Override
	protected void onStop() {
		spiceManager.shouldStop();
		super.onStop();
	}

	public final class ListXmlRequestRequestListener implements RequestListener<DataModel> {

		@Override
		public void onRequestFailure(final SpiceException e) {
		}

		@Override
		public void onRequestSuccess(final DataModel wordDef) {
			String text = "";
			for (Plant plant : wordDef.getPlants()) {
				text += plant.getCommonName() + ": " + plant.getBotanicalName() + "\n";
			}

			((TextView) findViewById(R.id.tv_result)).setText(text);
		}
	}

}
