package com.beemer.webservices;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class WebServicesActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);

		// Try http://services.aonaware.com/DictService/DictService.asmx/Define?word=apple
	}
	
	public void onClickSearch(View v){
		String result = wordDefinition(((TextView)findViewById(R.id.et_word)).getText().toString());
		((TextView)findViewById(R.id.tv_result)).setText(result);
	}
	

	private InputStream OpenHttpConnection(String urlString) throws IOException {

		InputStream in = null;
		int response = -1;
		URL url = new URL(urlString);
		URLConnection conn = url.openConnection();

		if (!(conn instanceof HttpURLConnection))
			throw new IOException("Not an HTTP connection");

		try {
			HttpURLConnection httpConn = (HttpURLConnection) conn;
			httpConn.setAllowUserInteraction(false);
			httpConn.setInstanceFollowRedirects(true);
			httpConn.setRequestMethod("GET");
			httpConn.connect();
			response = httpConn.getResponseCode();
			if (response == HttpURLConnection.HTTP_OK) {
				in = httpConn.getInputStream();
			}
		} catch (Exception ex) {
			throw new IOException("Error connecting");
		}
		return in;
	}

	private String wordDefinition(String word) {
		InputStream in = null;
		try {
			String url = "http://services.aonaware.com/DictService/DictService.asmx/Define?word=" + word;

			in = OpenHttpConnection(url);

			Document doc = null;
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db;
			try {
				db = dbf.newDocumentBuilder();
				doc = db.parse(in);
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			doc.getDocumentElement().normalize();
			// ---retrieve all the <Definition> nodes---
			NodeList definitionElements = doc.getElementsByTagName("Definition");
			String strDefinition = "";
			int len = definitionElements.getLength();
			for (int i = 0; i < definitionElements.getLength(); i++) {
				Node itemNode = definitionElements.item(i);
				if (itemNode.getNodeType() == Node.ELEMENT_NODE) {
					// ---convert the Node into an Element---
					Element definitionElement = (Element) itemNode;
					// ---get all the <WordDefinition> elements under
					// the <Definition> element---
					NodeList wordDefinitionElements = (definitionElement).getElementsByTagName("WordDefinition");
					strDefinition = "";
					for (int j = 0; j < wordDefinitionElements.getLength(); j++) {
						// ---convert a <WordDefinition> Node into an Element---
						Element wordDefinitionElement = (Element) wordDefinitionElements.item(j);
						// ---get all the child nodes under the
						// <WordDefinition> element---
						NodeList textNodes = ((Node) wordDefinitionElement).getChildNodes();
						strDefinition += ((Node) textNodes.item(0)).getNodeValue() + ". ";
					}
					
					return strDefinition;
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			return e1.getMessage();
		}
		return "";
	}

}