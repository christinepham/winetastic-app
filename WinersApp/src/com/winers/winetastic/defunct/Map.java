package com.winers.winetastic.defunct;

import com.winers.winetastic.AbstractActivity;
import com.winers.winetastic.R;
import com.winers.winetastic.R.string;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class Map extends AbstractActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_map);
        sendToGoogleMaps();
    }
  
        protected void sendToGoogleMaps() {
        	String url ="http://google.com";
        	if (!url.startsWith("https://") && !url.startsWith("http://")){
        	    url = "http://" + url;
        	}
        	Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        	
            //Intent i = new Intent(Intent.ACTION_VIEW); // Create a new intent - stating you want to 'view something'
            //i.setData(Uri.parse(url));  // Add the url data (allowing android to realise you want to open the browser)
            startActivity(i); // Go go go!
        }

		@Override
		protected int getTitleText() {
			// TODO Auto-generated method stub
			return R.string.title_activity_map;
		}

}
