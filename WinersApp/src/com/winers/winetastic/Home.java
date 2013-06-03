
package com.winers.winetastic;

import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.gson.Gson;

public class Home extends AbstractActivity {

	private AdvancedSearchAPICall advancedSearchAPICall;
	//private UserFunctions uF;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	System.err.println("Attempting to create");
    	System.out.println("hello");
        super.onCreate(savedInstanceState);
        //uF = new UserFunctions();
        if (!uF.isUserLoggedIn(getApplicationContext())) {
        	Intent i = new Intent(Home.this, Intro.class);
			startActivity(i);
        }
        
    	System.err.println("Created. Getting layout...");          
        setContentView(R.layout.activity_main);
    	System.err.println("Got layout.");   
    	
    	ImageButton homeButton = (ImageButton) findViewById(R.id.home_button);
    	homeButton.setVisibility(View.GONE);
        
    	//Button toIntro = (Button)findViewById(R.id.to_intro);
    	//Button logout = (Button)findViewById(R.id.logout);
    	Button search_but = (Button)findViewById(R.id.search);
        Button my_wines_but = (Button)findViewById(R.id.myWines);
       // Button cal_but = (Button)findViewById(R.id.calendar);
        Button map_but = (Button)findViewById(R.id.map);
 //       Button toast_but = (Button)findViewById(R.id.toast);
        Button add_but = (Button)findViewById(R.id.addWine);
 //       Button settings_but = (Button)findViewById(R.id.settingsw);
        ImageButton daily_vine_but = (ImageButton)findViewById(R.id.dailyVineButton);
        
        // search
        search_but.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Home.this, WineSearch.class);
				startActivity(i);
			}
        });
        
        // my wines
        my_wines_but.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Home.this, WineCellTabLayout.class);
				startActivity(i);
			}
        });

        // event calendar 
        /*
        cal_but.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Home.this, EventCalendar.class);
				startActivity(i);
			}
        });
        */
        // map
        map_but.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
		
				String url = "http://google.com/maps?q=wineries"; 
	        	Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
	            startActivity(i); // Go go go!
			}
        });
        
        /** Will add logout to the top banner so user has choice to logout at any time
         * 
         */
        
        /*
        logout.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				uF.logoutUser(getApplicationContext());
				Toast.makeText(Home.this, "You have been logged out", Toast.LENGTH_LONG).show();
				Intent i = new Intent(Home.this, Intro.class);
				startActivity(i);
			}
        });
        
        // FOR TESTING - go to Intro page
        toIntro.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Home.this, Intro.class);
				startActivity(i);
			}
        });*/
        
    
        
//        map_but.setOnClickListener(new View.OnClickListener(){
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Intent i = new Intent(Home.this, Map.class);
//				startActivity(i);
//			}
//        });
//        

        // toast to share
        /*
        toast_but.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Home.this, ToastToShare.class);
				startActivity(i);
			}
        });*/
       
        // add a wine
        add_but.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Home.this, AddWine.class);
				startActivity(i);
			}
        });
        
        // settings
        /*
        settings_but.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Home.this, WineAppSettings.class);
				startActivity(i);
			}
        });*/
        
        // daily vine
        daily_vine_but.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				// Start AsyncTask to perform network operation (API call)
				advancedSearchAPICall = new AdvancedSearchAPICall();
				advancedSearchAPICall.execute();
			}  	
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	protected int getTitleText() {
		// TODO Auto-generated method stub
		return R.string.app_name;
	}
	
	/**
	 * Network operations must be performed in an AsyncTask, so that's
	 * what this class is for.
	 * Postcondition: upon successful search of at least one result, user
	 *                is redirected to the search results page.
	 */
	class AdvancedSearchAPICall extends AsyncTask<Void, Void, String> {
		private String wineryResponse;
		private String wineResponse;
		
		String searchQuery;
	    Gson gson;
	    APISnoothResponse snoothResponse;
	    List<APISnoothResponseWineArray> wineAPIResponse;
	    
		@Override
		protected void onPreExecute() {
			// This is where the "searching" overlay will go
		}
		
		// This gets executed after onPreExecute()
		@Override
		protected String doInBackground(Void... arg0) {
			wineResponse = WinetasticManager.getRandomWine();
			gson = new Gson();
		    snoothResponse = gson.fromJson(wineResponse, APISnoothResponse.class);
		    wineAPIResponse = (List<APISnoothResponseWineArray>) snoothResponse.wineResults;
			wineryResponse = WinetasticManager.getWineryDetails(wineAPIResponse.get(0).wineryID);
			return "";
		}
		
		// This gets executed after doInBackground()
		protected void onPostExecute(String result) {
			Intent i = new Intent(Home.this, DailyVine.class);
			i.putExtra("Search Query", wineResponse);
			i.putExtra("Winery", wineryResponse);
			startActivity(i);
		}
	}
    
}
