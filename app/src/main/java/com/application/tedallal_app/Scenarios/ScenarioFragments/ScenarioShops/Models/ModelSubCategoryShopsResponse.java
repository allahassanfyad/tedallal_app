package com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioShops.Models;//  ModelHomeDetailsResponse.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on September 19, 2020

import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;


public class ModelSubCategoryShopsResponse {

	@SerializedName("id")
	private int id;
	@SerializedName("id_admin")
	private String id_admin;
	@SerializedName("title_ar")
	private String title_ar;
	@SerializedName("title_en")
	private String title_en;
	@SerializedName("id_suppliers")
	private int id_suppliers;
	@SerializedName("id_category")
	private String id_category;
	@SerializedName("datee")
	private String datee;
	@SerializedName("img_category")
	private String img_category;
	@SerializedName("name_category")
	private String name_category;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getId_admin() {
		return id_admin;
	}

	public void setId_admin(String id_admin) {
		this.id_admin = id_admin;
	}

	public String getTitle_ar() {
		return title_ar;
	}

	public void setTitle_ar(String title_ar) {
		this.title_ar = title_ar;
	}

	public String getTitle_en() {
		return title_en;
	}

	public void setTitle_en(String title_en) {
		this.title_en = title_en;
	}

	public int getId_suppliers() {
		return id_suppliers;
	}

	public void setId_suppliers(int id_suppliers) {
		this.id_suppliers = id_suppliers;
	}

	public String getId_category() {
		return id_category;
	}

	public void setId_category(String id_category) {
		this.id_category = id_category;
	}

	public String getDatee() {
		return datee;
	}

	public void setDatee(String datee) {
		this.datee = datee;
	}

	public String getImg_category() {
		return img_category;
	}

	public void setImg_category(String img_category) {
		this.img_category = img_category;
	}

	public String getName_category() {
		return name_category;
	}

	public void setName_category(String name_category) {
		this.name_category = name_category;
	}

	public ModelSubCategoryShopsResponse() {
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("id", id);
			jsonObject.put("id_admin", id_admin);
			jsonObject.put("title_ar", title_ar);
			jsonObject.put("title_en", title_en);
			jsonObject.put("id_suppliers", id_suppliers);
			jsonObject.put("id_category", id_category);
			jsonObject.put("datee", datee);
			jsonObject.put("img_category", img_category);
			jsonObject.put("name_category", name_category);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}
}