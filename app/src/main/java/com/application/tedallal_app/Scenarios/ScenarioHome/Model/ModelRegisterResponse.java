package com.application.tedallal_app.Scenarios.ScenarioHome.Model;//  ModelRegisterResponse.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on September 20, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class ModelRegisterResponse{

	@SerializedName("area")
	private String area;
	@SerializedName("city")
	private String city;
	@SerializedName("country")
	private String country;
	@SerializedName("datee")
	private String datee;
	@SerializedName("email")
	private String email;
	@SerializedName("id")
	private int id;
	@SerializedName("id_admin")
	private int idAdmin;
	@SerializedName("img")
	private String img;
	@SerializedName("name")
	private String name;
	@SerializedName("password")
	private String password;
	@SerializedName("phone")
	private String phone;
	@SerializedName("type")
	private String type;

	public void setArea(String area){
		this.area = area;
	}
	public String getArea(){
		return this.area;
	}
	public void setCity(String city){
		this.city = city;
	}
	public String getCity(){
		return this.city;
	}
	public void setCountry(String country){
		this.country = country;
	}
	public String getCountry(){
		return this.country;
	}
	public void setDatee(String datee){
		this.datee = datee;
	}
	public String getDatee(){
		return this.datee;
	}
	public void setEmail(String email){
		this.email = email;
	}
	public String getEmail(){
		return this.email;
	}
	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return this.id;
	}
	public void setIdAdmin(int idAdmin){
		this.idAdmin = idAdmin;
	}
	public int getIdAdmin(){
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
	public void setPassword(String password){
		this.password = password;
	}
	public String getPassword(){
		return this.password;
	}
	public void setPhone(String phone){
		this.phone = phone;
	}
	public String getPhone(){
		return this.phone;
	}
	public void setType(String type){
		this.type = type;
	}
	public String getType(){
		return this.type;
	}

	public ModelRegisterResponse() {
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public ModelRegisterResponse(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		area = (String) jsonObject.opt("area");
		city = (String) jsonObject.opt("city");
		country = (String) jsonObject.opt("country");
		datee = (String) jsonObject.opt("datee");
		email = (String) jsonObject.opt("email");
		img = (String) jsonObject.opt("img");
		name = (String) jsonObject.opt("name");
		password = (String) jsonObject.opt("password");
		phone = (String) jsonObject.opt("phone");
		type = (String) jsonObject.opt("type");
		id = jsonObject.optInt("id");
		idAdmin = jsonObject.optInt("id_admin");
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("area", area);
			jsonObject.put("city", city);
			jsonObject.put("country", country);
			jsonObject.put("datee", datee);
			jsonObject.put("email", email);
			jsonObject.put("id", id);
			jsonObject.put("id_admin", idAdmin);
			jsonObject.put("img", img);
			jsonObject.put("name", name);
			jsonObject.put("password", password);
			jsonObject.put("phone", phone);
			jsonObject.put("type", type);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}