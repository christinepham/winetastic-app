package com.winers.winetastic.controller;

import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.winers.winetastic.DailyVine;
import com.winers.winetastic.model.data.APISnoothResponse;
import com.winers.winetastic.model.data.APISnoothResponseWineArray;
import com.winers.winetastic.model.manager.WinetasticManager;

/**
 * Network operations must be performed in an AsyncTask, so that's
 * what this class is for.
 * Postcondition: upon successful search of at least one result, user
 *                is redirected to the search results page.
 */
public class DailyVineIntentTask extends AsyncTask<Void, Void, String> {
	private String wineryResponse;
	private String wineResponse;
	private ProgressDialog dialog;
	
	private Context context;
    private Gson gson;
    private APISnoothResponse snoothResponse;
    private List<APISnoothResponseWineArray> wineAPIResponse;
    
    public DailyVineIntentTask(Context context) {
    	this.context = context;
    }
    
	@Override
	protected void onPreExecute() {
		// This is where the "searching" overlay will go
		super.onPreExecute();
		dialog = ProgressDialog.show(context, "","Loading...");
	}
	
	// This gets executed after onPreExecute()
	@Override
	protected String doInBackground(Void... arg0) {
		wineResponse = WinetasticManager.getRandomWine();
		gson = new Gson();
	    snoothResponse = gson.fromJson(wineResponse, APISnoothResponse.class);
	    wineAPIResponse = (List<APISnoothResponseWineArray>) snoothResponse.wineResults;
		wineryResponse = WinetasticManager.getWineryDetails(wineAPIResponse.get(0).wineryID);
		return "";
	}
	
	// This gets executed after doInBackground()
	protected void onPostExecute(String result) {
		if(dialog.isShowing())
			dialog.dismiss();
		Intent i = new Intent(context, DailyVine.class);
		i.putExtra("Search Query", wineResponse);
		i.putExtra("Winery", wineryResponse);
		context.startActivity(i);
	}
}