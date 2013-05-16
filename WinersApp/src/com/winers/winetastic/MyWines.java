package com.winers.winetastic;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MyWines extends AbstractActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wines);
        
        Button retHome = (Button)findViewById(R.id.e_cal_home);
        retHome.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MyWines.this, Home.class);
				startActivity(i);
			}
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_my_wines, menu);
        return true;
    }

	@Override
	protected int getTitleText() {
		// TODO Auto-generated method stub
		return R.string.my_wines_title;
	}
}
