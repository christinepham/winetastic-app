package com.winers.winetastic;

import com.example.winersapp.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class AddWine extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_wine);
        
        
        Button retHome = (Button)findViewById(R.id.e_cal_home);
        retHome.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(AddWine.this, Home.class);
				startActivity(i);
			}
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_add_wine, menu);
        return true;
    }
}
