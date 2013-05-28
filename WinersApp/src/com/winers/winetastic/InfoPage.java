package com.winers.winetastic;

import com.winers.winetastic.R;
import android.app.Activity;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/** 
 * Info page template for displaying statistics and information.
 * 
 * @author Victoria Do
 * @see WineInfoPage
 *
 */

public class InfoPage extends AbstractActivity {
	
	protected  TableLayout  statsTable;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_wine_info_page, menu);
        return true;
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
    	TableRow r = (TableRow) getLayoutInflater().inflate(R.layout.info_row, null);
    	
    	for(int i = 0; i < cols.length; i++) {
    		
    		// Add each string to the row
    		TextView text = new TextView(this);
    		
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

	@Override
	protected int getTitleText() {
		return R.string.info_title;
	}
}
