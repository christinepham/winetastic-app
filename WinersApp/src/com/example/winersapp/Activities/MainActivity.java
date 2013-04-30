package com.example.winersapp.Activities;

import com.example.winersapp.R;
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
        Button myWine = (Button)findViewById(R.id.search);
        myWine.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this, WineSearch.class);
				startActivity(i);
			}
        });
        
        Button wineOfDay = (Button)findViewById(R.id.wineOfDay);
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
		return 0;
	}
    
}
