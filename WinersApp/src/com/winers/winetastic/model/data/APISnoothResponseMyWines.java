package com.winers.winetastic.model.data;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class APISnoothResponseMyWines implements Serializable {
	@SerializedName("wines")
	public List<APISnoothResponseMyWineArray> myWineResults;
	
	@SerializedName("meta")
	public APISnoothResponseMetaData metaResults;
	
}




