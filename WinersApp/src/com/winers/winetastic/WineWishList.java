package com.winers.winetastic;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class WineWishList extends Activity {

	private ArrayList<ArrayList<String>> wines;
	private boolean removeMode;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		
//		  final boolean customTitleSupported = requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);        
//	        if ( customTitleSupported ) {
//	        	// if customTitlebar is supports, set the titlebar layout for it.
//	            getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.mycustomtitle);
//	        }
//      final TextView myTitleText = (TextView) findViewById(R.id.myTitle);
//      if ( myTitleText != null ) {
//          myTitleText.setText(getText(getTitleText()));
//      }
//      
//	  System.err.println("Exiting AbstractActivity onCreate method");
		
		setContentView(R.layout.activity_wine_wish_list);

        wines = new ArrayList<ArrayList<String>>();
        // TODO: 
        // will have to populate from the database using users wine 
        insertWines();
		SearchResultsListAdapter adapter = new SearchResultsListAdapter(this, wines);
		ListView lv = (ListView) findViewById(android.R.id.list);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> av, View v, int pos,
					long id) {			


				Intent i = new Intent(WineWishList.this, WineInfoPage.class);

				//List<APISnoothResponseWineArray> wineAPIResponse = snoothResponse.wineResults;	
				//String wineArraySerialized = gson.toJson(wineAPIResponse.get(pos));

				//i.putExtra("wine_data", wineArraySerialized);
				startActivity(i);
			}
		});

	 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.wine_wish_list, menu);
		return true;
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
	
	protected int getTitleText() {
		// TODO Auto-generated method stub
		return R.string.wish_list_title;
	}
	
    public void insertWines () {
    	
    	ArrayList<String> temp;
    	
    	temp = new ArrayList<String>();
    	temp.add("Hess numba 2");
    	temp.add("Napa");
    	temp.add("10.50");
    	wines.add(temp);
    }
    
	
}
