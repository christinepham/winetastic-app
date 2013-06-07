package com.winers.winetastic.controller;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.winers.winetastic.WineryInfoPage;
import com.winers.winetastic.model.manager.WinetasticManager;

public class WineryIntentTask extends AsyncTask<String, Void, String> {
	
	//ProgressDialog dialog;
	Context context;
	
	public WineryIntentTask(Context context) {
		this.context = context;
	}
	
	@Override
	protected void onPreExecute() {
		// This is where the "searching" overlay will go
		super.onPreExecute();
		//dialog = ProgressDialog.show(context, "","Loading...");
	}
	
	@Override
	protected String doInBackground(String ... winery) {
    	return WinetasticManager.getWineryDetails(winery[0]);	
	}
	
	// This gets executed after doInBackground()
	@Override
	protected void onPostExecute(String result) {	
		//if(dialog.isShowing())
		//	dialog.dismiss();
		if (result != null) {
	    	Intent i = new Intent(context, WineryInfoPage.class);
	    	i.putExtra("winery_data", result);
	    	context.startActivity(i);
		} else {
			// No winery in database.
			Toast.makeText(context, "No winery associated with this wine.", Toast.LENGTH_SHORT).show();
		}
	}
}    