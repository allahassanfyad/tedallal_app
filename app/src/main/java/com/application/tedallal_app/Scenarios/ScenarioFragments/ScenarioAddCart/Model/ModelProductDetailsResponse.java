package com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioAddCart.Model;//  ModelProductDetailsResponse.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on September 26, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class ModelProductDetailsResponse{

	@SerializedName("category")
	private String category;
	@SerializedName("color")
	private String color;
	@SerializedName("color_en")
	private String colorEn;
	@SerializedName("datee")
	private String datee;
	@SerializedName("des")
	private String des;
	@SerializedName("des_en")
	private String desEn;
	@SerializedName("id")
	private int id;
	@SerializedName("id_admin")
	private int idAdmin;
	@SerializedName("img_1")
	private String img1;
	@SerializedName("img_2")
	private String img2;
	@SerializedName("img_3")
	private String img3;
	@SerializedName("meteral")
	private String meteral;
	@SerializedName("meteral_en")
	private String meteralEn;
	@SerializedName("model")
	private String model;
	@SerializedName("model_en")
	private String modelEn;
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
	@SerializedName("styles")
	private String styles;
	@SerializedName("styles_en")
	private String stylesEn;
	@SerializedName("title")
	private String title;
	@SerializedName("title_en")
	private String titleEn;
	@SerializedName("type")
	private String type;
	@SerializedName("type_arm_en")
	private String typeArmEn;

	public void setCategory(String category){
		this.category = category;
	}
	public String getCategory(){
		return this.category;
	}
	public void setColor(String color){
		this.color = color;
	}
	public String getColor(){
		return this.color;
	}
	public void setColorEn(String colorEn){
		this.colorEn = colorEn;
	}
	public String getColorEn(){
		return this.colorEn;
	}
	public void setDatee(String datee){
		this.datee = datee;
	}
	public String getDatee(){
		return this.datee;
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
	public void setMeteral(String meteral){
		this.meteral = meteral;
	}
	public String getMeteral(){
		return this.meteral;
	}
	public void setMeteralEn(String meteralEn){
		this.meteralEn = meteralEn;
	}
	public String getMeteralEn(){
		return this.meteralEn;
	}
	public void setModel(String model){
		this.model = model;
	}
	public String getModel(){
		return this.model;
	}
	public void setModelEn(String modelEn){
		this.modelEn = modelEn;
	}
	public String getModelEn(){
		return this.modelEn;
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
	public void setStyles(String styles){
		this.styles = styles;
	}
	public String getStyles(){
		return this.styles;
	}
	public void setStylesEn(String stylesEn){
		this.stylesEn = stylesEn;
	}
	public String getStylesEn(){
		return this.stylesEn;
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
	public void setType(String type){
		this.type = type;
	}
	public String getType(){
		return this.type;
	}
	public void setTypeArmEn(String typeArmEn){
		this.typeArmEn = typeArmEn;
	}
	public String getTypeArmEn(){
		return this.typeArmEn;
	}

	public ModelProductDetailsResponse() {
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public ModelProductDetailsResponse(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		category = (String) jsonObject.opt("category");
		color = (String) jsonObject.opt("color");
		colorEn = (String) jsonObject.opt("color_en");
		datee = (String) jsonObject.opt("datee");
		des = (String) jsonObject.opt("des");
		desEn = (String) jsonObject.opt("des_en");
		img1 = (String) jsonObject.opt("img_1");
		img2 = (String) jsonObject.opt("img_2");
		img3 = (String) jsonObject.opt("img_3");
		meteral = (String) jsonObject.opt("meteral");
		meteralEn = (String) jsonObject.opt("meteral_en");
		model = (String) jsonObject.opt("model");
		modelEn = (String) jsonObject.opt("model_en");
		numberRate = (String) jsonObject.opt("number_rate");
		numberStar = (String) jsonObject.opt("number_star");
		price = (String) jsonObject.opt("price");
		priceDiscount = (String) jsonObject.opt("price_discount");
		rate = (String) jsonObject.opt("rate");
		slider = (String) jsonObject.opt("slider");
		sort = (String) jsonObject.opt("sort");
		styles = (String) jsonObject.opt("styles");
		stylesEn = (String) jsonObject.opt("styles_en");
		title = (String) jsonObject.opt("title");
		titleEn = (String) jsonObject.opt("title_en");
		type = (String) jsonObject.opt("type");
		typeArmEn = (String) jsonObject.opt("type_arm_en");
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
			jsonObject.put("category", category);
			jsonObject.put("color", color);
			jsonObject.put("color_en", colorEn);
			jsonObject.put("datee", datee);
			jsonObject.put("des", des);
			jsonObject.put("des_en", desEn);
			jsonObject.put("id", id);
			jsonObject.put("id_admin", idAdmin);
			jsonObject.put("img_1", img1);
			jsonObject.put("img_2", img2);
			jsonObject.put("img_3", img3);
			jsonObject.put("meteral", meteral);
			jsonObject.put("meteral_en", meteralEn);
			jsonObject.put("model", model);
			jsonObject.put("model_en", modelEn);
			jsonObject.put("number_rate", numberRate);
			jsonObject.put("number_star", numberStar);
			jsonObject.put("price", price);
			jsonObject.put("price_discount", priceDiscount);
			jsonObject.put("rate", rate);
			jsonObject.put("slider", slider);
			jsonObject.put("sort", sort);
			jsonObject.put("styles", styles);
			jsonObject.put("styles_en", stylesEn);
			jsonObject.put("title", title);
			jsonObject.put("title_en", titleEn);
			jsonObject.put("type", type);
			jsonObject.put("type_arm_en", typeArmEn);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}