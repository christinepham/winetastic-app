package com.winers.winetastic;

import com.google.gson.annotations.SerializedName;


/**
 * Class to store the details of a winery from a "Winery Details" Snooth 
 * API call.
 * 
 * To get a populated APISnoothResponseWinery object in your code, you'll first
 * have to either do a search to get a wineryID or use one from a previous
 * search.
 * 
 * Remember you have to do the API call from the doInBackground() method of
 * a class that extends AsyncTask. Look at WineSearch.java for an example.
 * 
 */
public class APISnoothResponseWinery {
	
	@SerializedName("winery")
	public APISnoothResponseWineryDetails wineryDetails;
	
	// This class is in APISnoothResponse.java for now...
	@SerializedName("meta")
	public APISnoothResponseMetaData metaResults; 
	
}

/*
 * Helper class for APISnoothResponseWinery to store the actual winery details
 */
class APISnoothResponseWineryDetails {
	public String id;
    public String name;
    
    @SerializedName("num_wines")
    public String numWines;
    public String address;
    public String city;
    public String state;
    public String zip;
    public String country;
    public String phone;
    public String url;
    public String email;
    public String closed;
    public String lat;
    public String lng;
    public String image;
    public String description;
    
    public APISnoothResponseWineryDetails() {
    	id = "";
        name = "";
        numWines = "";
        address = "";
        city = "";
        state = "";
        zip = "";
        country = "";
        phone = "";
        url = "";
        email = "";
        closed = "";
        lat = "";
        lng = "";
        image = "";
        description = "";
    }
}