package com.winers.winetastic.defunct;

import com.winers.winetastic.R;
import com.winers.winetastic.R.layout;
import com.winers.winetastic.R.menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class Settings extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_settings, menu);
        return true;
    }
}
