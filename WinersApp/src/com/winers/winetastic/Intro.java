package com.winers.winetastic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;

import com.example.winersapp.R;

public class Intro extends Activity {
	
	PopupWindow loginWindow;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winers_app_main);
        
        // Click event: Go to the Browse Wines module
        Button browse = (Button)findViewById(R.id.guest_find_wines);
        browse.setOnClickListener(new View.OnClickListener(){
    		@Override
    		public void onClick(View v) {
    			Intent i = new Intent(Intro.this, WineSearch.class);
    			startActivity(i);
    		}
        });   
        
        // Click event: Go to the Home Screen
        Button home = (Button)findViewById(R.id.guest_home_button);
        home.setOnClickListener(new View.OnClickListener(){
    		@Override
    		public void onClick(View v) {
    			Intent i = new Intent(Intro.this, Home.class);
    			startActivity(i);
    		}
        });           
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.winers_app_main, menu);
        return true;
    }
    
    /* Use Case: Login Window
     * Controls opening and closing the Login window.
     */
    public void showLogin(View view) {
    	// Instantiate PopupWindow containing the login layout.
    	LayoutInflater inflater = (LayoutInflater)
    		       this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);    	
    	loginWindow = new PopupWindow(
    			inflater.inflate(R.layout.activity_login, null, false), 
    			LayoutParams.WRAP_CONTENT,
    			LayoutParams.WRAP_CONTENT,
    			true);
    	loginWindow.showAtLocation(this.findViewById(R.id.main), Gravity.CENTER, 0, 0); 
    }
    
    public void closeLogin(View view) {
    	loginWindow.dismiss();
    }
    
   
}


