package com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioAllAddress.Model;//  ModelAllAddressResponse.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on November 10, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class ModelAllAddressResponse{

	@SerializedName("address")
	private String address;
	@SerializedName("area")
	private String area;
	@SerializedName("city")
	private String city;
	@SerializedName("country")
	private String country;
	@SerializedName("datee")
	private String datee;
	@SerializedName("id")
	private int id;
	@SerializedName("id_user")
	private String idUser;
	@SerializedName("name")
	private String name;
	@SerializedName("phone")
	private String phone;
	@SerializedName("posta")
	private String posta;
	@SerializedName("street")
	private String street;

	public void setAddress(String address){
		this.address = address;
	}
	public String getAddress(){
		return this.address;
	}
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
	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return this.id;
	}
	public void setIdUser(String idUser){
		this.idUser = idUser;
	}
	public String getIdUser(){
		return this.idUser;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setPhone(String phone){
		this.phone = phone;
	}
	public String getPhone(){
		return this.phone;
	}
	public void setPosta(String posta){
		this.posta = posta;
	}
	public String getPosta(){
		return this.posta;
	}
	public void setStreet(String street){
		this.street = street;
	}
	public String getStreet(){
		return this.street;
	}

	public ModelAllAddressResponse() {
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public ModelAllAddressResponse(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		address = (String) jsonObject.opt("address");
		datee = (String) jsonObject.opt("datee");
		idUser = (String) jsonObject.opt("id_user");
		name = (String) jsonObject.opt("name");
		area = (String) jsonObject.opt("area");
		city = (String) jsonObject.opt("city");
		country = (String) jsonObject.opt("country");
		id = jsonObject.optInt("id");
		phone = (String) jsonObject.opt("phone");
		posta = (String) jsonObject.opt("posta");
		street = (String) jsonObject.opt("street");
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("address", address);
			jsonObject.put("area", area);
			jsonObject.put("city", city);
			jsonObject.put("country", country);
			jsonObject.put("datee", datee);
			jsonObject.put("id", id);
			jsonObject.put("id_user", idUser);
			jsonObject.put("name", name);
			jsonObject.put("phone", phone);
			jsonObject.put("posta", posta);
			jsonObject.put("street", street);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}