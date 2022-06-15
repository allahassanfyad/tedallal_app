package com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioFavouriteFragment.Model;//  ModelAllFavouriteResponse.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on September 20, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class ModelAllFavouriteResponse{

	@SerializedName("category")
	private String category;
	@SerializedName("datee")
	private String datee;
	@SerializedName("datee1")
	private String datee1;
	@SerializedName("des")
	private String des;
	@SerializedName("des_en")
	private String desEn;
	@SerializedName("id")
	private int id;
	@SerializedName("id_admin")
	private int idAdmin;
	@SerializedName("id_product")
	private int idProduct;
	@SerializedName("id_user")
	private int idUser;
	@SerializedName("id1")
	private int id1;
	@SerializedName("img_1")
	private String img1;
	@SerializedName("img_2")
	private String img2;
	@SerializedName("img_3")
	private String img3;
	@SerializedName("number_rate")
	private String numberRate;
	@SerializedName("number_star")
	private String numberStar;
	@SerializedName("price")
	private String price;
	@SerializedName("price_discount")
	private String priceDiscount;
	@SerializedName("rate")
	private String rate;
	@SerializedName("slider")
	private String slider;
	@SerializedName("sort")
	private String sort;
	@SerializedName("title")
	private String title;
	@SerializedName("title_en")
	private String titleEn;

	public void setCategory(String category){
		this.category = category;
	}
	public String getCategory(){
		return this.category;
	}
	public void setDatee(String datee){
		this.datee = datee;
	}
	public String getDatee(){
		return this.datee;
	}
	public void setDatee1(String datee1){
		this.datee1 = datee1;
	}
	public String getDatee1(){
		return this.datee1;
	}
	public void setDes(String des){
		this.des = des;
	}
	public String getDes(){
		return this.des;
	}
	public void setDesEn(String desEn){
		this.desEn = desEn;
	}
	public String getDesEn(){
		return this.desEn;
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
	public void setIdProduct(int idProduct){
		this.idProduct = idProduct;
	}
	public int getIdProduct(){
		return this.idProduct;
	}
	public void setIdUser(int idUser){
		this.idUser = idUser;
	}
	public int getIdUser(){
		return this.idUser;
	}
	public void setId1(int id1){
		this.id1 = id1;
	}
	public int getId1(){
		return this.id1;
	}
	public void setImg1(String img1){
		this.img1 = img1;
	}
	public String getImg1(){
		return this.img1;
	}
	public void setImg2(String img2){
		this.img2 = img2;
	}
	public String getImg2(){
		return this.img2;
	}
	public void setImg3(String img3){
		this.img3 = img3;
	}
	public String getImg3(){
		return this.img3;
	}
	public void setNumberRate(String numberRate){
		this.numberRate = numberRate;
	}
	public String getNumberRate(){
		return this.numberRate;
	}
	public void setNumberStar(String numberStar){
		this.numberStar = numberStar;
	}
	public String getNumberStar(){
		return this.numberStar;
	}
	public void setPrice(String price){
		this.price = price;
	}
	public String getPrice(){
		return this.price;
	}
	public void setPriceDiscount(String priceDiscount){
		this.priceDiscount = priceDiscount;
	}
	public String getPriceDiscount(){
		return this.priceDiscount;
	}
	public void setRate(String rate){
		this.rate = rate;
	}
	public String getRate(){
		return this.rate;
	}
	public void setSlider(String slider){
		this.slider = slider;
	}
	public String getSlider(){
		return this.slider;
	}
	public void setSort(String sort){
		this.sort = sort;
	}
	public String getSort(){
		return this.sort;
	}
	public void setTitle(String title){
		this.title = title;
	}
	public String getTitle(){
		return this.title;
	}
	public void setTitleEn(String titleEn){
		this.titleEn = titleEn;
	}
	public String getTitleEn(){
		return this.titleEn;
	}

	public ModelAllFavouriteResponse() {
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public ModelAllFavouriteResponse(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		category = (String) jsonObject.opt("category");
		datee = (String) jsonObject.opt("datee");
		datee1 = (String) jsonObject.opt("datee1");
		des = (String) jsonObject.opt("des");
		desEn = (String) jsonObject.opt("des_en");
		img1 = (String) jsonObject.opt("img_1");
		img2 = (String) jsonObject.opt("img_2");
		img3 = (String) jsonObject.opt("img_3");
		numberRate = (String) jsonObject.opt("number_rate");
		numberStar = (String) jsonObject.opt("number_star");
		price = (String) jsonObject.opt("price");
		priceDiscount = (String) jsonObject.opt("price_discount");
		rate = (String) jsonObject.opt("rate");
		slider = (String) jsonObject.opt("slider");
		sort = (String) jsonObject.opt("sort");
		title = (String) jsonObject.opt("title");
		titleEn = (String) jsonObject.opt("title_en");
		id = jsonObject.optInt("id");
		idAdmin = jsonObject.optInt("id_admin");
		idProduct = jsonObject.optInt("id_product");
		idUser = jsonObject.optInt("id_user");
		id1 = jsonObject.optInt("id1");
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("category", category);
			jsonObject.put("datee", datee);
			jsonObject.put("datee1", datee1);
			jsonObject.put("des", des);
			jsonObject.put("des_en", desEn);
			jsonObject.put("id", id);
			jsonObject.put("id_admin", idAdmin);
			jsonObject.put("id_product", idProduct);
			jsonObject.put("id_user", idUser);
			jsonObject.put("id1", id1);
			jsonObject.put("img_1", img1);
			jsonObject.put("img_2", img2);
			jsonObject.put("img_3", img3);
			jsonObject.put("number_rate", numberRate);
			jsonObject.put("number_star", numberStar);
			jsonObject.put("price", price);
			jsonObject.put("price_discount", priceDiscount);
			jsonObject.put("rate", rate);
			jsonObject.put("slider", slider);
			jsonObject.put("sort", sort);
			jsonObject.put("title", title);
			jsonObject.put("title_en", titleEn);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}