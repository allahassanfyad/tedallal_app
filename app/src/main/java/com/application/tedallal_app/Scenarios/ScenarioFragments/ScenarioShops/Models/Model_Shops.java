package com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioShops.Models;

import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

public class Model_Shops {
    @SerializedName("id")
    private int id;
    @SerializedName("id_suppliers")
    private String idSuppliers;
    @SerializedName("id_admin")
    private String idAdmin;
    @SerializedName("name_suppliers")
    private String nameSuppliers;
    @SerializedName("address")
    private String address;
    @SerializedName("name_company")
    private String nameCompany;
    @SerializedName("name_company_ar")
    private String name_company_ar;
    @SerializedName("phone")
    private String phone;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("img")
    private String img;
    @SerializedName("datee")
    private String datee;
    @SerializedName("percent")
    private String percent;
    @SerializedName("type_suppliers")
    private String typeSuppliers;
    @SerializedName("name_employ")
    private String nameEmploy;
    @SerializedName("datee_start")
    private String dateeStart;
    @SerializedName("datee_end")
    private String dateeEnd;
    @SerializedName("status_suppliers")
    private String statusSuppliers;
    @SerializedName("type_shop")
    private String typeShop;
    @SerializedName("closing_time")
    private String closingTime;
    @SerializedName("opening_time")
    private String openingTime;
    @SerializedName("cover_suppliers")
    private String cover_suppliers;


    public String getCover_suppliers() {
        return cover_suppliers;
    }

    public void setCover_suppliers(String cover_suppliers) {
        this.cover_suppliers = cover_suppliers;
    }

    public String getName_company_ar() {
        return name_company_ar;
    }

    public void setName_company_ar(String name_company_ar) {
        this.name_company_ar = name_company_ar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdSuppliers() {
        return idSuppliers;
    }

    public void setIdSuppliers(String idSuppliers) {
        this.idSuppliers = idSuppliers;
    }

    public String getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(String idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getNameSuppliers() {
        return nameSuppliers;
    }

    public void setNameSuppliers(String nameSuppliers) {
        this.nameSuppliers = nameSuppliers;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDatee() {
        return datee;
    }

    public void setDatee(String datee) {
        this.datee = datee;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public String getTypeSuppliers() {
        return typeSuppliers;
    }

    public void setTypeSuppliers(String typeSuppliers) {
        this.typeSuppliers = typeSuppliers;
    }

    public String getNameEmploy() {
        return nameEmploy;
    }

    public void setNameEmploy(String nameEmploy) {
        this.nameEmploy = nameEmploy;
    }

    public String getDateeStart() {
        return dateeStart;
    }

    public void setDateeStart(String dateeStart) {
        this.dateeStart = dateeStart;
    }

    public String getDateeEnd() {
        return dateeEnd;
    }

    public void setDateeEnd(String dateeEnd) {
        this.dateeEnd = dateeEnd;
    }

    public String getStatusSuppliers() {
        return statusSuppliers;
    }

    public void setStatusSuppliers(String statusSuppliers) {
        this.statusSuppliers = statusSuppliers;
    }

    public String getTypeShop() {
        return typeShop;
    }

    public void setTypeShop(String typeShop) {
        this.typeShop = typeShop;
    }

    public String getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }

    public Model_Shops() {
    }


}
