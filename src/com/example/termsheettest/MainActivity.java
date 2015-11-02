package com.example.termsheettest;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TextView text = (TextView) findViewById(R.id.statusBox);
		text.setText("Loading Response..");
		requestClient();
	}

	private void requestClient() {
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet("http://termsheet.io");
		HttpResponse response = null;
		TextView text = (TextView) findViewById(R.id.statusBox);
		try {
			response = client.execute(request);
		} catch (ClientProtocolException e) {

			text.setText("ClientProtocolException thrown");
		} catch (IOException e) {

			text.setText("IOException thrown");
		}
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode >= 200 && statusCode < 300) {
			text.setText("The request is OK. Site is Working.");
			text.setTextColor(Color.parseColor("#008000"));
		} else {
			text.setText("The request is NOT Successful.");
			text.setTextColor(Color.parseColor("#FF0000"));
		}
	}
}
