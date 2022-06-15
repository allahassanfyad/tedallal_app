package com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioCart.Model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Rcy_Cart_Model extends RealmObject {

    private String productid;
    private String txttitle;
    private String txtqutity;
    private String txtprice;
    private String txtlocation;
    private String txtnumberchoose;
    private String txtsize;
    private String txtmodel;
    private String txtStyle;
    private String txtColor;
    private String txtArmType;
    private String txtClothType;
    private double latit;
    private double longit;
    private String imghome;
    private  String city;
    private  String hieght;
    private  String overallwidth;
    private  String waist;
    private  String sleeve;
    private  String kalosh;
    private  String extraRequest;

    @PrimaryKey
    private int id;

    public Rcy_Cart_Model() {
    }

    public Rcy_Cart_Model(String productid, String txttitle, String txtqutity, String txtprice, String txtlocation, String txtnumberchoose, String txtsize, String txtmodel, String txtStyle, String txtColor, String txtArmType, String txtClothType, double latit, double longit, String imghome, String city, String hieght, String overallwidth, String waist, String sleeve, String kalosh, String extraRequest, int id) {
        this.productid = productid;
        this.txttitle = txttitle;
        this.txtqutity = txtqutity;
        this.txtprice = txtprice;
        this.txtlocation = txtlocation;
        this.txtnumberchoose = txtnumberchoose;
        this.txtsize = txtsize;
        this.txtmodel = txtmodel;
        this.txtStyle = txtStyle;
        this.txtColor = txtColor;
        this.txtArmType = txtArmType;
        this.txtClothType = txtClothType;
        this.latit = latit;
        this.longit = longit;
        this.imghome = imghome;
        this.city = city;
        this.hieght = hieght;
        this.overallwidth = overallwidth;
        this.waist = waist;
        this.sleeve = sleeve;
        this.kalosh = kalosh;
        this.extraRequest = extraRequest;
        this.id = id;
    }


    @Override
    public String toString() {
        return "Rcy_Cart_Model{" +
                "productid='" + productid + '\'' +
                ", txttitle='" + txttitle + '\'' +
                ", txtqutity='" + txtqutity + '\'' +
                ", txtprice='" + txtprice + '\'' +
                ", txtlocation='" + txtlocation + '\'' +
                ", txtnumberchoose='" + txtnumberchoose + '\'' +
                ", txtsize='" + txtsize + '\'' +
                ", txtmodel='" + txtmodel + '\'' +
                ", txtStyle='" + txtStyle + '\'' +
                ", txtColor='" + txtColor + '\'' +
                ", txtArmType='" + txtArmType + '\'' +
                ", txtClothType='" + txtClothType + '\'' +
                ", latit=" + latit +
                ", longit=" + longit +
                ", imghome='" + imghome + '\'' +
                ", city='" + city + '\'' +
                ", hieght='" + hieght + '\'' +
                ", overallwidth='" + overallwidth + '\'' +
                ", waist='" + waist + '\'' +
                ", sleeve='" + sleeve + '\'' +
                ", kalosh='" + kalosh + '\'' +
                ", extraRequest='" + extraRequest + '\'' +
                ", id=" + id +
                '}';
    }


    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getTxttitle() {
        return txttitle;
    }

    public void setTxttitle(String txttitle) {
        this.txttitle = txttitle;
    }

    public String getTxtqutity() {
        return txtqutity;
    }

    public void setTxtqutity(String txtqutity) {
        this.txtqutity = txtqutity;
    }

    public String getTxtprice() {
        return txtprice;
    }

    public void setTxtprice(String txtprice) {
        this.txtprice = txtprice;
    }

    public String getTxtlocation() {
        return txtlocation;
    }

    public void setTxtlocation(String txtlocation) {
        this.txtlocation = txtlocation;
    }

    public String getTxtnumberchoose() {
        return txtnumberchoose;
    }

    public void setTxtnumberchoose(String txtnumberchoose) {
        this.txtnumberchoose = txtnumberchoose;
    }

    public String getTxtsize() {
        return txtsize;
    }

    public void setTxtsize(String txtsize) {
        this.txtsize = txtsize;
    }

    public String getTxtmodel() {
        return txtmodel;
    }

    public void setTxtmodel(String txtmodel) {
        this.txtmodel = txtmodel;
    }

    public String getTxtStyle() {
        return txtStyle;
    }

    public void setTxtStyle(String txtStyle) {
        this.txtStyle = txtStyle;
    }

    public String getTxtColor() {
        return txtColor;
    }

    public void setTxtColor(String txtColor) {
        this.txtColor = txtColor;
    }

    public String getTxtArmType() {
        return txtArmType;
    }

    public void setTxtArmType(String txtArmType) {
        this.txtArmType = txtArmType;
    }

    public String getTxtClothType() {
        return txtClothType;
    }

    public void setTxtClothType(String txtClothType) {
        this.txtClothType = txtClothType;
    }

    public double getLatit() {
        return latit;
    }

    public void setLatit(double latit) {
        this.latit = latit;
    }

    public double getLongit() {
        return longit;
    }

    public void setLongit(double longit) {
        this.longit = longit;
    }

    public String getImghome() {
        return imghome;
    }

    public void setImghome(String imghome) {
        this.imghome = imghome;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHieght() {
        return hieght;
    }

    public void setHieght(String hieght) {
        this.hieght = hieght;
    }

    public String getOverallwidth() {
        return overallwidth;
    }

    public void setOverallwidth(String overallwidth) {
        this.overallwidth = overallwidth;
    }

    public String getWaist() {
        return waist;
    }

    public void setWaist(String waist) {
        this.waist = waist;
    }

    public String getSleeve() {
        return sleeve;
    }

    public void setSleeve(String sleeve) {
        this.sleeve = sleeve;
    }

    public String getKalosh() {
        return kalosh;
    }

    public void setKalosh(String kalosh) {
        this.kalosh = kalosh;
    }

    public String getExtraRequest() {
        return extraRequest;
    }

    public void setExtraRequest(String extraRequest) {
        this.extraRequest = extraRequest;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
