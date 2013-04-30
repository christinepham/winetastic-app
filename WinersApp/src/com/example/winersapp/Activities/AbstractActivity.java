package com.example.winersapp.Activities;

import com.example.winersapp.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public abstract class AbstractActivity extends Activity {

	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
	 
	  super.onCreate(savedInstanceState);
	  //check if customTitlebar is supported.
	  final boolean customTitleSupported = requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
	        setContentView(R.layout.activity_main);
	        if ( customTitleSupported ) {
	        	// if customTitlebar is supports, set the titlebar layout for it.
	            getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.mycustomtitle);
	        }
	 }
	 
	 /**
	  * Implement this method to return a string resource id from the strings.xml file
	  * 
	  * @return
	  */
	 protected abstract int getTitleText() ;
}
