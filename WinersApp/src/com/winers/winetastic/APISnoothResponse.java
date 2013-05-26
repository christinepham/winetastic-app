package com.winers.winetastic;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class APISnoothResponse {
	
	@SerializedName("wines")
	public List<APISnoothResponseWineArray> wineResults;
	
	@SerializedName("meta")
	public APISnoothResponseMetaData metaResults;
	
}

class APISnoothResponseMetaData {
	public String results;
	public String status;
	public String errmsg;
	public String returned;
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
	
}