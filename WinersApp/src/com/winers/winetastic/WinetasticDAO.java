package com.winers.winetastic;

import java.security.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

import com.google.gson.Gson;

public class WinetasticDAO {
	private final String API_KEY = "7sft6abh56pjc52byts04mq9vpok18ufzksn5r4g92amybdy";
	private final String SNOOTH_URL = "http://api.snooth.com";
	private final String WINE_RESOURCE_ID = "/wines/";
	private final String WINERY_RESOURCE_ID = "/winery/";
	private final String MY_WINES_RESOURCE_ID = "/my-wines/";
	private final String RATE_WINE_RESOURCE_ID = "/rate/";
	private final String CREATE_ACCOUNT_RESOURCE_ID = "/create-account/";
	private final String RANDOM_STRING = "k8d9j5h8g4u7tr4";
	private final int API_RETVAL_LENGTH_FOR_ERROR = 120;
	
	
	/**
	 * performSearch takes two parameters and returns a JSON string
	 * with the result of the API call.
	 * @param searchArgs: An ArrayList of strings of search arguments
	 * @param numResults: The maximum number of results to return
	 */
	public String performQuickSearch(ArrayList<String> searchArgs,
								int numResults) {
		
		String url = SNOOTH_URL + WINE_RESOURCE_ID + "?akey=" + API_KEY;
		url += "&n=" + numResults;
		url += "&t=wine";
		url += "&a=1";
		url += "&q=";
		for (String arg : searchArgs) {
			url += arg + "+";
		}
		
		// take off last "+"
		url = url.substring(0, (url.length()-1));
        return callSnoothAPIWineSearch(url);
	}
	
	
	
	/**
	 * performAdvancedSearch takes two parameters and returns a JSON string
	 * containing the result of the API call.
	 * 
	 * @param searchParameters: The object containing the search parameters
	 * @param numResults: The maximum number of results to return
	 */
	public String performAdvancedSearch(WineSearchObject searchParameters,
										int numResults) {
		
		String url = SNOOTH_URL + WINE_RESOURCE_ID + "?akey=" + API_KEY;
		
		url += "&a=1";
	
		if (searchParameters.getType() != "") {
			url += "&q=" + searchParameters.getType();
			if (searchParameters.getAccent() != "")
				url += "+" + searchParameters.getAccent();
		}
		else if (searchParameters.getAccent() != "")
			url += "&q=" + searchParameters.getAccent();
		
		url += "&n=" + numResults;
		if (searchParameters.getColor() != "") 
			url += "&color=" + searchParameters.getColor();
		if (searchParameters.getPrice() != "") {
			url += searchParameters.parsePriceString();
			url += "&s=price+desc";
			
		}
		url += "&t=wine";

		
		return callSnoothAPIWineSearch(url);
	}
	
	public String performCombinedSearch(WineSearchObject searchParameters, 
								ArrayList<String> searchArgs, int numResults){
		String url = SNOOTH_URL + WINE_RESOURCE_ID + "?akey=" + API_KEY;
		
		url += "&a=1";
	
		if (searchParameters.getType() != "") {
			url += "&q=" + searchParameters.getType() + "+";
			if (searchParameters.getAccent() != "")
				url += searchParameters.getAccent() + "+";
		}
		else if (searchParameters.getAccent() != "")
			url += "&q=" + searchParameters.getAccent() + "+";
		else{
			url += "&q=";
		}
		for (String arg : searchArgs) {
			url += arg + "+";
		}
		
		// take off last "+"
		url = url.substring(0, (url.length()-1));
		
		url += "&n=" + numResults;
		if (searchParameters.getColor() != "") 
			url += "&color=" + searchParameters.getColor();
		if (searchParameters.getPrice() != "") {
			url += searchParameters.parsePriceString();
			url += "&s=price+asc";
		}
		url += "&t=wine";
		
		return callSnoothAPIWineSearch(url);
	}
	
	public void addWineToWishlist(String email, String wineID) {
		String url = SNOOTH_URL + RATE_WINE_RESOURCE_ID + "?akey=" + API_KEY;
		url += "&u=" + RANDOM_STRING + email;
		url += "&p=" + RANDOM_STRING;
		url += "&id=" + wineID;
		url += "&w=1";
		
		retrieveStream(url);
	}
	
	public void addWineToCellar(String email, String wineID) {
		String url = SNOOTH_URL + RATE_WINE_RESOURCE_ID + "?akey=" + API_KEY;
		url += "&u=" + RANDOM_STRING + email;
		url += "&p=" + RANDOM_STRING;
		url += "&id=" + wineID;
		url += "&c=1";
		
		retrieveStream(url);
	}
	
