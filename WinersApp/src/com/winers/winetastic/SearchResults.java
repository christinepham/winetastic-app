package com.winers.winetastic;

import java.util.ArrayList;
import java.util.HashMap;

import com.winers.winetastic.R;
import com.winers.winetastic.R.layout;
import com.winers.winetastic.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class SearchResults extends ListActivity {

	private HashMap<String, ArrayList<String>> wines;
	private String searchQuery;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        searchQuery = (String) getIntent().getExtras().get("Search Query");
        Toast.makeText(this, searchQuery, Toast.LENGTH_SHORT).show();
        
        wines = new HashMap<String, ArrayList<String>>();
        insertWines();
        SearchResultsListAdapter adapter = new SearchResultsListAdapter(this, wines);
//        ListView list = (ListView) findViewById(R.id.list)
        getListView().setAdapter(adapter);
        getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> av, View v, int pos,
					long id) {
				// TODO Auto-generated method stub
				Intent i = new Intent(SearchResults.this, WineInfoPage.class);
				startActivity(i);
			}
        	
        });
        
        
        
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
    
    public String parseQuery (String s) {
    	return "";
    }
    
}
