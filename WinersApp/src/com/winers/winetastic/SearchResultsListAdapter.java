package com.winers.winetastic;

import java.util.ArrayList;
import java.util.HashMap;

import com.winers.winetastic.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SearchResultsListAdapter extends BaseAdapter{

	// -- Fields -- //
	private Activity activity;
	private static LayoutInflater inflater=null;
	public ImageLoader imageLoader; 
	private HashMap<String, ArrayList<String>> wines;
	

	// -- Constructors --//
	public SearchResultsListAdapter (Activity a, HashMap<String, ArrayList<String>> w) {
		activity = a;
		wines = w;	
		inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return wines.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		View vi=convertView;
		if(convertView==null)
			vi = inflater.inflate(R.layout.search_results_row, null);

		TextView name = (TextView) vi.findViewById(R.id.name);
		TextView location = (TextView) vi.findViewById(R.id.location);
		TextView price = (TextView) vi.findViewById(R.id.price);
		int i = 0;
		String currWine = null;
		//ArrayList<String> wine_names = (ArrayList<String>)wines.keySet();
		for (String wine : wines.keySet()) {
			if (i == position) {
				currWine = wine;
				break;
			}
			++i;

		}
		ArrayList<String> descriptions = wines.get(currWine);
		name.setText(descriptions.get(0));
		location.setText(descriptions.get(1));
		price.setText(descriptions.get(2));

		return vi;
	}

	private class ImageLoader {
		public ImageLoader() {

		}
	}

}
