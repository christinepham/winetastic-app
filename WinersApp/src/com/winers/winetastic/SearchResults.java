package com.winers.winetastic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import com.google.gson.Gson;

public class SearchResults extends ListActivity {


	private ArrayList<ArrayList<String>> wines;
	private String searchQuery;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search_results);
        searchQuery = (String) getIntent().getExtras().get("Search Query");
        //Toast.makeText(this, searchQuery, Toast.LENGTH_SHORT).show();
        
        //Convert back to POJO
        final Gson gson = new Gson();
        final APISnoothResponse snoothResponse = gson.fromJson(searchQuery, APISnoothResponse.class);
        final List<APISnoothResponseWineArray> wineAPIResponse = snoothResponse.wineResults;
        
        wines = new ArrayList<ArrayList<String>>();
        insertWines(wineAPIResponse);
        final SearchResultsListAdapter adapter = new SearchResultsListAdapter(this, wines);
//        ListView list = (ListView) findViewById(R.id.list)
        getListView().setAdapter(adapter);
        getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> av, View v, int pos,
					long id) {			

				
				Intent i = new Intent(SearchResults.this, WineInfoPage.class);
				
		    	List<APISnoothResponseWineArray> wineAPIResponse = snoothResponse.wineResults;	
		    	String wineArraySerialized = gson.toJson(wineAPIResponse.get(pos));

				i.putExtra("wine_data", wineArraySerialized);
				startActivity(i);
			}
        });
    }

//        setContentView(R.layout.activity_search_results);
//        searchQuery = (String) getIntent().getExtras().get("Search Query");
//        Toast.makeText(this, searchQuery, Toast.LENGTH_SHORT).show();
//        
//        wines = new HashMap<String, ArrayList<String>>();
//        insertWines();
//        SearchResultsListAdapter adapter = new SearchResultsListAdapter(this, wines);
////        ListView list = (ListView) findViewById(R.id.list)
//        getListView().setAdapter(adapter);
//        getListView().setOnItemClickListener(new OnItemClickListener() {

			
//			public void onItemClick(AdapterView<?> av, View v, int pos,
//					long id) {
//				// TODO Auto-generated method stub
//				Intent i = new Intent(SearchResults.this, WineInfoPage.class);
//				startActivity(i);
//			}
        	
       
        
        
        
   


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    //    getMenuInflater().inflate(R.menu.activity_search_results, menu);
        return true;
    }
    
    /*
     * I changed insertWines to use an integer for the mapping instead of a string
     * to make sure it kept the same order as the wineAPIResponse array. T
     */
    public void insertWines (List<APISnoothResponseWineArray> wineAPIResponse) {
    	
    	ArrayList<String> temp;
    	
    	for (APISnoothResponseWineArray snoothWine : wineAPIResponse) {
    		temp = new ArrayList<String>();
    		temp.add(snoothWine.name);
    		temp.add(snoothWine.region);
    		temp.add(snoothWine.price);
    		wines.add(temp);
    	}
    }
    
    public String parseQuery (String s) {
    	return "";
    }
    
}