package com.beemer.webkit;

import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebkitActivity extends Activity {
	WebView browser;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		browser = (WebView) findViewById(R.id.webkit);
		browser.loadData("<html><body> Hello,world! 			</body></html>", "text/html", "UTF-8");
		loadTime();

		browser.setWebViewClient(new WebCallback());

	}

	void loadTime() {
		String page = "<html><body><a href=\"/clock\">" + new Date().toString() + "</a></body></html>";
		browser.loadData(page, "text/html", "UTF-8");
	}

	private class WebCallback extends WebViewClient {
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			loadTime();
			return (true);
		}
	}

}