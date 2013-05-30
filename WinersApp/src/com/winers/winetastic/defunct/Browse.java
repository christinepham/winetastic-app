package com.winers.winetastic.defunct;

import com.winers.winetastic.AbstractActivity;
import com.winers.winetastic.Home;
import com.winers.winetastic.R;
import com.winers.winetastic.R.id;
import com.winers.winetastic.R.layout;
import com.winers.winetastic.R.menu;
import com.winers.winetastic.R.string;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class Browse extends AbstractActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wine_app_settings);
        
        Button retHome = (Button)findViewById(R.id.e_cal_home);
        retHome.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Browse.this, Home.class);
				startActivity(i);
			}
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_wine_app_settings, menu);
        return true;
    }

	@Override
	protected int getTitleText() {
		// TODO Auto-generated method stub
		return R.string.wine_app_settings_title;
	}
}
