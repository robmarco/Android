package com.beemer.guia;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {
	WebView browser;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		browser = (WebView) findViewById(R.id.webkit);
		browser.loadUrl("http://mobile.guiadelocio.com//");
		browser.setWebViewClient(new WebCallback());
	}

	private class WebCallback extends WebViewClient {
		
		//Con esto controlamos los enlaces dentro de las web
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			browser.loadUrl(url);
			return (true);
		}
	}

}