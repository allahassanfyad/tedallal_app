package com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioAddCart.Model;//  ModelHomeDetailsResponse.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on September 19, 2020

import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;


public class ModelExtraRequestResponse {

	@SerializedName("id")
	private int id;
	@SerializedName("id_admin")
	private String id_admin;
	@SerializedName("title_ar")
	private String title_ar;
	@SerializedName("title_en")
	private String title_en;
	@SerializedName("id_product")
	private int id_product;
	@SerializedName("datee")
	private String datee;
	@SerializedName("product_price")
	private String product_price;

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

	public int getId_product() {
		return id_product;
	}

	public void setId_product(int id_product) {
		this.id_product = id_product;
	}

	public String getDatee() {
		return datee;
	}

	public void setDatee(String datee) {
		this.datee = datee;
	}

	public String getProduct_price() {
		return product_price;
	}

	public void setProduct_price(String product_price) {
		this.product_price = product_price;
	}

	public ModelExtraRequestResponse() {
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
			jsonObject.put("id_product", id_product);
			jsonObject.put("datee", datee);
			jsonObject.put("product_price", product_price);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}
}