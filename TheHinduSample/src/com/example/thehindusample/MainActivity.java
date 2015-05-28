package com.example.thehindusample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

	TextView list,map;
	
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        list = (TextView)findViewById(R.id.place_txt);
       
        list.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent listintent = new Intent(MainActivity.this, ListActivity.class);
				startActivity(listintent);
				
			}
		});
   
        
    }


}
