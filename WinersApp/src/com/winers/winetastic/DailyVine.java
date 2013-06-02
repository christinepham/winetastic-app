package com.winers.winetastic;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

public class DailyVine extends FragmentActivity {
	
	String searchQuery; 
	
    //Convert back to POJO
    Gson gson;
    APISnoothResponse snoothResponse;
    List<APISnoothResponseWineArray> wineAPIResponse;
    List<APISnoothResponseWineArray> wineAPIResponsePass;
    String wineArraySerialized;
    
	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_daily_vine);
		
		searchQuery = (String) getIntent().getExtras().get("Search Query");
		
	    //Convert back to POJO
	    gson = new Gson();
	    snoothResponse = gson.fromJson(searchQuery, APISnoothResponse.class);
	    wineAPIResponse = (List<APISnoothResponseWineArray>) snoothResponse.wineResults;
	    wineAPIResponsePass = snoothResponse.wineResults;	
    	wineArraySerialized = gson.toJson(wineAPIResponsePass.get(0));
	    
		// Create the adapter that will return a fragment for each of the two
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);	
		
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.daily_vine, menu);
		return true;
	}

    public void openInBrowser(View v) {
    	Intent i = new Intent(Intent.ACTION_VIEW);
    	i.setData(Uri.parse(wineAPIResponse.get(0).link + "/#aprices"));
    	startActivity(i);    	
    }
  
	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}


		@Override
		public Fragment getItem(int position) {
			
			Fragment fragment = new Fragment();
			Bundle args = new Bundle();  
			args.putString("random_wine_name", wineAPIResponse.get(0).name);
			args.putString("random_wine_price", wineAPIResponse.get(0).price);
			args.putString("random_wine_region", wineAPIResponse.get(0).region);
			args.putString("random_wine_varietal", wineAPIResponse.get(0).varietal);
			args.putString("random_wine_type", wineAPIResponse.get(0).type);
			args.putString("random_wine_vintage", wineAPIResponse.get(0).vintage);
			args.putString("random_wine_image", wineAPIResponse.get(0).image);
			args.putString("random_wine_link", wineAPIResponse.get(0).link);
			args.putString("wine_data", wineArraySerialized);
			
			switch (position) {
			case 0:
				fragment = new WineryOfDay();
				break;
			case 1:
				fragment = new WineOfDay();
				break;
			}
			fragment.setArguments(args);
			return fragment;
			
		}

		@Override
		public int getCount() {
			// Show 2 total pages.
			return 2;
		}

		// Titles for each Fragment ("From the Vine" and "To the Cellar")
		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.fromTheVine).toUpperCase(l);
			case 1:
				return getString(R.string.toTheCellar).toUpperCase(l);
			}
			return null;
		}

	}
}