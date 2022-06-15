package com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioAddCart.Model;

import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

public class Model_Product_Color {

    @SerializedName("id")
    private int id;
    @SerializedName("id_admin")
    private String id_admin;
    @SerializedName("id_product")
    private String id_product;
    @SerializedName("color_code")
    private String color_code;
    @SerializedName("color_en")
    private String color_en;
    @SerializedName("color_ar")
    private String color_ar;
    @SerializedName("datee")
    private String datee;

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

    public String getId_product() {
        return id_product;
    }

    public void setId_product(String id_product) {
        this.id_product = id_product;
    }

    public String getColor_code() {
        return color_code;
    }

    public void setColor_code(String color_code) {
        this.color_code = color_code;
    }

    public String getColor_en() {
        return color_en;
    }

    public void setColor_en(String color_en) {
        this.color_en = color_en;
    }

    public String getColor_ar() {
        return color_ar;
    }

    public void setColor_ar(String color_ar) {
        this.color_ar = color_ar;
    }

    public String getDatee() {
        return datee;
    }

    public void setDatee(String datee) {
        this.datee = datee;
    }

    public Model_Product_Color() {
    }

    public JSONObject toJsonObject()
    {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", id);
            jsonObject.put("id_admin", id_admin);
            jsonObject.put("id_product", id_product);
            jsonObject.put("color_code", color_code);
            jsonObject.put("color_en", color_en);
            jsonObject.put("color_ar", color_ar);
            jsonObject.put("datee", datee);

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonObject;
    }
}
