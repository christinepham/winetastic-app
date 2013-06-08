package com.winers.winetastic.controller;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.winers.winetastic.WineryInfoPage;
import com.winers.winetastic.model.manager.WinetasticManager;

public class WineryIntentTask extends AsyncTask<String, Void, String> {
	
	ProgressDialog dialog;
	Context context;
	private boolean isOnline;
	
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
		String rv = "";
		if (!SystemManager.isOnline(context)) {
			isOnline = false;
		} else {
			isOnline = true;
			rv = WinetasticManager.getWineryDetails(winery[0]);
		}
		return rv;
	}
	
	// This gets executed after doInBackground()
	@Override
	protected void onPostExecute(String result) {	
		if(dialog.isShowing())
			dialog.dismiss();
		if (!isOnline) {
			Toast.makeText(context.getApplicationContext(), "You must be connected to the Internet to use this feature", Toast.LENGTH_SHORT).show();
		} else {
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
}    