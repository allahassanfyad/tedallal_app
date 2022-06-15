package com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Model;//  ModelAllCategoryResponse.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on September 26, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class ModelAllCategoryResponse{

	@SerializedName("dateregister")
	private String dateregister;
	@SerializedName("id")
	private int id;
	@SerializedName("id_admin")
	private String idAdmin;
	@SerializedName("img")
	private String img;
	@SerializedName("name")
	private String name;
	@SerializedName("name_en")
	private String nameEn;

	public void setDateregister(String dateregister){
		this.dateregister = dateregister;
	}
	public String getDateregister(){
		return this.dateregister;
	}
	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return this.id;
	}
	public void setIdAdmin(String idAdmin){
		this.idAdmin = idAdmin;
	}
	public String getIdAdmin(){
		return this.idAdmin;
	}
	public void setImg(String img){
		this.img = img;
	}
	public String getImg(){
		return this.img;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setNameEn(String nameEn){
		this.nameEn = nameEn;
	}
	public String getNameEn(){
		return this.nameEn;
	}

	public ModelAllCategoryResponse() {
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public ModelAllCategoryResponse(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		dateregister = (String) jsonObject.opt("dateregister");
		idAdmin = (String) jsonObject.opt("id_admin");
		img = (String) jsonObject.opt("img");
		name = (String) jsonObject.opt("name");
		nameEn = (String) jsonObject.opt("name_en");
		id = jsonObject.optInt("id");
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("dateregister", dateregister);
			jsonObject.put("id", id);
			jsonObject.put("id_admin", idAdmin);
			jsonObject.put("img", img);
			jsonObject.put("name", name);
			jsonObject.put("name_en", nameEn);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}