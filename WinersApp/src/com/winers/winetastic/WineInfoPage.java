package com.winers.winetastic;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.winers.winetastic.defunct.ImageHelper;

/**
 * Displays statistics and descriptors for a specific wine. 
 * 
 * @author Victoria Do
 */

public class WineInfoPage extends InfoPage {
	
	private	APISnoothResponseWineArray 	info;
	private WineSearchObject			searchObject;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get wine details from caller
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
        	Gson gson = new Gson();
        	this.info = gson.fromJson(extras.getString("wine_data"), APISnoothResponseWineArray.class);        	
        }
        
        // Set layout
        setContentView(R.layout.activity_info_wine);
        
        // Get wine detail and descriptor table elements
        this.statsTable = (TableLayout) findViewById(R.id.info_module_statistics);
       
        // Set name
        TextView namev = (TextView) findViewById(R.id.info_wine_name);
        namev.setText(info.name, TextView.BufferType.NORMAL);
        
        // Set region
        TextView regionv = (TextView) findViewById(R.id.info_wine_region);
        regionv.setText(info.region, TextView.BufferType.NORMAL);
        
        // Set image
        ImageView img = (ImageView) findViewById(R.id.info_pic);        
        ImageLoader.loadFromWeb(info.image, img);
        	
        
        // Set rating
        try {
        	float theRating = Float.parseFloat(info.snoothRank);
            RatingBar rating = (RatingBar) findViewById(R.id.info_rating);
            rating.setRating(theRating);        	
        } catch(NumberFormatException e) {
        	// no rating available
        }
        
        // Populate table
        if(!(info.type.equals("")))	 	addRow(statsTable, "Type", info.type);        
        if(!(info.varietal.equals(""))) addRow(statsTable, "Varietal", info.varietal);        
        if(!(info.price.equals(""))) 	addRow(statsTable, "Price", "$" + info.price);  
        if(!(info.vintage.equals(""))) 	addRow(statsTable, "Vintage", info.vintage);    
        if(info.winery != null)		 	addRow(statsTable, "Winery", info.winery);
        
        // TODO: Search similar wines
        searchObject = new WineSearchObject();
        searchObject.setColor(info.type);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.activity_info_wine, menu);
        return true;
    }
    
    public void openWinePrices(View v) {
    	Intent i = new Intent(Intent.ACTION_VIEW);
    	i.setData(Uri.parse(info.link + "/#aprices"));
    	startActivity(i);    	
    }
    
	/**
	 * Gets APISnoothResponseWinery object and passes to a new WineryInfoPage activity.
	 * Postcondition: Opens winery intent or displays error message if none found.
	 */    
    
    public void viewWineryInfo(View v) {
    	System.err.println("Winery clicked.");
    	new WineryIntentTask().execute();
    }
    
	private class WineryIntentTask extends AsyncTask<Void, Void, String> {
		
		@Override
		protected String doInBackground(Void... arg0) {
			System.err.println("Getting winery details.");
	    	return WinetasticManager.getWineryDetails(info.wineryID);	
		}
		
		// This gets executed after doInBackground()
		protected void onPostExecute(String result) {		
			if (result != null) {
		    	Intent i = new Intent(WineInfoPage.this, WineryInfoPage.class);
		    	i.putExtra("winery_data", result);
		    	startActivity(i);
			} else {
				// No winery in database.
				Toast.makeText(WineInfoPage.this, "No winery associated with this wine.", Toast.LENGTH_SHORT).show();
			}
		}
	}    
}

