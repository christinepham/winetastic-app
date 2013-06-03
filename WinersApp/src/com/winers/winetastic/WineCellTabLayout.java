package com.winers.winetastic;

import android.app.LocalActivityManager;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;

public class WineCellTabLayout extends AbstractActivity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wine_cell_tab_layout);
         
        TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
        //TabHost tabHost = getTabHost();
        
        
        LocalActivityManager mLocalActivityManager = new LocalActivityManager(this, false);
        mLocalActivityManager.dispatchCreate(savedInstanceState);
        tabHost.setup(mLocalActivityManager);
        Toast.makeText(this, "tabhost: "+ tabHost, Toast.LENGTH_SHORT).show();
        
        // Tab for MyWines
        TabSpec myWinesTab = tabHost.newTabSpec("My Wines");
        // setting Title and Icon for the Tab
        myWinesTab.setIndicator("Wine Collection");//, getResources().getDrawable(R.drawable.wines));
        Intent myWinesIntent = new Intent(this, MyWines.class);
        myWinesTab.setContent(myWinesIntent);
         
        // Tab for Wishlist 
        TabSpec wishListTab = tabHost.newTabSpec("Wish List");        
        wishListTab.setIndicator("Wish List");//, getResources().getDrawable(R.drawable.wishlist));
        Intent wishListIntenet = new Intent(this, WineWishList.class);
        wishListTab.setContent(wishListIntenet);
         
        // Adding all TabSpec to TabHost
        tabHost.addTab(myWinesTab); // Adding photos tab
        tabHost.addTab(wishListTab); // Adding songs tab
        
    }

	@Override
	protected int getTitleText() {
		// TODO Auto-generated method stub
		return R.string.my_wines_title;
	}	
	

}