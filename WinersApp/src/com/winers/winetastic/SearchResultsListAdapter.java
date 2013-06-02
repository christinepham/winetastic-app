package com.winers.winetastic;

import java.util.ArrayList;
import java.util.HashMap;

import com.winers.winetastic.R;
import com.winers.winetastic.defunct.ImageHelper;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SearchResultsListAdapter extends BaseAdapter{

	// -- Fields -- //
	private Activity activity;
	private static LayoutInflater inflater=null;
	public ImageLoader imageLoader; 
	private ArrayList<ArrayList<String>> wines;
	

	// -- Constructors --//
	public SearchResultsListAdapter (Activity a, ArrayList<ArrayList<String>> wines2) {
		activity = a;
		wines = wines2;	
		inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return wines.size();
	}

	/**
	 * need access to the wine info, guessing this is it
	 * - Victoria
	 * @see android.widget.Adapter#getItem(int)
	 * 
	 * I changed this because I changed "wines" from 
	 * HashMap<String, ArrayList<String>> to
	 * ArrayList<ArrayList<String>>
	 * 
	 * - Jack (5/28)
	 */
	@Override
	public Object getItem(int position) {
		return wines.get(position);
	}

	@Override
	public long getItemId(int position) {
		//Toast.makeText(activity, position, Toast.LENGTH_SHORT).show();
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

        ImageView img = (ImageView) vi.findViewById(R.id.list_image);		
		
		ArrayList<String> descriptions = wines.get(position);
		name.setText(descriptions.get(0));
		location.setText(descriptions.get(1));
		price.setText("$" + descriptions.get(2));
		
        // Set image
        //ImageLoader.loadFromWeb(descriptions.get(3), img);					

		return vi;
	}

}
