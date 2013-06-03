package com.winers.winetastic;

import com.google.gson.Gson;

import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

public class WineryInfoPage extends InfoPage {

	private	APISnoothResponseWineryDetails 	info;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.err.println("WineryInfoPage: Starting intent");
        
        // Get winery details from caller
        Bundle extras = getIntent().getExtras();    
        if(extras != null) {
        	System.err.println("extras non-null");
        	Gson gson = new Gson();
        	APISnoothResponseWinery obj = gson.fromJson(extras.getString("winery_data"), APISnoothResponseWinery.class);
        	info = obj.wineryDetails;
        }        
        
        setContentView(R.layout.activity_info_winery);               
        // Set name
        TextView namev = (TextView) findViewById(R.id.info_winery_name);
        namev.setText(info.name, TextView.BufferType.NORMAL);      
	
        // Set image
        ImageView img = (ImageView) findViewById(R.id.info_winery_pic);        
        ImageLoader.loadFromWeb(info.image, img);	
        
        // Set address
        TextView address1 = (TextView) findViewById(R.id.info_winery_address1);
        address1.setText(info.address, TextView.BufferType.NORMAL);
        
        TextView address2 = (TextView) findViewById(R.id.info_winery_address2); 
        address2.setText(info.city + ", " + info.state);
        
        TextView address3 = (TextView) findViewById(R.id.info_winery_address3); 
        address3.setText(info.country);        
        
        
        // Set description
//        TableLayout descTable = (TableLayout) findViewById(R.id.info_winery_module_desc);
//        if(info.description != null && !(info.description.equals(""))) {
//        	addRow(descTable, Html.fromHtml(info.description).toString());
//        } else {
//        	addRow(descTable, "No information available.");
//        }
        
        // Populate table
//        if(!(info.type.equals("")))	 	addRow(statsTable, "Type", info.type);        
//        if(!(info.varietal.equals(""))) addRow(statsTable, "Varietal", info.varietal);        
//        if(!(info.price.equals(""))) 	addRow(statsTable, "Price", "$" + info.price);  
//        if(!(info.vintage.equals(""))) 	addRow(statsTable, "Vintage", info.vintage);    
//        if(info.winery != null)		 	addRow(statsTable, "Winery", info.winery);        
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.activity_info_winery, menu);
        return true;
    }
}
