package com.winers.winetastic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.winers.winetastic.R;
import com.winers.winetastic.R.layout;
import com.winers.winetastic.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.widget.Toast;

import com.google.gson.Gson;

public class SearchResults extends ListActivity {

	private HashMap<String, ArrayList<String>> wines;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
<<<<<<< HEAD
     
=======
        searchQuery = (String) getIntent().getExtras().get("Search Query");
        //Toast.makeText(this, searchQuery, Toast.LENGTH_SHORT).show();
        
        //Convert back to POJO
        Gson gson = new Gson();
        APISnoothResponse snoothResponse = gson.fromJson(searchQuery, APISnoothResponse.class);
>>>>>>> ab49d7f5551fc0bb07b4308d41c599513b97299f
        
        
        wines = new HashMap<String, ArrayList<String>>();
        insertWines(snoothResponse);
        SearchResultsListAdapter adapter = new SearchResultsListAdapter(this, wines);
//        ListView list = (ListView) findViewById(R.id.list)
        getListView().setAdapter(adapter);
        
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_search_results, menu);
        return true;
    }
    
    public void insertWines (APISnoothResponse sR) {
    	
    	List<APISnoothResponseWineArray> wineAPIResponse = sR.wineResults;
    	
    	ArrayList<String> temp;
    	
    	for (APISnoothResponseWineArray snoothWine : wineAPIResponse) {
    		temp = new ArrayList<String>();
    		temp.add(snoothWine.name);
    		temp.add(snoothWine.region);
    		temp.add(snoothWine.price);
    		wines.put(snoothWine.name, temp);
    	}
    	
    }
}
