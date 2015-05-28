package com.example.thehindusample;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;

public class JsonParsing {
	
	static String sResponse = null;
	 static InputStream in =null;
	 static JSONArray object = null;
	 static String json = null;
	 
	  public JsonParsing(){
		  
	
	  }

	 
	public String getJSONFromUrl(String url){
		
		
		
		try {
			DefaultHttpClient client = new DefaultHttpClient();
			HttpEntity responseEntity = null;
			
			HttpGet get = new HttpGet(url);
			
			HttpResponse response = client.execute(get);
			
		    responseEntity = response.getEntity();
			
			if (responseEntity != null){
				
				sResponse = EntityUtils.toString(responseEntity);
				
			}
				

			
		     }  catch (UnsupportedEncodingException e) {
		    	 
		    	 
			e.printStackTrace();
			
		     }catch (ClientProtocolException e) {
		    	 
		    	 
			e.printStackTrace();
		     } catch (IOException e) {
		    	 
		    	 
			e.printStackTrace();
		  }

		return sResponse;
	}
	
	}


