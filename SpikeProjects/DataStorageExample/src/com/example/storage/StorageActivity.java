package com.example.storage;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.example.storage.R;

public class StorageActivity extends Activity {

	private EditText textBox;
	private static final int READ_BLOCK_SIZE = 100;
	File file;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.internal);

		textBox = (EditText) findViewById(R.id.editText1);
		Button saveBtn = (Button) findViewById(R.id.btnSave);
		Button loadBtn = (Button) findViewById(R.id.btnLoad);

		File sdCard = Environment.getExternalStorageDirectory();
		File directory = new File(sdCard.getAbsolutePath() + "/MyFiles");
		directory.mkdirs();
		file = new File(directory, "textfile.txt");

		saveBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String str = textBox.getText().toString();
				FileOutputStream fOut;
				try {
					fOut = new FileOutputStream(file);
					OutputStreamWriter osw = new OutputStreamWriter(fOut);

					osw.write(str);
					osw.flush();
					osw.close();

					Toast.makeText(getBaseContext(), "File saved successfully!", Toast.LENGTH_SHORT).show();
					textBox.setText("");

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		});

		loadBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				try {
					FileInputStream fIn = new FileInputStream(file);

					InputStreamReader isr = new InputStreamReader(fIn);

					char[] inputBuffer = new char[READ_BLOCK_SIZE];
					String s = "";

					int charRead;

					while ((charRead = isr.read(inputBuffer)) > 0) {
						String readString = String.copyValueOf(inputBuffer, 0, charRead);
						s += readString;

						inputBuffer = new char[READ_BLOCK_SIZE];
					}

					textBox.setText(s);

					Toast.makeText(getBaseContext(), "File loaded successfully!", Toast.LENGTH_SHORT).show();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		});
	}

}
