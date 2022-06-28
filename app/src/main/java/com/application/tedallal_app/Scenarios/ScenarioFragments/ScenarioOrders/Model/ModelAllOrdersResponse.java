package com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioOrders.Model;//  ModelAllOrdersResponse.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on October 2, 2020

import org.json.*;

import java.util.*;

import com.google.gson.annotations.SerializedName;


public class ModelAllOrdersResponse {

    @SerializedName("address")
    private String address;
    @SerializedName("area")
    private String area;
    @SerializedName("branch")
    private String branch;
    @SerializedName("city")
    private String city;
    @SerializedName("copon_code")
    private String coponCode;
    @SerializedName("datee")
    private String datee;
    @SerializedName("deliver_code")
    private String deliverCode;
    @SerializedName("deliver_details")
    private String deliverDetails;
    @SerializedName("id")
    private int id;
    @SerializedName("id_admin")
    private String idAdmin;
    @SerializedName("id_agent")
    private String idAgent;
    @SerializedName("id_product")
    private int idProduct;
    @SerializedName("id_user")
    private int idUser;
    @SerializedName("sitelan")
    private String sitelan;
    @SerializedName("sitelon")
    private String sitelon;
    @SerializedName("status")
    private String status;
    @SerializedName("timee")
    private String timee;
    @SerializedName("totle_price")
    private String totlePrice;
    @SerializedName("dateeeeee")
    private String dateeeeee;


    public String getDateeeeee() {
        return dateeeeee;
    }

    public void setDateeeeee(String dateeeeee) {
        this.dateeeeee = dateeeeee;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getArea() {
        return this.area;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getBranch() {
        return this.branch;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return this.city;
    }

    public void setCoponCode(String coponCode) {
        this.coponCode = coponCode;
    }

    public String getCoponCode() {
        return this.coponCode;
    }

    public void setDatee(String datee) {
        this.datee = datee;
    }

    public String getDatee() {
        return this.datee;
    }

    public void setDeliverCode(String deliverCode) {
        this.deliverCode = deliverCode;
    }

    public String getDeliverCode() {
        return this.deliverCode;
    }

    public void setDeliverDetails(String deliverDetails) {
        this.deliverDetails = deliverDetails;
    }

    public String getDeliverDetails() {
        return this.deliverDetails;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setIdAdmin(String idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getIdAdmin() {
        return this.idAdmin;
    }

    public void setIdAgent(String idAgent) {
        this.idAgent = idAgent;
    }

    public String getIdAgent() {
        return this.idAgent;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getIdProduct() {
        return this.idProduct;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdUser() {
        return this.idUser;
    }

    public void setSitelan(String sitelan) {
        this.sitelan = sitelan;
    }

    public String getSitelan() {
        return this.sitelan;
    }

    public void setSitelon(String sitelon) {
        this.sitelon = sitelon;
    }

    public String getSitelon() {
        return this.sitelon;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public void setTimee(String timee) {
        this.timee = timee;
    }

    public String getTimee() {
        return this.timee;
    }

    public void setTotlePrice(String totlePrice) {
        this.totlePrice = totlePrice;
    }

    public String getTotlePrice() {
        return this.totlePrice;
    }

    public ModelAllOrdersResponse() {
    }

    /**
     * Instantiate the instance using the passed jsonObject to set the properties values
     */
    public ModelAllOrdersResponse(JSONObject jsonObject) {
        if (jsonObject == null) {
            return;
        }
        address = (String) jsonObject.opt("address");
        area = (String) jsonObject.opt("area");
        branch = (String) jsonObject.opt("branch");
        city = (String) jsonObject.opt("city");
        coponCode = (String) jsonObject.opt("copon_code");
        datee = (String) jsonObject.opt("datee");
        deliverCode = (String) jsonObject.opt("deliver_code");
        deliverDetails = (String) jsonObject.opt("deliver_details");
        idAdmin = (String) jsonObject.opt("id_admin");
        sitelan = (String) jsonObject.opt("sitelan");
        sitelon = (String) jsonObject.opt("sitelon");
        status = (String) jsonObject.opt("status");
        timee = (String) jsonObject.opt("timee");
        totlePrice = (String) jsonObject.opt("totle_price");
        id = jsonObject.optInt("id");
        idAgent = (String) jsonObject.opt("id_agent");
        idProduct = jsonObject.optInt("id_product");
        idUser = jsonObject.optInt("id_user");
        dateeeeee = (String) jsonObject.opt("dateeeeee");
    }

    /**
     * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
     */
    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("address", address);
            jsonObject.put("area", area);
            jsonObject.put("branch", branch);
            jsonObject.put("city", city);
            jsonObject.put("copon_code", coponCode);
            jsonObject.put("datee", datee);
            jsonObject.put("deliver_code", deliverCode);
            jsonObject.put("deliver_details", deliverDetails);
            jsonObject.put("id", id);
            jsonObject.put("id_admin", idAdmin);
            jsonObject.put("id_agent", idAgent);
            jsonObject.put("id_product", idProduct);
            jsonObject.put("id_user", idUser);
            jsonObject.put("sitelan", sitelan);
            jsonObject.put("sitelon", sitelon);
            jsonObject.put("status", status);
            jsonObject.put("timee", timee);
            jsonObject.put("totle_price", totlePrice);
            jsonObject.put("dateeeeee", dateeeeee);

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonObject;
    }

}