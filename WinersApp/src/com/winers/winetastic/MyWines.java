package com.winers.winetastic;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;

public class MyWines extends ListActivity {

	private ArrayList<ArrayList<String>> wines;

	private String myWinesQuery;

	private boolean removeMode = false;

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_wines);
		
//        //Convert back to POJO
//        final Gson gson = new Gson();
//        final APISnoothResponse snoothResponse = gson.fromJson(searchQuery, APISnoothResponse.class);
//        final List<APISnoothResponseWineArray> wineAPIResponse = snoothResponse.wineResults;
        
		
		myWinesQuery = (String) getIntent().getExtras().get("MyWines Query");
		
		System.err.println("MY WINES QUERY FROM INSIDE MY WIIIIIINESL: " + myWinesQuery);
		
		final Gson gson = new Gson();
        final APISnoothResponseMyWines myWinesResponse = gson.fromJson(myWinesQuery, APISnoothResponseMyWines.class);
        final List<APISnoothResponseMyWineArray> winesAPIResponse = myWinesResponse.myWineResults;
		
        wines = new ArrayList<ArrayList<String>>();
        // TODO: 
        // will have to populate from the database using users wine 
        insertWines(winesAPIResponse);
		SearchResultsListAdapter adapter = new SearchResultsListAdapter(this, wines);
		getListView().setAdapter(adapter);
		getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> av, View v, int pos,
					long id) {			

				Intent i = new Intent(MyWines.this, WineInfoPage.class);
				
				// HOLY SHIT HERE WE GO
				
				List<APISnoothResponseWineArray> wineArrayForInfoPage = new ArrayList<APISnoothResponseWineArray>();
				APISnoothResponseWineArray tempArray = new APISnoothResponseWineArray();
				for (APISnoothResponseMyWineArray wineZZZ : winesAPIResponse) {
		    		if (wineZZZ.cellared.equals("1")) {
		    			tempArray.code = wineZZZ.code;
		    			tempArray.image = wineZZZ.image;
		    			tempArray.link = wineZZZ.link;
		    			tempArray.name = wineZZZ.name;
		    			tempArray.price = wineZZZ.price;
		    			tempArray.region = wineZZZ.region;
		    			tempArray.type = wineZZZ.type;
		    			tempArray.varietal = wineZZZ.varietal;
		    			tempArray.winery = wineZZZ.winery;
		    			tempArray.snoothRank = wineZZZ.snoothRank;
		    			tempArray.wineryID = wineZZZ.wineryID;
		    			wineArrayForInfoPage.add(tempArray);
		    		}
		    	}
				String wineArraySerialized = gson.toJson(wineArrayForInfoPage.get(pos));
				

				//List<APISnoothResponseWineArray> wineAPIResponse = snoothResponse.wineResults;	
				//String wineArraySerialized = gson.toJson(wineAPIResponse.get(pos));

				i.putExtra("wine_data", wineArraySerialized);
				startActivity(i);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_search_results, menu);
		return true;
	}


	protected int getTitleText() {
		// TODO Auto-generated method stub
		return R.string.my_wines_title;
	}
	
	public void toggleRemove(View v) {
		removeMode = !removeMode;
		
		if(removeMode) {
			v.setBackgroundColor(getResources().getColor(R.color.red));
			((Button) v).setText(getResources().getString(R.string.removeModeTrue));
			
		} else {
			v.setBackgroundColor(getResources().getColor(R.color.charcoal));
			((Button) v).setText(getResources().getString(R.string.removeModeFalse));			
		}
	}
	
	
    public void insertWines (List<APISnoothResponseMyWineArray> winesArray) {
    	
    	ArrayList<String> temp;
    	
    	for (APISnoothResponseMyWineArray wineZZZ : winesArray) {
    		if (wineZZZ.cellared.equals("1")) {
		    	temp = new ArrayList<String>();
		    	temp.add(wineZZZ.name);
	    		temp.add(wineZZZ.region);
	    		temp.add(wineZZZ.price);
	    		temp.add(wineZZZ.image);
		    	wines.add(temp);
    		}
    	}
    }
}
