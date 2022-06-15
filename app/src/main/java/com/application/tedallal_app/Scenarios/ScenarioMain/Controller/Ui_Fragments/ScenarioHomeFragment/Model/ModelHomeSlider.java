package com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Model;//  ModelHomeSlider.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on October 2, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class ModelHomeSlider{

	@SerializedName("id")
	private long id;
	@SerializedName("img")
	private String img;
	@SerializedName("url")
	private String url;
	@SerializedName("id_admin")
	private String idAdmin;
	@SerializedName("type")
	private String type;
	@SerializedName("datee")
	private String datee;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIdAdmin() {
		return idAdmin;
	}

	public void setIdAdmin(String idAdmin) {
		this.idAdmin = idAdmin;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDatee() {
		return datee;
	}

	public void setDatee(String datee) {
		this.datee = datee;
	}

	public ModelHomeSlider() {
	}


	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("id", id);
			jsonObject.put("img", img);
			jsonObject.put("url", url);
			jsonObject.put("id_admin", idAdmin);
			jsonObject.put("type", type);
			jsonObject.put("datee", datee);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}