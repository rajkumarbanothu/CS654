package com.example.googlemaps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class LostMobileLocationGetter extends AsyncTask<Void, Void, JSONObject> {

	@Override
	protected JSONObject doInBackground(Void... params) {
		JSONObject jsonResponse = null;
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(
					"http://172.27.30.78:8099/MyWebService/LocationTracker");

			HttpResponse response = httpClient.execute(httpGet);
			InputStream inputstream = response.getEntity().getContent();
			if (inputstream != null) {
				jsonResponse = new JSONObject(
						convertInputStreamToString(inputstream));

			} else
				jsonResponse = new JSONObject("{failure:'YES'}");

		} catch (Exception e) {
			Log.d("InputStream", e.getLocalizedMessage());
		}

		return jsonResponse;
	}

	protected void onPostExecute(JSONObject result) {
		super.onPostExecute(result);
		

	}

	private String convertInputStreamToString(InputStream inputStream)
			throws IOException {
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream));
		String line = "";
		String result = "";
		while ((line = bufferedReader.readLine()) != null)
			result += line;

		inputStream.close();
		return result;

	}
}