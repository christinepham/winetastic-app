package com.winers.winetastic;

import java.util.ArrayList;

import com.example.winersapp.R;

import android.app.Activity;
import android.app.ExpandableListActivity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.SearchView;
import android.widget.Toast;

public class WineSearch extends ExpandableListActivity 
implements OnChildClickListener {


	private ArrayList<String> groups = new ArrayList<String>();
	private ArrayList<Object> children = new ArrayList<Object>();
	private AdvancedSearchAdapter searchAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wine_search);

		ExpandableListView searchOptions = getExpandableListView();
		searchOptions.setDividerHeight(2);
		searchOptions.setGroupIndicator(null);
		searchOptions.setClickable(true);

		
		
		setGroups();
		setChildren();

		searchAdapter = new AdvancedSearchAdapter(groups, children);
		searchAdapter.setInflater(
				(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE),
				this);
		getExpandableListView().setAdapter(searchAdapter);
		searchOptions.setOnChildClickListener(this);
		
		
		Button search = (Button) findViewById(R.id.search);
		search.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				searchAdapter.search();
			}
			
		});
		
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		SearchView searchView = (SearchView) menu.findItem(R.id.search_bar);
		searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
		//searchView.setVisibility(SearchView.VISIBLE);	
		//searchView.setFocusable(false);



		InputMethodManager imm = (InputMethodManager)getSystemService(
				Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(searchView.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
		return true;
	}


	protected int getTitleText() {
		// TODO Auto-generated method stub
		return R.string.wine_search_title;
	}


	public void setGroups () {
		groups.add("Color");
		groups.add("Season");
		groups.add("Type");
		groups.add("Accent");
	}

	public void setChildren () {
		// will have to access database for some of these values, but for now we are using these
		ArrayList<String> tempChild = new ArrayList<String>();

		// Color group
		tempChild.add("Red");
		tempChild.add("White");
		tempChild.add("Rose");
		tempChild.add("Sparkling");
		children.add(tempChild);

		// Season group
		tempChild = new ArrayList<String>();
		tempChild.add("Fall");
		tempChild.add("Winter");
		tempChild.add("Spring");
		tempChild.add("Summer");
		children.add(tempChild);

		// Type group
		tempChild = new ArrayList<String>();
		tempChild.add("Chardonnay");
		tempChild.add("Sauvignon Blanc");
		tempChild.add("Pinot Gris");
		children.add(tempChild);

		// Accent group
		tempChild = new ArrayList<String>();
		tempChild.add("Acidic");
		tempChild.add("AgeWorthy");
		tempChild.add("Aggressive");
		children.add(tempChild);

	}

	@Override
	public boolean onChildClick(ExpandableListView parent, View v, int groupPos,
			int childPos, long id) {
		Toast.makeText(WineSearch.this, "Clicked On Child",
				Toast.LENGTH_SHORT).show();
		return true;
	}
}
