package com.winers.winetastic;

//import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
//import java.net.URL;
//import java.nio.charset.Charset;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
//import org.json.JSONException;
//import org.json.JSONObject;

//import android.app.Dialog;
import android.app.ExpandableListActivity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
//import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.SearchView;
//import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

public class WineSearch extends ExpandableListActivity
implements OnChildClickListener {


	private ArrayList<String> groups = new ArrayList<String>();
	private ArrayList<Object> children = new ArrayList<Object>();
	private AdvancedSearchAdapter searchAdapter;
	
	//Added by Jack
	APISnoothCall apiCall = new APISnoothCall();


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wine_search);

		ExpandableListView searchOptions = getExpandableListView();
		searchOptions.setDividerHeight(2);
		searchOptions.setGroupIndicator(null);
		searchOptions.setClickable(true);
		
		

		setGroups();
		setChildren();

		searchAdapter = new AdvancedSearchAdapter(groups, children);
		searchAdapter.setInflater(
				(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE),
				this);
		getExpandableListView().setAdapter(searchAdapter);
		searchOptions.setOnChildClickListener(this);
		
		
		Button search = (Button) findViewById(R.id.search);
		search.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
<<<<<<< HEAD
				searchAdapter.search();
				Intent i = new Intent(WineSearch.this, SearchResults.class);
				startActivity(i);
=======
				//searchAdapter.search();
				//Intent i = new Intent(WineSearch.this, APISnoothCallActivity.class);
				//i.putExtra("Search Query", searchAdapter.getSelectedItems());
				//startActivity(i);
				
				apiCall = new APISnoothCall();
				apiCall.execute();
				
>>>>>>> ab49d7f5551fc0bb07b4308d41c599513b97299f
			}
			
		});
		
		
		Button reset = (Button) findViewById(R.id.reset);
		reset.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				searchAdapter.clear();
			}
			
		});
		
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		SearchView searchView = (SearchView) menu.findItem(R.id.search_bar);
		searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
		//searchView.setVisibility(SearchView.VISIBLE);	
		//searchView.setFocusable(false);



		InputMethodManager imm = (InputMethodManager)getSystemService(
				Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(searchView.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
		return true;
	}


	protected int getTitleText() {
		// TODO Auto-generated method stub
		return R.string.wine_search_title;
	}


	public void setGroups () {
		groups.add("Color");
		groups.add("Price");
		groups.add("Type");
		groups.add("Accent");
	}

	public void setChildren () {
		// Let's just hard-code the values here. What do you think?
		ArrayList<String> tempChild = new ArrayList<String>();

		// Color group
		tempChild.add("Red");
		tempChild.add("White");
		tempChild.add("Rose");
		children.add(tempChild);

		// Price group
		tempChild = new ArrayList<String>();
		tempChild.add("Less than $15/bottle");
		tempChild.add("$15 - $50/bottle");
		tempChild.add("$50 - $150/bottle");
		tempChild.add("Over $150/bottle");
		children.add(tempChild);

		// Type group
		tempChild = new ArrayList<String>();
		tempChild.add("Chardonnay");
		tempChild.add("Sauvignon Blanc");
		tempChild.add("Champagne");
		tempChild.add("Pinot Gris");
		tempChild.add("Reisling");
		tempChild.add("White Zinfandel");
		tempChild.add("Muscatto");
		tempChild.add("Port");
		tempChild.add("Sweet Sherry");
		tempChild.add("Pinot Noir");
		tempChild.add("Merlot");
		tempChild.add("Shiraz");
		tempChild.add("Sangiovese");
		tempChild.add("Cabernet Sauvignon");
		tempChild.add("Zinfandel");
		children.add(tempChild);

		// Accent group
		tempChild = new ArrayList<String>();
		tempChild.add("Acidic");
		tempChild.add("AgeWorthy");
		tempChild.add("Aggressive");
		children.add(tempChild);

	}

	@Override
	public boolean onChildClick(ExpandableListView parent, View v, int groupPos,
			int childPos, long id) {
		Toast.makeText(WineSearch.this, "Clicked On Child", Toast.LENGTH_SHORT).show();
		return true;
	}
	

	class APISnoothCall extends AsyncTask<Void, Void, String> {
		
		//Dialog searchingDialogBox;
		//TextView tvSearching;
		
		@Override
		protected void onPreExecute() {
			//super.onPreExecute();
			//searchingDialogBox = new Dialog(WineSearch.this);
			
			
			//searchingDialogBox.setCancelable(false);
			//searchingDialogBox.requestWindowFeature(Window.FEATURE_NO_TITLE);
			//searchingDialogBox.setContentView(R.layout.progressdialog);
			//tvSearching = (TextView) searchingDialogBox.findViewById(R.id.tv1);
			//searchingDialogBox.show();
		}
		
		@Override
		protected String doInBackground(Void... arg0) {
			
			WineSearchObject sP = searchAdapter.getSearchParameters();
			String url = "http://api.snooth.com/wines/?akey=7sft6abh56pjc52byts04mq9vpok18ufzksn5r4g92amybdy";
			url += "&q=napa";
			if (sP.getType() != "") 
				url += "+" + sP.getType();
			url += "&n=20";
			if (sP.getColor() != "") 
				url += "&color=" + sP.getColor();
			if (sP.getPrice() != "")
				url += sP.parsePriceString();
			url += "&t=wine";	
			InputStream source = retrieveStream(url);  
	        Gson gson = new Gson();
	        Reader reader = new InputStreamReader(source);
		    
	        APISnoothResponse snoothResponse = gson.fromJson(reader, APISnoothResponse.class);
	        return gson.toJson(snoothResponse);
		}
		
		protected void onPostExecute(String result) {
			//super.onPostExecute(result);
			//Toast.makeText(WineSearch.this, numResults, Toast.LENGTH_SHORT).show();
			//context.startActivity(new Intent(context, SearchResults.class));
			//searchingDialogBox.dismiss();
			
			//Integer resultLength = result.length();
			//Toast.makeText(WineSearch.this, resultLength.toString(), Toast.LENGTH_SHORT).show();
			
			if (result.length() > 80) {
				Intent i = new Intent(WineSearch.this, SearchResults.class);
				i.putExtra("Search Query", result);
				startActivity(i);
			} else {
				Toast.makeText(WineSearch.this, "No matches were found. Please try your search again.", Toast.LENGTH_SHORT).show();
			}
		}
		
		private InputStream retrieveStream(String url) {
	    	
	    	DefaultHttpClient client = new DefaultHttpClient(); 
	        
	        HttpGet getRequest = new HttpGet(url);
	          
	        try {
	           
	           HttpResponse getResponse = client.execute(getRequest);
	           final int statusCode = getResponse.getStatusLine().getStatusCode();
	           
	           if (statusCode != HttpStatus.SC_OK) { 
	              Log.w(getClass().getSimpleName(), "Error " + statusCode + " for URL " + url); 
	              return null;
	           }

	           HttpEntity getResponseEntity = getResponse.getEntity();
	           return getResponseEntity.getContent();
	           
	        } 
	        catch (IOException e) {
	           getRequest.abort();
	           Log.w(getClass().getSimpleName(), "Error for URL " + url, e);
	        }
	        
	        return null;
	        
	     }

	}
}
