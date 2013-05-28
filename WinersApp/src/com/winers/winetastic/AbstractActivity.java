package com.winers.winetastic;

import com.winers.winetastic.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public abstract class AbstractActivity extends Activity {

	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
	  System.err.println("(AbstractActivity) onCreate() called");
	  super.onCreate(savedInstanceState);
	  System.err.println("AbstractActivity: super.onCreate successful.");	  
	  //check if customTitlebar is supported.
	  final boolean customTitleSupported = requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);        
	        if ( customTitleSupported ) {
	        	// if customTitlebar is supports, set the titlebar layout for it.
	            getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.mycustomtitle);
	        }
	        final TextView myTitleText = (TextView) findViewById(R.id.myTitle);
	        if ( myTitleText != null ) {
	            myTitleText.setText(getText(getTitleText()));
	        }
	  System.err.println("Exiting AbstractActivity onCreate method");
	 }
	 
	 /**
	  * Implement this method to return a string resource id from the strings.xml file
	  * 
	  * @return
	  */
	 protected abstract int getTitleText() ;
}
