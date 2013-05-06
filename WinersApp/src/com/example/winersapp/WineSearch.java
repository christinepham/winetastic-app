package com.example.winersapp;

import com.example.winersapp.R;
import com.example.winersapp.Activities.AbstractActivity;
import com.example.winersapp.Activities.MainActivity;

import android.app.Activity;
import android.app.ExpandableListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WineSearch extends ExpandableListActivity {
	
	private String[] categories 	= {"Color", "Season", "Type", "Accent"};
	private String[] colors 		= {"Red", "White", "Rose", "Sparkling"};
	private String[] seasons 		= {"Fall", "Winter", "Spring", "Summer"};
	private String[] types 			= {"Chardonnay","Sauvignon Blanc", "Pinot Gris",
							  		   "Merlot", "Shiraz", "Cabernet Sauvignon"};
	private String[] accents 		= {"Acidic", "Acrid", "Ageworthy", "Aggressive"}; 
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wine_search);
        
        Button retHome = (Button)findViewById(R.id.e_cal_home);
        retHome.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(WineSearch.this, MainActivity.class);
				startActivity(i);
			}
        });
	}
	
	

	protected int getTitleText() {
		// TODO Auto-generated method stub
		return R.string.wine_search_title;
	}
}
