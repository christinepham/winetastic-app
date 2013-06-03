package com.winers.winetastic;

import com.google.gson.Gson;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.View;
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
        statsTable = (TableLayout) findViewById(R.id.info_winery_module_statistics);        
        
        // Set name
        TextView namev = (TextView) findViewById(R.id.info_winery_name);
        namev.setText(info.name, TextView.BufferType.NORMAL);      
	
        // Set image
        ImageView img = (ImageView) findViewById(R.id.info_winery_pic);        
        ImageLoader.loadFromWeb(info.image, img);	
        
        // Set address: If info.state exists, the winery is in the US
        TextView address1 = (TextView) findViewById(R.id.info_winery_address1);        
        TextView address2 = (TextView) findViewById(R.id.info_winery_address2);         
        TextView address3 = (TextView) findViewById(R.id.info_winery_address3);         
        
        address1.setText(info.address, TextView.BufferType.NORMAL);        
	    address2.setText(info.city + ", " + info.state + " " + info.zip);   
        if(!info.country.isEmpty()) {
        	if(!(info.zip.isEmpty())) 
        		address3.setText(info.zip);
        	else 
        		address3.setText("");
        } else {
        	if(!(info.country.equals(""))) address3.setText(info.country);
        	else address3.setText("");
        }
        
        // Set description
        TableLayout descTable = (TableLayout) findViewById(R.id.info_winery_module_desc);
        if(info.description == null || info.description.isEmpty()) {
        	addRow(descTable, "No information available.");
        } else {
        	String about = parseString(Html.fromHtml(info.description).toString(), 40, true);
        	addRow(descTable, about);        	
        }
        
        // Populate table
        int closed = Integer.valueOf((info.closed));
        if(closed != 0)					addRow(statsTable, "This winery is closed."); 
        if(!(info.phone.isEmpty()))	 	addRow(statsTable, "Number", info.phone);    
        if(!(info.email.isEmpty()))	 	addRow(statsTable, "Email", info.email);  
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.activity_info_winery, menu);
        return true;
    }
    
    public void openURL(View v) {
    	if(info.url != null && !(info.url.isEmpty())) {
	    	Intent i = new Intent(Intent.ACTION_VIEW);
	    	i.setData(Uri.parse(info.url));
	    	startActivity(i);      
    	}
    }    
    
    public void openInMaps(View v) {
    	String mapname = info.name;
    	String mapcountry = info.country;
    	String mapcity = info.city;
        String url = "http://google.com/maps?q=" + mapname + "+" + mapcity + "+" + mapcountry;
        Intent i = new Intent(Intent.ACTION_VIEW); // Create a new intent - stating you want to 'view something'
        i.setData(Uri.parse(url));  // Add the url data (allowing android to realise you want to open the browser)
        startActivity(i); // Go go go!    	
    }
}
