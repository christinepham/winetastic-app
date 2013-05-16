package com.winers.winetastic;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.winersapp.R;
import com.example.winersapp.R.layout;
import com.example.winersapp.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.widget.Toast;

public class SearchResults extends ListActivity {

	private HashMap<String, ArrayList<String>> wines;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
     
        
        
        wines = new HashMap<String, ArrayList<String>>();
        insertWines();
        SearchResultsListAdapter adapter = new SearchResultsListAdapter(this, wines);
//        ListView list = (ListView) findViewById(R.id.list)
        getListView().setAdapter(adapter);
        
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_search_results, menu);
        return true;
    }
    
    public void insertWines () {
    	ArrayList<String> temp = new ArrayList<String> ();
    	
    	
    	temp.add("Hess");
    	temp.add("Napa");
    	temp.add("$5.50");
    	wines.put("Hess", temp);
    	
    	temp = new ArrayList<String> ();
    	temp.add("Chardonnay");
    	temp.add("Sonoma");
    	temp.add("$10.50");
    	wines.put("Chardonnay", temp);
    	
    }
}
