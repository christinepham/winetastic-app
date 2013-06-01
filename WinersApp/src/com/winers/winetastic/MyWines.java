package com.winers.winetastic;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.AdapterView.OnItemClickListener;

public class MyWines extends ListActivity {

	private ArrayList<ArrayList<String>> wines;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_results);
		
//        //Convert back to POJO
//        final Gson gson = new Gson();
//        final APISnoothResponse snoothResponse = gson.fromJson(searchQuery, APISnoothResponse.class);
//        final List<APISnoothResponseWineArray> wineAPIResponse = snoothResponse.wineResults;
        
		
        wines = new ArrayList<ArrayList<String>>();
        // TODO: 
        // will have to populate from the database using users wine 
        insertWines();
		SearchResultsListAdapter adapter = new SearchResultsListAdapter(this, wines);
		getListView().setAdapter(adapter);
		getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> av, View v, int pos,
					long id) {			


				Intent i = new Intent(MyWines.this, WineInfoPage.class);

				//List<APISnoothResponseWineArray> wineAPIResponse = snoothResponse.wineResults;	
				//String wineArraySerialized = gson.toJson(wineAPIResponse.get(pos));

				//i.putExtra("wine_data", wineArraySerialized);
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
	
	
    public void insertWines () {
    	
    	ArrayList<String> temp;
    	
    	temp = new ArrayList<String>();
    	temp.add("Hess");
    	temp.add("Napa");
    	temp.add("10.50");
    	wines.add(temp);
    }
}
