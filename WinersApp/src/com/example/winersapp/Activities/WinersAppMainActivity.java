package com.example.winersapp.Activities;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

import com.example.winersapp.Choose;
import com.example.winersapp.Load;
import com.example.winersapp.R;
import com.example.winersapp.SearchBar;
import com.example.winersapp.R.layout;
import com.example.winersapp.R.menu;

public class WinersAppMainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);
        tv.setText("Test");
        setContentView(R.layout.activity_winers_app_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.winers_app_main, menu);
        return true;
    }
    
}
