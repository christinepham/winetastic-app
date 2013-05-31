package com.winers.winetastic;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

/**
 * Displays statistics and descriptors for a specific wine. 
 * 
 * @author Victoria Do
 */

public class WineInfoPage extends InfoPage {
	
	private TableLayout					descTable;
	private	APISnoothResponseWineArray 	info;

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
        this.descTable = (TableLayout) findViewById(R.id.info_module_descriptors);
        
        // Set name
        TextView namev = (TextView) findViewById(R.id.info_wine_name);
        namev.setText(info.name, TextView.BufferType.NORMAL);
        
        // Set region
        TextView regionv = (TextView) findViewById(R.id.info_wine_region);
        regionv.setText(info.region, TextView.BufferType.NORMAL);
        
        // Set image
        Drawable d = ImageHelper.loadImageFromWeb(info.image);
        Toast.makeText(WineInfoPage.this, "" + d, Toast.LENGTH_SHORT).show();
        ImageView img = (ImageView) findViewById(R.id.info_pic);
        img.setImageDrawable(d);
        
        // Set rating
        try {
        	float theRating = Float.parseFloat(info.snoothRank);
            RatingBar rating = (RatingBar) findViewById(R.id.info_rating);
            rating.setRating(theRating);        	
        } catch(NumberFormatException e) {
        	// no rating available
        }
        
        // Populate table
        if(info.varietal != null) 	addRow(statsTable, "Varietal", info.varietal);        
        if(info.price != null) 		addRow(statsTable, "Price", "$" + info.price);  
        if(info.vintage != null) 	addRow(statsTable, "Vintage", info.vintage);    
        if(info.winery != null) 	addRow(statsTable, "Winery", info.winery);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.activity_wine_info_page, menu);
        return true;
    }
}

