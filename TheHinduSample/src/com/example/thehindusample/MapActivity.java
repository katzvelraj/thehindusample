package com.example.thehindusample;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends ActionBarActivity{
	
	private GoogleMap googleMap;
	ArrayList<HashMap<String, String>> placelist;
	
	
	private static final String TAG_LAT = "lat";
	private static final String TAG_LONG = "lon";
	
	String lat,lon;
	double latt, longt;
	int i;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		 setContentView(R.layout.activity_map);
	
		//place list
	     placelist = ListActivity.oslist;  
	     
	     

		 
		 
		 try {
				// Loading map
				initilizeMap();
		

				// Changing map type
				googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
				// googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
				// googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
				// googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
				// googleMap.setMapType(GoogleMap.MAP_TYPE_NONE);

				// Showing / hiding your current location
				googleMap.setMyLocationEnabled(true);

				// Enable / Disable zooming controls
				googleMap.getUiSettings().setZoomControlsEnabled(false);

				// Enable / Disable my location button
				googleMap.getUiSettings().setMyLocationButtonEnabled(true);

				// Enable / Disable Compass icon
				googleMap.getUiSettings().setCompassEnabled(true);

				// Enable / Disable Rotate gesture
				googleMap.getUiSettings().setRotateGesturesEnabled(true);

				// Enable / Disable zooming functionality
				googleMap.getUiSettings().setZoomGesturesEnabled(true);
				
				
				
				
				for(HashMap<String, String> place : placelist ){
					
					
					
					
					lat = place.get(TAG_LAT);
					lon = place.get(TAG_LONG);
					
					latt = Double.parseDouble(lat);
					longt = Double.parseDouble(lon);
					
					
					double[] randomLocation = createRandLocation(latt,
							longt);
					MarkerOptions marker = new MarkerOptions().position(
							new LatLng(randomLocation[0], randomLocation[1]))
							.title("Hindu Sample ");
					
					marker.icon(BitmapDescriptorFactory
							.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
					
					googleMap.addMarker(marker);
					
					
					CameraPosition cameraPosition = new CameraPosition.Builder()
					.target(new LatLng(randomLocation[0],
							randomLocation[1])).zoom(15).build();
					
					googleMap.animateCamera(CameraUpdateFactory
							.newCameraPosition(cameraPosition));
				}


			} catch (Exception e) {
				e.printStackTrace();
			}

		}


		@Override
		protected void onResume() {
			super.onResume();
			initilizeMap();
		}

		/**
		 * function to load map If map is not created it will create it for you
		 * */
		private void initilizeMap() {
			if (googleMap == null) {
				googleMap = ((MapFragment) getFragmentManager().findFragmentById(
						R.id.map)).getMap();

				// check if map is created successfully or not
				if (googleMap == null) {
					Toast.makeText(getApplicationContext(),
							"Sorry! unable to create maps", Toast.LENGTH_SHORT)
							.show();
				}
			}
		}

		/*
		 * creating random postion around a location for testing purpose only
		 */
		private double[] createRandLocation(double latitude, double longitude) {

			return new double[] { latitude + ((Math.random() - 0.5) / 500),
					longitude + ((Math.random() - 0.5) / 500),
					150 + ((Math.random() - 0.5) * 10) };
		}

	

}
