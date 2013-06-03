package com.winers.winetastic;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Displays information about the random wine of the day.
 * 
 * @author Helena
 *
 */
public class WineOfDay extends Fragment {	
	
	private TableLayout					statsTable;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = (View) inflater.inflate(R.layout.fragment_wine_of_day, container, false);
        
		Button b = (Button) rootView.findViewById(R.id.info_button_more_info);
		
		b.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity().getBaseContext(), WineInfoPage.class);
				i.putExtra("wine_data", getArguments().getString("wine_data", "wineArraySerialized"));
				startActivity(i);
			}  	
	    });
	        
        
        // Get wine detail table elements
        statsTable = (TableLayout) rootView.findViewById(R.id.info_module_wod_statistics);
        
        
        // Set image
        ImageView img = (ImageView) rootView.findViewById(R.id.imageView2);
        ImageLoader.loadFromWeb(getArguments().getString("random_wine_image", "random_wine_image"), img);
        
        
        // Populate table
        if(getArguments().getString("random_wine_name","random_wine_name") != null) 
        	addRow(statsTable, Html.fromHtml("Name").toString(), getArguments().getString("random_wine_name","random_wine_name"));    
        if(getArguments().getString("random_wine_price","random_wine_price") != null) 
        	addRow(statsTable, "Price", getArguments().getString("random_wine_price","random_wine_price"));    
        if(getArguments().getString("random_wine_price","random_wine_price") != null) 
        	addRow(statsTable, Html.fromHtml("Region").toString(), getArguments().getString("random_wine_region","random_wine_region"));    
        if(getArguments().getString("random_wine_price","random_wine_price") != null) 
        	addRow(statsTable, "Varietal", getArguments().getString("random_wine_varietal","random_wine_varietal"));    
        if(getArguments().getString("random_wine_price","random_wine_price") != null) 
        	addRow(statsTable, "Type", getArguments().getString("random_wine_type","random_wine_type"));    
        if(getArguments().getString("random_wine_price","random_wine_price") != null) 
        	addRow(statsTable, "Vintage", getArguments().getString("random_wine_vintage","random_wine_vintage"));
		
        return rootView;
    }
    
    /**
     * Attaches a row to a TableLayout.
     * 
     * @param parent  the TableLayout view to attach the row to
     * @param cols    text to be added to the row
     * 
     * @author Victoria
     */
    protected void addRow(View parent, String ... cols) {
    	// Get width of parent view
    	int width = parent.getLayoutParams().width;
    	
    	// Create a table row
    	TableRow r = (TableRow) getActivity().getLayoutInflater().inflate(R.layout.info_row, null);
    	
    	for(int i = 0; i < cols.length; i++) {
    		
    		// Add each string to the row
    		TextView text = new TextView(getActivity());
    		
    		// Bold the first string
    		if(i == 0 && cols.length > 1) {
    			text.setTypeface(Typeface.DEFAULT_BOLD, Typeface.BOLD);
    			text.setWidth(100);
    		}
    		
    		// Set contents of text, make black, change width
    		text.setText(cols[i], TextView.BufferType.NORMAL);
    		text.setTextColor(getResources().getColor(R.color.black));
    		
    		r.addView(text);
    	}    	
    	((TableLayout)parent).addView(r);
    }

}
