package com.winers.winetastic;

import java.util.ArrayList;

import com.google.gson.Gson;

import android.os.Bundle;
import android.view.Menu;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Displays statistics and descriptors for a specific wine. 
 * 
 * @author Victoria Do
 */

public class WineInfoPage extends InfoPage {
	
	private TableLayout					descTable;
	private	APISnoothResponseWineArray 	info;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get wine details from caller
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
        	Gson gson = new Gson();
        	this.info = gson.fromJson(extras.getString("wine_data"), APISnoothResponseWineArray.class);        	
        }
        System.out.println("info is " + info);
        
        // Set layout
        setContentView(R.layout.activity_info_wine);
        
        // Get wine detail and descriptor table elements
        this.statsTable = (TableLayout) findViewById(R.id.info_module_statistics);
        this.descTable = (TableLayout) findViewById(R.id.info_module_descriptors);
        
        // Set name
        TextView namev = (TextView) findViewById(R.id.info_wine_name);
        namev.setText(info.name, TextView.BufferType.NORMAL);
        
        // Set region
        TextView regionv = (TextView) findViewById(R.id.info_wine_region);
        regionv.setText(info.region, TextView.BufferType.NORMAL);
        
        // TODO: Set image
        
        
        // Populate table
        if(info.varietal != null) addRow(statsTable, "Varietal", info.varietal);        
        if(info.price != null) addRow(statsTable, "Price", info.price);  
        if(info.vintage != null) addRow(statsTable, "Vintage", info.vintage);    
        if(info.winery != null) addRow(statsTable, "Winery", info.winery);
        
        // TODO: Get descriptors
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.activity_wine_info_page, menu);
        return true;
    }
}

//package com.winers.winetastic;
//
//import com.example.winersapp.R;
//import com.example.winersapp.R.layout;
//import com.example.winersapp.R.menu;
//
//import android.os.Bundle;
//import android.app.Activity;
//import android.view.Menu;
//
//public class WineInfoPage extends Activity {
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_wine_info_page);
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.activity_wine_info_page, menu);
//        return true;
//    }
//}

