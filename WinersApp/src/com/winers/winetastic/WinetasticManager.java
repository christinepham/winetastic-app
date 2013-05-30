package com.winers.winetastic;

import java.util.ArrayList;

public class WinetasticManager {
	
	WinetasticDAO dao = new WinetasticDAO();
	
	// Call DAO layer's performSearch
	public String performQuickSearch(ArrayList<String> searchArgs,
			int numResults) {
		return dao.performQuickSearch(searchArgs, numResults);
	}
	
	// Call DAO layer's performAdvancedSearch
	public String performAdvancedSearch(WineSearchObject searchParameters,
			int numResults) {
		return dao.performAdvancedSearch(searchParameters, numResults);
	}
	
	// Call DAO layer's getRandomWine
	public String getRandomWine() {
		return dao.getRandomWine();
	}
	
	// Call DAO layer's getWineryDetails
	public String getWineryDetails(String wineryID) {
		return dao.getWineryDetails(wineryID);
	}
	
	// Call DAO layer's hasSearchResults
	public Boolean hasSearchResults(String searchResultString) {
		return dao.hasSearchResults(searchResultString);
	}
	
	
}
