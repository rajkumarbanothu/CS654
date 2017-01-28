package com.example.googlemaps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.content.Context;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.maps.MapActivity;

public class MainActivity extends MapActivity {

	
	public GoogleMap map;
	private LocationManager manager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();
		manager = (LocationManager) this
				.getSystemService(Context.LOCATION_SERVICE);
		// new LostMobLocGetter().execute();
		callAsynchronousTask();
		// LocationListener listener = new LocationListener() {
		//
		// @Override
		// public void onLocationChanged(Location location) {
		// // TODO Auto-generated method stub
		// double lat = location.getLatitude();
		// double lon = location.getLongitude();
		// map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
		// LatLng latlon = new LatLng(lat, lon);
		// map.addMarker(new MarkerOptions().position(latlon).title(
		// "My Location"));
		// map.moveCamera(CameraUpdateFactory.newLatLng(latlon));
		// map.animateCamera(CameraUpdateFactory.zoomTo(21));
		// }
		//
		// @Override
		// public void onStatusChanged(String provider, int status,
		// Bundle extras) {
		// // TODO Auto-generated method stub
		//
		// }
		//
		// @Override
		// public void onProviderEnabled(String provider) {
		// // TODO Auto-generated method stub
		//
		// }
		//
		// @Override
		// public void onProviderDisabled(String provider) {
		// // TODO Auto-generated method stub
		//
		// }
		//
		// };
		// manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,
		// listener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	class LostMobLocGetter extends AsyncTask<Void, Void, JSONObject> {

		@Override
		protected JSONObject doInBackground(Void... params) {
			JSONObject jsonResponse = null;
			try {
				HttpClient httpClient = new DefaultHttpClient();
				HttpGet httpGet = new HttpGet(
						"http://172.27.22.52:8099/MyWebService/LocationTracker");

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
			try {
				LatLng myLoc = new LatLng(result.getDouble("latitude"),
						result.getDouble("longitude"));
//				BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory
//						.defaultMarker(BitmapDescriptorFactory.HUE_AZURE);
//				Marker marker = map.addMarker(new MarkerOptions()
//						.position(myLoc).icon(bitmapDescriptor)
//						.title(myLoc.toString()));
//				CameraUpdate updatePosition = CameraUpdateFactory
//						.newLatLng(myLoc);
//
//				// Creating CameraUpdate object for zoom
//				CameraUpdate updateZoom = CameraUpdateFactory.zoomBy(2.0f);
//
//				// Updating the camera position to the user input latitude and
//				// longitude
//				map.moveCamera(updatePosition);
//
//				// Applying zoom to the marker position
//				map.animateCamera(updateZoom);
//				Toast.makeText(getBaseContext(), "Location Changed",
//						Toast.LENGTH_SHORT).show();
				
				 map.addMarker(new MarkerOptions().position(myLoc).title(
				 "My Location"));
				 map.moveCamera(CameraUpdateFactory.newLatLng(myLoc));
				 map.animateCamera(CameraUpdateFactory.zoomTo(16));
				
			} catch (Exception ex) {
				android.util.Log.e("Error", ex.getMessage());
			}

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

	public void callAsynchronousTask() {
		final Handler handler = new Handler();
		Timer timer = new Timer();
		TimerTask doAsynchronousTask = new TimerTask() {
			@Override
			public void run() {
				handler.post(new Runnable() {
					public void run() {
						try {
							LostMobLocGetter mobLocGetter = new LostMobLocGetter();
							mobLocGetter.execute();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		};
		timer.schedule(doAsynchronousTask, 0, 3000);
	}
}
