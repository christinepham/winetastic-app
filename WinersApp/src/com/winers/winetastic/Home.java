package com.winers.winetastic;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Home extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	System.err.println("Attempting to create");
        super.onCreate(savedInstanceState);
    	System.err.println("Created. Getting layout...");          
        setContentView(R.layout.activity_main);
    	System.err.println("Got layout.");        
        
        Button search_but = (Button)findViewById(R.id.search);
        Button my_wines_but = (Button)findViewById(R.id.myWines);
        Button cal_but = (Button)findViewById(R.id.calendar);
        Button map_but = (Button)findViewById(R.id.map);
 //       Button toast_but = (Button)findViewById(R.id.toast);
        Button add_but = (Button)findViewById(R.id.addWine);
 //       Button settings_but = (Button)findViewById(R.id.settingsw);
        //Button wineOfDay = (Button)findViewById(R.id.wineOfDay);
 //       ImageButton wineOfDay = (ImageButton)findViewById(R.id.wineOfDay);
        
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
				Intent i = new Intent(Home.this, MyWines.class);
				startActivity(i);
			}
        });

        // event calendar 
        cal_but.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Home.this, EventCalendar.class);
				startActivity(i);
			}
        });
        
        // map
        map_but.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			//	Intent i = new Intent(Home.this, Map.class);
			//	startActivity(i);
			}
        });
        
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
        
        // wine of the day
   /*     wineOfDay.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this, WineOfDay.class);
				startActivity(i);
			}  	
        });*/
        
        /*
        wineOfDay.setOnClickListener(new View.OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
                myWebLink.setData(Uri.parse("http://www.heartsmartwine.com/wine/5/2008_Napa_Valley_Cabernet_Sauvignon_Great_Dane.html"));
                    startActivity(myWebLink);
        	}
        });*/
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
    
}