package com.example.winersapp;

import com.example.winersapp.R;
import com.example.winersapp.Activities.AbstractActivity;

import android.app.Activity;
import android.os.Bundle;

public class WineSearch extends AbstractActivity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wine_search);
	}

	@Override
	protected int getTitleText() {
		// TODO Auto-generated method stub
		return 0;
	}
}
