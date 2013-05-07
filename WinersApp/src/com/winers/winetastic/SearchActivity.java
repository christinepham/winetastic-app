package com.winers.winetastic;

import com.example.winersapp.R;
import com.example.winersapp.R.layout;
import com.example.winersapp.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SearchActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_search);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.activity_search, menu);
        return true;
    }
}
