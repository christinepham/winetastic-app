package com.winers.winetastic;

import com.winers.winetastic.AbstractActivity;
import com.winers.winetastic.Home;
import com.winers.winetastic.R;
import com.winers.winetastic.R.id;
import com.winers.winetastic.R.layout;
import com.winers.winetastic.R.menu;
import com.winers.winetastic.R.string;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class Map extends AbstractActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        sendToGoogleMaps();
    }
  
        protected void sendToGoogleMaps() {
                String url = "http://google.com/maps?q=wineries"; // You could have this at the top of the class as a constant, or pass it in as a method variable, if you wish to send to multiple websites
                Intent i = new Intent(Intent.ACTION_VIEW); // Create a new intent - stating you want to 'view something'
                i.setData(Uri.parse(url));  // Add the url data (allowing android to realise you want to open the browser)
                startActivity(i); // Go go go!
        }

		@Override
		protected int getTitleText() {
			// TODO Auto-generated method stub
			return R.string.title_activity_map;
		}

}
