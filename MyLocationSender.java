package com.mobile.tracker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;
import android.util.Log;

public class MyLocationSender extends AsyncTask<String, Void, String> {

	@Override
	protected String doInBackground(String... params) {

		String result = "";
		try {
			HttpClient httpClient = new DefaultHttpClient();
			// StringBuilder queryString = new StringBuilder(50);
			// queryString.append("?lat=" + params[0]);
			// queryString.append("&lon=" + params[1]);
			// HttpGet httpGet = new HttpGet(
			// "http://172.27.30.78:8099/MyWebService/LocationTracker"
			// + queryString.toString());
			//
			// HttpResponse httpRes = httpClient.execute(httpGet);
			// InputStream inputstream = httpRes.getEntity().getContent();
			HttpPost httpPost = new HttpPost(
					"http://172.27.22.52:8099/MyWebService/LocationTracker");
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("lat", params[0]
					.toString()));
			nameValuePairs.add(new BasicNameValuePair("lon", params[1]
					.toString()));
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			// Execute HTTP Post Request
			httpClient.execute(httpPost);

		} catch (Exception e) {
			Log.d("InputStream", e.getLocalizedMessage());
		}

		return result;
	}

//	private String convertInputStreamToString(InputStream inputStream)
//			throws IOException {
//		BufferedReader bufferedReader = new BufferedReader(
//				new InputStreamReader(inputStream));
//		String line = "";
//		String result = "";
//		while ((line = bufferedReader.readLine()) != null)
//			result += line;
//
//		inputStream.close();
//		return result;

//	}

}
