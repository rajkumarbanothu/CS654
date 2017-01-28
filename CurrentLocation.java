package com.example.googlemaps;


import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class CurrentLocation extends MapActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mymap);
		MapView view=(MapView)findViewById(R.id.mapview);
		view.setBuiltInZoomControls(true);
		final MapController controller=view.getController();
		LocationManager manager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
		LocationListener listener=new LocationListener() {

			@Override
			public void onLocationChanged(Location location) {
				// TODO Auto-generated method stub
				controller.setCenter(new GeoPoint((int)location.getLatitude(),(int)location.getLongitude()));
				
			}

			@Override
			public void onStatusChanged(String provider, int status,
					Bundle extras) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onProviderEnabled(String provider) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onProviderDisabled(String provider) {
				// TODO Auto-generated method stub
				
			}
			
		};
		manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);
		
	}
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
