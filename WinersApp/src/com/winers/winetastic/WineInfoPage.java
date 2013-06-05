package com.winers.winetastic;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.winers.winetastic.model.data.APISnoothResponseWineArray;
import com.winers.winetastic.model.data.WineSearchObject;
import com.winers.winetastic.model.manager.DatabaseHandler;
import com.winers.winetastic.model.manager.ImageLoader;
import com.winers.winetastic.model.manager.NetworkTaskManager;

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
        
        // DB test
        //db = new DatabaseHandler(getApplicationContext());
        //Toast.makeText(WineInfoPage.this, "User email: " + db.getUserDetails().get("email"), Toast.LENGTH_SHORT).show();
        
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
        
        Button addToWishlistButton = (Button) findViewById(R.id.info_button_add_wishlist);
        Button addToCellarButton = (Button) findViewById(R.id.info_button_add_cellar);
        Button buyButton = (Button) findViewById(R.id.info_button_purchase);
        
        addToWishlistButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				NetworkTaskManager.addToWishlist(WineInfoPage.this, info.name, info.code);
			}
		});	
        
        addToCellarButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				NetworkTaskManager.addToCellar(WineInfoPage.this, info.name, info.code);
			}
		});	
        
        
        buyButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Intent.ACTION_VIEW);
		    	i.setData(Uri.parse(info.link + "/#aprices"));	
		    	startActivity(i);  
			}
		});	
        	
        
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
    
    /* 
     * WineInfoPage was crashing when I clicked the Buy button so I created a listener for it
     * 6/3/13 - 2:45am
     * -Jack
     */
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
    	NetworkTaskManager.startWineryIntent(WineInfoPage.this, info.winery);
    }
}

