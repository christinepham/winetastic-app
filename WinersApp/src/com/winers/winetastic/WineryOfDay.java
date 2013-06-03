package com.winers.winetastic;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class WineryOfDay extends Fragment {
	
	private TableLayout					statsTable;
	
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = (View) inflater.inflate(R.layout.fragment_winery_of_day, container, false);
        
        // Get wine detail table elements
        statsTable = (TableLayout) rootView.findViewById(R.id.info_module_winery_statistics);
        
        // Populate table
        if(getArguments().getString("random_winery_name","random_winery_name") != null) 
        	addRow(statsTable, "Name", getArguments().getString("random_winery_name","random_winery_name"));
        
        return rootView;
    }
    
    /**
     * Attaches a row to a TableLayout.
     * 
     * @param parent  the TableLayout view to attach the row to
     * @param cols    text to be added to the row
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