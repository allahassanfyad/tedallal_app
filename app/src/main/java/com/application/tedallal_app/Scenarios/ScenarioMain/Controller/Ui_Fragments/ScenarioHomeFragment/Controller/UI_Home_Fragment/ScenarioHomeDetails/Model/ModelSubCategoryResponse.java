package com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Controller.UI_Home_Fragment.ScenarioHomeDetails.Model;//  ModelHomeDetailsResponse.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on September 19, 2020

import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;


public class ModelSubCategoryResponse {

	@SerializedName("id")
	private int id;
	@SerializedName("namesub")
	private String namesub;
	@SerializedName("namesub_en")
	private String namesub_en;
	@SerializedName("urlsub")
	private String urlsub;
	@SerializedName("id_admin")
	private int id_admin;
	@SerializedName("imgsub")
	private String imgsub;
	@SerializedName("id_category")
	private String id_category;
	@SerializedName("dateregister")
	private String dateregister;

	private  Boolean  isFav = false;

	public Boolean getIsFav(){
		return isFav;
	}
	public void setIsFav(Boolean favs) {
		isFav = favs;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNamesub() {
		return namesub;
	}

	public void setNamesub(String namesub) {
		this.namesub = namesub;
	}

	public String getNamesub_en() {
		return namesub_en;
	}

	public void setNamesub_en(String namesub_en) {
		this.namesub_en = namesub_en;
	}

	public String getUrlsub() {
		return urlsub;
	}

	public void setUrlsub(String urlsub) {
		this.urlsub = urlsub;
	}

	public int getId_admin() {
		return id_admin;
	}

	public void setId_admin(int id_admin) {
		this.id_admin = id_admin;
	}

	public String getImgsub() {
		return imgsub;
	}

	public void setImgsub(String imgsub) {
		this.imgsub = imgsub;
	}

	public String getId_category() {
		return id_category;
	}

	public void setId_category(String id_category) {
		this.id_category = id_category;
	}

	public String getDateregister() {
		return dateregister;
	}

	public void setDateregister(String dateregister) {
		this.dateregister = dateregister;
	}

	public Boolean getFav() {
		return isFav;
	}

	public void setFav(Boolean fav) {
		isFav = fav;
	}

	public ModelSubCategoryResponse() {
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("id", id);
			jsonObject.put("namesub", namesub);
			jsonObject.put("namesub_en", namesub_en);
			jsonObject.put("urlsub", urlsub);
			jsonObject.put("id_admin", id_admin);
			jsonObject.put("imgsub", imgsub);
			jsonObject.put("id_category", id_category);
			jsonObject.put("dateregister", dateregister);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}
}