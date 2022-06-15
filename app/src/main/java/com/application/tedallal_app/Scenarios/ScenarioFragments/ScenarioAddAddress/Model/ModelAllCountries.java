package com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioAddAddress.Model;//  ModelAllCountries.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on October 21, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class ModelAllCountries{

	@SerializedName("ar")
	private String ar;
	@SerializedName("code_country")
	private String codeCountry;
	@SerializedName("en")
	private String en;
	@SerializedName("id")
	private int id;

	public void setAr(String ar){
		this.ar = ar;
	}
	public String getAr(){
		return this.ar;
	}
	public void setCodeCountry(String codeCountry){
		this.codeCountry = codeCountry;
	}
	public String getCodeCountry(){
		return this.codeCountry;
	}
	public void setEn(String en){
		this.en = en;
	}
	public String getEn(){
		return this.en;
	}
	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return this.id;
	}

	public ModelAllCountries() {
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public ModelAllCountries(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		ar = (String) jsonObject.opt("ar");
		en = (String) jsonObject.opt("en");
		codeCountry = (String) jsonObject.opt("code_country");
		id = jsonObject.optInt("id");
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("ar", ar);
			jsonObject.put("code_country", codeCountry);
			jsonObject.put("en", en);
			jsonObject.put("id", id);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}