package com.winers.winetastic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.winers.winetastic.R;
public class Intro extends Activity {
	
	private UserFunctions uF;
	private Handler mHandler = new Handler();	// Handles background rotation	
	private int currentFrame = 0;				// The current background image
	private static final int TRANSITION_TIME = 5000; // Milliseconds b/w bgs

    ImageView browse; 	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        uF = new UserFunctions();
        
        // Check if user is logged in. If so, redirect to Home screen
        /*
         * Uncomment the following to automatically take logged in user to
         * Home screen when app starts
         *
        
        if (uF.isUserLoggedIn(getApplicationContext())) {
        	Intent i = new Intent(Intro.this, Home.class);
			startActivity(i);
        }
        */
        
        setContentView(R.layout.activity_intro);
        
        
        
        // Views   
        Button home = (Button)findViewById(R.id.guest_home_button);  
        Button register = (Button)findViewById(R.id.guest_register_button);
        browse = (ImageView)findViewById(R.id.guest_find_wines); 	        
        
        // Click event: Go to the Browse Wines module
        browse.setOnClickListener(new View.OnClickListener(){
    		@Override
    		public void onClick(View v) {
    			System.out.println("baaahhh");
    			Intent i = new Intent(Intro.this, WineSearch.class);
    			startActivity(i);
    		}
        });  
        
        // Click event: Go to the Home Screen
        home.setOnClickListener(new View.OnClickListener(){
    		@Override
    		public void onClick(View v) {
    			if (!uF.isUserLoggedIn(getApplicationContext())) {
    				Toast.makeText(Intro.this, "You must be logged in to go to the Home screen", Toast.LENGTH_LONG).show();
    			}
    			else {
	        		System.err.println("Detected click for HOME.");    			
	    			Intent i = new Intent(Intro.this, Home.class);
	    			startActivity(i);
    			}
    		}
        });       
        
     // Click event: Go to the Home Screen
        register.setOnClickListener(new View.OnClickListener(){
    		@Override
    		public void onClick(View v) {
        		System.err.println("Detected click for REGISTER.");    			
    			Intent i = new Intent(Intro.this, RegisterAccount.class);
    			startActivity(i);
    		}
        });
        
    /*    
        // Click event: Go to info screen layout
        Button info = (Button)findViewById(R.id.guest_info_button);         
        info.setOnClickListener(new View.OnClickListener(){
    		@Override
    		public void onClick(View v) { 			
    			Intent i = new Intent(Intro.this, InfoPage.class);
    			startActivity(i);
    		}
        });     
        */    
        
//        // Go to maps
//        Button map = (Button)findViewById(R.id.guest_map);
//        map.setOnClickListener(new View.OnClickListener() {
//        	@Override
//        	public void onClick(View v) {
//        		Intent i = new Intent(Intro.this, Map.class);
//        		startActivity(i);
//        	}
//        });
        
        // Rotates background every x seconds 
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(8000);
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                changeBackground();
                            }
                        });
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
            }
        }).start();        
    }
    
    @Override
    public void onWindowFocusChanged (boolean hasFocus) {
    	super.onWindowFocusChanged(hasFocus);
    	
        // Set display size
//        ImageHelper.setDisplaySize(this);
//        System.out.println("Scaling... " + browse.getWidth());        
//        ImageHelper.scaleToScreenWidth(browse);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.winers_app_main, menu);
        return true;
    }
    
    /** Use Case: Login Window
     * Author: Victoria Do
     * Controls opening and closing the Login window.
     * @param view  The view to be filled with the login layout
     */
    
    
    public void showLogin(View view) { 	
    	System.out.println("SHOW LOGIN");
    	// Instantiate PopupWindow containing the login layout.
    	Intent intent = new Intent();
    	intent.setClass(this, Login.class);
    	startActivity(intent);
    }
    

    public void gotoMap(View view) {
    //	Intent i = new Intent(Intro.this, Map.class);
	//	startActivity(i);    	
    }

//    public void gotoMap(View view) {
//    	Intent i = new Intent(Intro.this, Map.class);
//		startActivity(i);    	
//    }

    
    /* Controls background rotation. 
     * Author: Victoria Do
     */
    private void changeBackground() { 	
    	RelativeLayout layout = (RelativeLayout) findViewById(R.id.intro_layout);
    	TransitionDrawable td = (TransitionDrawable) layout.getBackground().getCurrent();
    	if(currentFrame == 0) {
    		td.startTransition(TRANSITION_TIME);    	
    		currentFrame++;
    	} else {
    		td.reverseTransition(TRANSITION_TIME);
    		currentFrame--;
    	}
    }  
}

