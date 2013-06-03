package com.winers.winetastic;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * Class to store a list of wines returned by a search
 * 
 * Remember you have to do the API call from the doInBackground() method of
 * a class that extends AsyncTask. Look at WineSearch.java for an example.
 * 
 */
public class APISnoothResponse {
	
	@SerializedName("wines")
	public List<APISnoothResponseWineArray> wineResults;
	
	@SerializedName("meta")
	public APISnoothResponseMetaData metaResults;
	
}



class APISnoothResponseWineArray {
	public String region;
	
	
	
	@SerializedName("snoothrank")
	public String snoothRank;
	
	@SerializedName("num_merchants")
	public String numMerchants;
	public String vintage;
	public String link;
	public String image;
	public String available;
	public String code;
	public String type;
	public String varietal;
	public String price;
	
	@SerializedName("num_reviews")
	public String numReviews;
	public String name;
	
	@SerializedName("winery_id")
	public String wineryID;
	public String winery;
	
	public APISnoothResponseWineArray() {
		 region = "";	
		 snoothRank = "";	
		 numMerchants = "";
		 vintage = "";
		 link = "";
		 image = "";
		 available = "";
		 code = "";
		 type = "";
		 varietal = "";
		 price = "";
		 numReviews = "";
		 name = "";		
		 wineryID = "";
		 winery = "";
	}
	
	
}

