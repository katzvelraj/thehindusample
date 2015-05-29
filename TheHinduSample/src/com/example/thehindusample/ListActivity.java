package com.example.thehindusample;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ListActivity  extends ActionBarActivity{
	
	JSONArray json;
	
	private ProgressDialog pdialog ;
	
	View view;
	private static final String TAG_ID = "id";
	private static final String TAG_PRO_NAME = "projectName";
	private static final String TAG_LAT = "lat";
	private static final String TAG_LONG = "lon";
	
	public static ArrayList<HashMap<String, String>> oslist = new ArrayList<HashMap<String, String>>();
	
	
	private static String url ="http://54.254.240.217:8080/app-task/projects/";
	
	 ListView list;
	 TextView mapView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		list = (ListView)findViewById(R.id.list);
		mapView = (TextView)findViewById(R.id.mapview_txt);
		
	
		
		// Calling async task to get json
				new JSONParse().execute();
				
				mapView.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						
						Intent mapintent = new Intent(ListActivity.this, MapActivity.class);
						startActivity(mapintent);
						
					}
				});
		
	}
	

	 
	 public class JSONParse extends AsyncTask<Void , Void ,Void>{
      
      
      protected void onPreExecute() {
    	  super.onPreExecute();
    	  pdialog = new ProgressDialog(ListActivity.this);
    	  pdialog.setMessage("downloading");
    	  pdialog.setCancelable(false);
    	  pdialog.show();
    	  
		
	}
		@Override
		protected Void doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			JsonParsing jparse = new JsonParsing();
			String json = jparse.getJSONFromUrl(url);
		
			
			
			if (json != null) {
				try {
					JSONArray jsonObj = new JSONArray(json);
					
					
					oslist.clear();	

    				for(int i = 0; i < jsonObj.length(); i++){
    				JSONObject c = jsonObj.getJSONObject(i);
    				
    				// Storing  JSON item in a Variable
    				String id = c.getString(TAG_ID);
    				String name = c.getString(TAG_PRO_NAME);
    				String lat = c.getString(TAG_LAT);
    				String lon = c.getString(TAG_LONG);
    					
    				// Adding value HashMap key => value
    				//db.insertValue(ver, name, api);    				
    				
    				

    				HashMap<String, String> map = new HashMap<String, String>();
                    map.put(TAG_ID, id);
    				map.put(TAG_PRO_NAME, name);
    				map.put(TAG_LAT, lat);
    				map.put(TAG_LONG, lon);
    				// adding contact to contact list
    				
    				
    			
    				
					oslist.add(map);
    				
					
					
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {
				Log.e("ServiceHandler", "Couldn't get any data from the url");
			}

			
			
			return null;
			
			
	 }
	 
		 @SuppressLint("NewApi")
		protected void onPostExecute(Void  json) {
			
			 if (pdialog!= null) {
				 pdialog.dismiss();
				 pdialog= null;
			
			}
			 
			 
				ListAdapter adapter = new SimpleAdapter(ListActivity.this, oslist,
						R.layout.item_list,
						new String[] { TAG_ID,TAG_PRO_NAME,TAG_LAT, TAG_LONG }, new int[] {
								R.id.item_idtxt,R.id.item_nametxt,R.id.item_lattxt, R.id.item_longtxt});
				
				

				list.setAdapter(adapter);
				
		        
				list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

		            @Override
		            public void onItemClick(AdapterView<?> parent, View view,
		                                    int position, long id) {
    		 
    	 }
    });
		 }
	 }
	 
	 
	 @Override
	    protected void onStop() {

	        super.onStop();

	        if (pdialog != null) {
	        	pdialog.dismiss();
	        	pdialog = null;
	        }

	    }


}
