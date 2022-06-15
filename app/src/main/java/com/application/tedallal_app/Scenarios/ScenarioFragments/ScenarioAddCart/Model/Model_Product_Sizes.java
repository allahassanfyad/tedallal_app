package com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioAddCart.Model;

import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

public class Model_Product_Sizes {

    @SerializedName("id")
    private int id;
    @SerializedName("id_product")
    private String idProduct;
    @SerializedName("sizes_en")
    private String sizesEn;
    @SerializedName("sizes_ar")
    private String sizesAr;
    @SerializedName("datee")
    private String datee;
    @SerializedName("id_admin")
    private String idAdmin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getSizesEn() {
        return sizesEn;
    }

    public void setSizesEn(String sizesEn) {
        this.sizesEn = sizesEn;
    }

    public String getSizesAr() {
        return sizesAr;
    }

    public void setSizesAr(String sizesAr) {
        this.sizesAr = sizesAr;
    }

    public String getDatee() {
        return datee;
    }

    public void setDatee(String datee) {
        this.datee = datee;
    }

    public String getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(String idAdmin) {
        this.idAdmin = idAdmin;
    }




    public Model_Product_Sizes() {
    }

    public JSONObject toJsonObject()
    {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", id);
            jsonObject.put("idProduct", idProduct);
            jsonObject.put("sizesEn", sizesEn);
            jsonObject.put("sizesAr", sizesAr);
            jsonObject.put("datee", datee);
            jsonObject.put("idAdmin", idAdmin);

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonObject;
    }
}