	public void createSnoothAccount(String email) {
		String url = SNOOTH_URL + CREATE_ACCOUNT_RESOURCE_ID + "?akey=" + API_KEY;
		String newEmail = RANDOM_STRING + email;
		url += "&e=" + newEmail;
		url += "&p=" + RANDOM_STRING;
		
		MessageDigest m;
		String hashtext = new String();
		try {
			m = MessageDigest.getInstance("MD5");
			m.reset();
			m.update(newEmail.getBytes());
			byte[] digest = m.digest();
			BigInteger bigInt = new BigInteger(1,digest);
			hashtext = bigInt.toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		
		hashtext = hashtext.substring(0,19);
		System.err.println("sn = " + hashtext);
		url += "&s=" + hashtext;
		System.err.println("Created snooth account using the following URL:");
		System.err.println(url);
		InputStream source = retrieveStream(url);
		Reader reader = new InputStreamReader(source);
	}
	
	
	public String returnMyWines(String email) {
		String url = SNOOTH_URL + MY_WINES_RESOURCE_ID + "?akey=" + API_KEY;
		url += "&u=" + RANDOM_STRING + email;
		url += "&p=" + RANDOM_STRING;
		return callSnoothAPIMyWines(url);
	}
	
	/**
	 * getRandomWine returns a JSON string containing a single random wine
	 */
	public String getRandomWine() {
		Random rng = new Random();
		final int MAX_INT = 1000;
		int randomInt = rng.nextInt(MAX_INT);
		
		String url = SNOOTH_URL + WINE_RESOURCE_ID + "?akey=" + API_KEY;
		url += "&n=1";
		url += "&f=" + randomInt;
		
		System.err.println("getRandomWine: " + url);
		return callSnoothAPIWineSearch(url);
	}
	
	/**
	 * getWineryDetails returns a JSON string containing the details of the
	 * winery specified by wineryID.
	 * The wineryID here is the same as the winery_id returned by a wine search
	 * API call which gets stored in an APISnoothResponse object.
	 */
	public String getWineryDetails(String wineryID) {
		String url = SNOOTH_URL + WINERY_RESOURCE_ID + "?akey=" + API_KEY;
		url += "&id=" + wineryID;
		
		System.err.println("url: "+ url);
		System.err.println("callSnoothAPI(url) = " + callSnoothAPIWinerySearch(url));
		return callSnoothAPIWinerySearch(url);
	}

	/**
	 * hasSearchResults returns true if the JSON response string has more
	 * than API_RETVAL_LENGTH_FOR_ERROR characters, meaning that the search 
	 * had at least one result.
	 * @param searchResultString: the resulting string of the API call
	 */
	public Boolean hasSearchResults(String searchResultString) {
		return (searchResultString.length() > API_RETVAL_LENGTH_FOR_ERROR);
	}
	
	private String callSnoothAPIMyWines(String url){
		// For converting to and from JSON/Java objects
		Gson gson = new Gson();
		// Make API call
		InputStream source = retrieveStream(url);  
		//System.err.println("callSnoothAPI: " + url);
        Reader reader = new InputStreamReader(source);
	    
        // Convert JSON object to Java object
        APISnoothResponseMyWines snoothResponse = gson.fromJson(reader, APISnoothResponseMyWines.class);
        
        // Return JSON array
        return gson.toJson(snoothResponse);
	}
	
	private String callSnoothAPIWineSearch(String url) {
		
		// For converting to and from JSON/Java objects
		Gson gson = new Gson();
		// Make API call
		InputStream source = retrieveStream(url);  
		//System.err.println("callSnoothAPI: " + url);
        Reader reader = new InputStreamReader(source);
	    
        // Convert JSON object to Java object
        APISnoothResponse snoothResponse = gson.fromJson(reader, APISnoothResponse.class);
        
        // Return JSON array
        return gson.toJson(snoothResponse);
	}
	
private String callSnoothAPIWinerySearch(String url) {
		
		// For converting to and from JSON/Java objects
		Gson gson = new Gson();
		// Make API call
		InputStream source = retrieveStream(url);  
		//System.err.println("callSnoothAPI: " + url);
        Reader reader = new InputStreamReader(source);
	    
        // Convert JSON object to Java object
        APISnoothResponseWinery snoothResponseWinery = gson.fromJson(reader, APISnoothResponseWinery.class);
        
        // Return JSON array
        return gson.toJson(snoothResponseWinery);
	}
	
	private InputStream retrieveStream(String url) {
    	
    	DefaultHttpClient client = new DefaultHttpClient(); 
        
        HttpGet getRequest = new HttpGet(url);
          
        try {
           
           HttpResponse getResponse = client.execute(getRequest);
           final int statusCode = getResponse.getStatusLine().getStatusCode();
           
           if (statusCode != HttpStatus.SC_OK) { 
              Log.w(getClass().getSimpleName(), "Error " + statusCode + " for URL " + url); 
              return null;
           }

           HttpEntity getResponseEntity = getResponse.getEntity();
           return getResponseEntity.getContent();
           
        } 
        catch (IOException e) {
           getRequest.abort();
           Log.w(getClass().getSimpleName(), "Error for URL " + url, e);
           System.err.println(e.toString());
        }
        
        return null;  
     }
}
