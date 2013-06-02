package com.winers.winetastic;

import java.util.ArrayList;

public class WinetasticManager {
	
	private static WinetasticDAO dao = new WinetasticDAO();
	
	WinetasticManager() { }
	
	// Call DAO layer's performSearch
	public static String performQuickSearch(ArrayList<String> searchArgs,
			int numResults) {
		return WinetasticManager.dao.performQuickSearch(searchArgs, numResults);
	}
	
	// Call DAO layer's performAdvancedSearch
	public static String performAdvancedSearch(WineSearchObject searchParameters,
			int numResults) {
		return WinetasticManager.dao.performAdvancedSearch(searchParameters, numResults);
	}
	
	// Call DAO layer's getRandomWine
	public static String getRandomWine() {
		return WinetasticManager.dao.getRandomWine();
	}
	
	// Call DAO layer's getWineryDetails
	public static String getWineryDetails(String wineryID) {
		return WinetasticManager.dao.getWineryDetails(wineryID);
	}
	
	// Call DAO layer's hasSearchResults
	public static Boolean hasSearchResults(String searchResultString) {
		return WinetasticManager.dao.hasSearchResults(searchResultString);
	}
	
	
}
