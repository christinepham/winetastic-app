package com.example.winersapp.Activities;

import com.example.winersapp.AddWine;
import com.example.winersapp.EventCalendar;
import com.example.winersapp.Map;
import com.example.winersapp.MyWines;
import com.example.winersapp.R;
import com.example.winersapp.ToastToShare;
import com.example.winersapp.WineAppSettings;
import com.example.winersapp.WineOfDay;
import com.example.winersapp.WineSearch;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button search_but = (Button)findViewById(R.id.search);
        Button my_wines_but = (Button)findViewById(R.id.myWines);
        Button cal_but = (Button)findViewById(R.id.calendar);
        Button map_but = (Button)findViewById(R.id.map);
        Button toast_but = (Button)findViewById(R.id.toast);
        Button add_but = (Button)findViewById(R.id.addWine);
        Button settings_but = (Button)findViewById(R.id.settingsw);
        Button wineOfDay = (Button)findViewById(R.id.wineOfDay);
        
        
        // search
        search_but.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this, WineSearch.class);
				startActivity(i);
			}
        });
        
        // my wines
        my_wines_but.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this, MyWines.class);
				startActivity(i);
			}
        });

        // event calendar 
        cal_but.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this, EventCalendar.class);
				startActivity(i);
			}
        });
        
        // map
        map_but.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this, Map.class);
				startActivity(i);
			}
        });
        
        // toast to share
        toast_but.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this, ToastToShare.class);
				startActivity(i);
			}
        });
       
        // add a wine
        add_but.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this, AddWine.class);
				startActivity(i);
			}
        });
        
        // settings
        settings_but.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this, WineAppSettings.class);
				startActivity(i);
			}
        });
        
        // wine of the day
        wineOfDay.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this, WineOfDay.class);
				startActivity(i);
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
    
}
