package com.winers.winetastic;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
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
	private DatabaseHandler db;

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
				new AddToWishlist().execute();
			}
		});	
        
        addToCellarButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new AddToCellar().execute();
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
    	new WineryIntentTask().execute();
    }
    
    private class AddToWishlist extends AsyncTask<Void, Void, String> {
    	private boolean hasWine = false;
    	ProgressDialog dialog;
    	boolean isGuest;
    	
    	@Override
		protected void onPreExecute() {
			// This is where the "searching" overlay will go
			super.onPreExecute();
			dialog = ProgressDialog.show(WineInfoPage.this, "","Loading...");
		}
    	
    	@Override
		protected String doInBackground(Void... arg0) {
			System.err.println("Adding wine to wishlist.");
			UserFunctions uf = new UserFunctions();
			if (!uf.isUserLoggedIn(getApplicationContext())) {
				isGuest = true;
			}
				else {
				db = new DatabaseHandler(getApplicationContext());
				String email = db.getUserDetails().get("email");
				if (WinetasticManager.isWineInWishlist(email, info.code)) {
					hasWine = true;
				} else {
					WinetasticManager.addWineToWishlist(email, info.code);	
				}
			}
	    	return "";
		}
    	
    	protected void onPostExecute(String result) {
    		if(dialog.isShowing())
				dialog.dismiss();
    		if (isGuest) {
    			Toast.makeText(getApplicationContext(), "You must be logged in to use this feature", Toast.LENGTH_SHORT).show();
    		} else {
	    		if (hasWine) {
	    			Toast.makeText(WineInfoPage.this, info.name + " is already in your Wishlist", Toast.LENGTH_SHORT).show();
	    		} else {
	    			Toast.makeText(WineInfoPage.this, info.name + " has been added to your Wishlist", Toast.LENGTH_SHORT).show();
	    		}
    		}
    	}
    }
    
    private class AddToCellar extends AsyncTask<Void, Void, String> {
    	private boolean hasWine = false;
    	ProgressDialog dialog;
    	boolean isGuest = false;
    	
    	@Override
		protected void onPreExecute() {
			// This is where the "searching" overlay will go
			super.onPreExecute();
			dialog = ProgressDialog.show(WineInfoPage.this, "","Loading...");
		}
    	
    	@Override
		protected String doInBackground(Void... arg0) {
			System.err.println("Adding wine to cellar.");
			UserFunctions uf = new UserFunctions();
			if (!uf.isUserLoggedIn(getApplicationContext())) {
				isGuest = true;
			}
				else {
				db = new DatabaseHandler(getApplicationContext());
				String email = db.getUserDetails().get("email");
				if (WinetasticManager.isWineInCellar(email, info.code)) {
					hasWine = true;
				} else {
					WinetasticManager.addWineToCellar(email, info.code);	
				}
			}
	    	return "";
		}
    	
    	protected void onPostExecute(String result) {
    		if(dialog.isShowing())
				dialog.dismiss();
    		if (isGuest) {
    			Toast.makeText(getApplicationContext(), "You must be logged in to use this feature", Toast.LENGTH_SHORT).show();
    		} else {
	    		if (hasWine) {
	    			Toast.makeText(WineInfoPage.this, info.name + " is already in your Cellar", Toast.LENGTH_SHORT).show();
	    		} else {
	    			Toast.makeText(WineInfoPage.this, info.name + " has been added to your Cellar", Toast.LENGTH_SHORT).show();
	    		}
    		}
    	}
    }
    
	private class WineryIntentTask extends AsyncTask<Void, Void, String> {
		
		ProgressDialog dialog;
		
		@Override
		protected void onPreExecute() {
			// This is where the "searching" overlay will go
			super.onPreExecute();
			dialog = ProgressDialog.show(WineInfoPage.this, "","Loading...");
		}
		
		@Override
		protected String doInBackground(Void... arg0) {
			System.err.println("Getting winery details.");
	    	return WinetasticManager.getWineryDetails(info.wineryID);	
		}
		
		// This gets executed after doInBackground()
		protected void onPostExecute(String result) {	
			if(dialog.isShowing())
				dialog.dismiss();
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

