package com.example.winersapp;

import com.example.winersapp.R;
import com.example.winersapp.Activities.AbstractActivity;
import com.example.winersapp.Activities.MainActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WineSearch extends AbstractActivity {
	
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

	@Override
	protected int getTitleText() {
		// TODO Auto-generated method stub
		return R.string.wine_search_title;
	}
}
