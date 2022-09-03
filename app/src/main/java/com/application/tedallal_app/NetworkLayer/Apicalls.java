package com.application.tedallal_app.NetworkLayer;

import android.content.Context;

import com.android.volley.Request;

import org.json.JSONException;

import java.util.Arrays;
import java.util.Collections;

/**
 * @desc Java Api Calls Contains all the Project Calls
 */

public class Apicalls {

    private APIRouter apiRouter;

    public Apicalls(Context context, NetworkInterface networkInterface) {

        apiRouter = new APIRouter(context, networkInterface);
    }

    //----------------------------------------------------------------------------------------------

    /**
     * @func User Login
     */

    public void loginUser(final String email, final String password) {

        apiRouter.performRequest(Apiclient.LOGIN_USER.getURL(), Apiclient.LOGIN_USER.getParams(), Arrays.asList(email, password), Request.Method.POST, Apiclient.LOGIN_USER.getCode());

    }


    //----------------------------------------------------------------------------------------------


    /**
     * @func User Registration
     */

    public void registerUser(final String name, final String email, final String password, final String phone, final String img, final String country) {

        apiRouter.performRequest(Apiclient.Register_Uer.getURL(), Apiclient.Register_Uer.getParams(), Arrays.asList(name, email, password, phone, img, country), Request.Method.POST, Apiclient.Register_Uer.getCode());


    }

    //----------------------------------------------------------------------------------------------


    /**
     * @func Edit User Profile
     */

    public void get_all_branches() {

//        apiRouter.performRequest(Apiclient.GET_ALL_BRANCHES.getURL(),Apiclient.GET_ALL_BRANCHES.getParams(),null, Request.Method.GET,Apiclient.GET_ALL_BRANCHES.getCode());

    }

    //----------------------------------------------------------------------------------------------


    /**
     * @func Add a new Ad
     */

    public void get_Forget_Password(final String email) {

        apiRouter.performRequest(Apiclient.FORGET_PASSWORD.getURL(), Apiclient.FORGET_PASSWORD.getParams(), Collections.singletonList(email), Request.Method.POST, Apiclient.FORGET_PASSWORD.getCode());

    }

    //----------------------------------------------------------------------------------------------


    /**
     * @func Main Activity Ads
     */

    public void insert_Fav(final String id_user, final String id_product) {


        apiRouter.performRequest(Apiclient.INSERT_FAV.getURL(), Apiclient.INSERT_FAV.getParams(), Arrays.asList(id_user, id_product), Request.Method.POST, Apiclient.INSERT_FAV.getCode());


    }

    //----------------------------------------------------------------------------------------------


    /**
     * @func Main Activity Ads
     */


    public void add_Address(final String name, final String country, final String city, final String area, final String street, final String posta, final String phone, final String block, final String building, final String apartment, final String floor, final String type_of_delivery_place, final String id_user, final String avenve, final String location) {


        apiRouter.performRequest(Apiclient.ADD_ADDRESS.getURL(), Apiclient.ADD_ADDRESS.getParams(), Arrays.asList(name, country, city, area, street, posta, phone, block, building, apartment, floor, type_of_delivery_place, id_user, avenve, location), Request.Method.POST, Apiclient.ADD_ADDRESS.getCode());

    }


    //----------------------------------------------------------------------------------------------


    /**
     * @func Main Activity Ads
     */

    public void get_all_address(final String id_user) {

        apiRouter.performRequest(Apiclient.GET_ALL_ADDRESS.getURL(), Apiclient.GET_ALL_ADDRESS.getParams(), Collections.singletonList(id_user), Request.Method.POST, Apiclient.GET_ALL_ADDRESS.getCode());

    }

    //----------------------------------------------------------------------------------------------


    /**
     * @func Main Activity Ads
     */

    public void add_Promo_Code(final String code, final String totle_price) {


        apiRouter.performRequest(Apiclient.ADD_PROMO.getURL(), Apiclient.ADD_PROMO.getParams(), Arrays.asList(code, totle_price), Request.Method.POST, Apiclient.ADD_PROMO.getCode());


    }

    //----------------------------------------------------------------------------------------------


    /**
     * @func Main Activity Ads
     */

    public void get_All_Fav(final String id_user) {


        apiRouter.performRequest(Apiclient.Get_All_FAVOURITE.getURL(), Apiclient.Get_All_FAVOURITE.getParams(), Collections.singletonList(id_user), Request.Method.POST, Apiclient.Get_All_FAVOURITE.getCode());


//        apiRouter.performRequest(Apiclient.SEND_ORDER.getURL(),Apiclient.SEND_ORDER.getParams(), Arrays.asList(items), Request.Method.POST,Apiclient.SEND_ORDER.getCode());

    }


    //----------------------------------------------------------------------------------------------

    /**
     * @func Main Activity Ads
     */

    public void get_User_Profile(final String id_user) {


        apiRouter.performRequest(Apiclient.USER_PROFILE.getURL(), Apiclient.USER_PROFILE.getParams(), Collections.singletonList(id_user), Request.Method.POST, Apiclient.USER_PROFILE.getCode());

    }


    //----------------------------------------------------------------------------------------------


    public void update_User_Profile(final String name, final String email, final String password, final String phone, final String img, final String country, final String id_user) {


        apiRouter.performRequest(Apiclient.UPDATE_PROFILE.getURL(), Apiclient.UPDATE_PROFILE.getParams(), Arrays.asList(name, email, password, phone, img, country, id_user), Request.Method.POST, Apiclient.UPDATE_PROFILE.getCode());


    }

    //----------------------------------------------------------------------------------------------


    public void select_All_Product(final String id_user) {

        apiRouter.performRequest(Apiclient.SELECT_ALL_PRODUCT.getURL(), Apiclient.SELECT_ALL_PRODUCT.getParams(), Collections.singletonList(id_user), Request.Method.POST, Apiclient.FORGET_PASSWORD.getCode());


    }

    //----------------------------------------------------------------------------------------------


    public void get_All_Category() {

        apiRouter.performRequest(Apiclient.SELECT_ALL_CATEGORY.getURL(), Apiclient.SELECT_ALL_CATEGORY.getParams(), null, Request.Method.POST, Apiclient.SELECT_ALL_CATEGORY.getCode());


    }

    //----------------------------------------------------------------------------------------------


    public void get_All_Product_By_Category(final String category, final String id_user) {

        apiRouter.performRequest(Apiclient.SELECT_ALL_PRODUCT_BY_CATEGORY.getURL(), Apiclient.SELECT_ALL_PRODUCT_BY_CATEGORY.getParams(), Arrays.asList(category, id_user), Request.Method.POST, Apiclient.SELECT_ALL_PRODUCT_BY_CATEGORY.getCode());

    }


    //----------------------------------------------------------------------------------------------


    public void get_All_Product_By_Offers(final String id_user) {

        apiRouter.performRequest(Apiclient.GET_ALL_PRODUCT_BY_OFFER.getURL(), Apiclient.GET_ALL_PRODUCT_BY_OFFER.getParams(), Collections.singletonList(id_user), Request.Method.POST, Apiclient.GET_ALL_PRODUCT_BY_OFFER.getCode());

    }
//----------------------------------------------------------------------------------------------


    public void get_All_Product_By_Shops(final String id_user, final String id_supplier) {

        apiRouter.performRequest(Apiclient.GET_ALL_PRODUCT_BY_SHOPS.getURL(), Apiclient.GET_ALL_PRODUCT_BY_SHOPS.getParams(), Arrays.asList(id_user, id_supplier), Request.Method.POST, Apiclient.GET_ALL_PRODUCT_BY_SHOPS.getCode());

    }

    //----------------------------------------------------------------------------------------------


    public void get_All_Shops() {

        apiRouter.performRequest(Apiclient.GET_ALL_SHOPS.getURL(), Apiclient.GET_ALL_SHOPS.getParams(), null, Request.Method.POST, Apiclient.GET_ALL_SHOPS.getCode());

    }


    //----------------------------------------------------------------------------------------------


    public void get_single_Product_Details(final String id_prodct) {


        apiRouter.performRequest(Apiclient.SELECT_SINGLE_PRODUCT.getURL(), Apiclient.SELECT_SINGLE_PRODUCT.getParams(), Collections.singletonList(id_prodct), Request.Method.POST, Apiclient.SELECT_SINGLE_PRODUCT.getCode());

    }


    //----------------------------------------------------------------------------------------------


    public void get_All_Orders(final String id_user) {


        apiRouter.performRequest(Apiclient.ALL_ORDER.getURL(), Apiclient.ALL_ORDER.getParams(), Collections.singletonList(id_user), Request.Method.POST, Apiclient.ALL_ORDER.getCode());


    }


    //----------------------------------------------------------------------------------------------


    public void get_Order_Details(final String id_order) {


        apiRouter.performRequest(Apiclient.ORDER_DETAILS.getURL(), Apiclient.ORDER_DETAILS.getParams(), Collections.singletonList(id_order), Request.Method.POST, Apiclient.ORDER_DETAILS.getCode());


    }

    //----------------------------------------------------------------------------------------------


    public void get_All_Product_Of_order(final String id_order) {

        apiRouter.performRequest(Apiclient.GET_ALL_PRODUCT_OF_ORDER.getURL(), Apiclient.GET_ALL_PRODUCT_OF_ORDER.getParams(), Collections.singletonList(id_order), Request.Method.POST, Apiclient.GET_ALL_PRODUCT_OF_ORDER.getCode());

    }


    //----------------------------------------------------------------------------------------------


    public void get_Home_Slider() {

        apiRouter.performRequest(Apiclient.GET_HOME_SLIDER.getURL(), Apiclient.GET_HOME_SLIDER.getParams(), null, Request.Method.GET, Apiclient.GET_HOME_SLIDER.getCode());

    }

    //----------------------------------------------------------------------------------------------


    public void get_Search(final String id_user, final String search_text, final String lan) {

        apiRouter.performRequest(Apiclient.SEARCH.getURL(), Apiclient.SEARCH.getParams(), Arrays.asList(id_user, search_text, lan), Request.Method.POST, Apiclient.SEARCH.getCode());


    }


    //----------------------------------------------------------------------------------------------


    public void delete_Address(final String id_address) {

        apiRouter.performRequest(Apiclient.DELETE_ADDRESS.getURL(), Apiclient.DELETE_ADDRESS.getParams(), Collections.singletonList(id_address), Request.Method.POST, Apiclient.DELETE_ADDRESS.getCode());

    }


    //----------------------------------------------------------------------------------------------


    public void get_All_Countries() {

        apiRouter.performRequest(Apiclient.GET_ALL_COUNTRIES.getURL(), Apiclient.GET_ALL_COUNTRIES.getParams(), null, Request.Method.POST, Apiclient.GET_ALL_COUNTRIES.getCode());


    }


    //----------------------------------------------------------------------------------------------


    public void edit_Address(final String name, final String country, final String city, final String area, final String street, final String posta, final String phone, final String id_user, final String id_address, final String block, final String building, final String apartment, final String floor, final String avenve) {

        apiRouter.performRequest(Apiclient.EDIT_ADDRESS.getURL(), Apiclient.EDIT_ADDRESS.getParams(), Arrays.asList(name, country, city, area, street, posta, phone, id_user, id_address, block, building, apartment, floor, avenve), Request.Method.POST, Apiclient.EDIT_ADDRESS.getCode());

    }


    //----------------------------------------------------------------------------------------------


    public void get_Best_Selling(final String id_user) {


        apiRouter.performRequest(Apiclient.GET_BEST_SELLING.getURL(), Apiclient.GET_BEST_SELLING.getParams(), Collections.singletonList(id_user), Request.Method.POST, Apiclient.GET_BEST_SELLING.getCode());

    }
    //----------------------------------------------------------------------------------------------


    /**
     * @func Main Activity Ads
     */

    public void get_Recently_Added(final String id_user) {

        apiRouter.performRequest(Apiclient.GET_RECENTLY_ADDED.getURL(), Apiclient.GET_RECENTLY_ADDED.getParams(), Collections.singletonList(id_user), Request.Method.POST, Apiclient.GET_RECENTLY_ADDED.getCode());

    }

    //----------------------------------------------------------------------------------------------


    /**
     * @func Main Activity Ads
     */

    public void get_All_Sub_Category(final String id_category) {

        apiRouter.performRequest(Apiclient.GET_ALL_SUB_CATEGORY.getURL(), Apiclient.GET_ALL_SUB_CATEGORY.getParams(), Collections.singletonList(id_category), Request.Method.POST, Apiclient.GET_ALL_SUB_CATEGORY.getCode());

    }

//----------------------------------------------------------------------------------------------


    /**
     * @func Main Activity Ads
     */

    public void get_All_Sub_Category_Product(final String id_user, final String id_category) {

        apiRouter.performRequest(Apiclient.GET_ALL_SUB_CATEGORY_Product.getURL(), Apiclient.GET_ALL_SUB_CATEGORY_Product.getParams(), Arrays.asList(id_user, id_category), Request.Method.POST, Apiclient.GET_ALL_SUB_CATEGORY_Product.getCode());

    }

//----------------------------------------------------------------------------------------------


    /**
     * @func Main Activity Ads
     */

    public void get_Product_Sizes(final String id_product) {

        apiRouter.performRequest(Apiclient.GET_Product_Sizes.getURL(), Apiclient.GET_Product_Sizes.getParams(), Collections.singletonList(id_product), Request.Method.POST, Apiclient.GET_Product_Sizes.getCode());

    }
//----------------------------------------------------------------------------------------------


    /**
     * @func Main Activity Ads
     */

    public void get_Product_Color(final String id_product) {

        apiRouter.performRequest(Apiclient.GET_Product_color.getURL(), Apiclient.GET_Product_color.getParams(), Collections.singletonList(id_product), Request.Method.POST, Apiclient.GET_Product_color.getCode());

    }

    //----------------------------------------------------------------------------------------------


    /**
     * @func Main Activity Ads
     */

    public void get_Delivery_price() {

        apiRouter.performRequest(Apiclient.GET_DELIVERY_PRICE.getURL(), Apiclient.GET_DELIVERY_PRICE.getParams(), null, Request.Method.POST, Apiclient.GET_DELIVERY_PRICE.getCode());

    }
//----------------------------------------------------------------------------------------------


    /**
     * @func Main Activity Ads
     */

    public void get_Taxes_Price() {

        apiRouter.performRequest(Apiclient.GET_TAXES_PRICE.getURL(), Apiclient.GET_TAXES_PRICE.getParams(), null, Request.Method.POST, Apiclient.GET_TAXES_PRICE.getCode());

    }


    //----------------------------------------------------------------------------------------------


    public void get_User_Car_Details(final String car_id) {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.USER_CAR_DETAILS.getURL(), Request.Method.POST, Apiclient.USER_CAR_DETAILS.getParams(), Collections.singletonList(car_id), null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    //----------------------------------------------------------------------------------------------


    /**
     * @func Main Activity Ads
     */

    public void send_Complaint_Message(final String name, final String email, final String phone, final String title, final String message) {
        try {

            apiRouter.makeAdvancedRequest(Apiclient.SEND_COMPLAINT.getURL(), Request.Method.POST, Apiclient.SEND_COMPLAINT.getParams(), Arrays.asList(name, email, phone, title, message), null);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    //----------------------------------------------------------------------------------------------


    /**
     * @func Main Activity Ads
     */

    public void get_Previous_appointment() {
        try {

            apiRouter.makeAdvancedRequest(Apiclient.PREVIOUS_APPOINTMENT.getURL(), Request.Method.GET, Apiclient.PREVIOUS_APPOINTMENT.getParams(), null, null);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    //----------------------------------------------------------------------------------------------


    public void get_Home_Title() {

        apiRouter.performRequest(Apiclient.GET_HOME_TITLE.getURL(), Apiclient.GET_HOME_TITLE.getParams(), null, Request.Method.POST, Apiclient.GET_HOME_TITLE.getCode());

    }

    //----------------------------------------------------------------------------------------------


    public void get_Next_Appointment_Details(final String appointment_id) {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.NEXT_APPOINTMENT_DETAILS.getURL(), Request.Method.POST, Apiclient.NEXT_APPOINTMENT_DETAILS.getParams(), Collections.singletonList(appointment_id), null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //----------------------------------------------------------------------------------------------


    public void get_Notification() {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.NOTIFICATION.getURL(), Request.Method.GET, Apiclient.NOTIFICATION.getParams(), null, null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    //----------------------------------------------------------------------------------------------


    public void change_Language(final String lang) {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.CHANGE_LANGUAGE.getURL(), Request.Method.POST, Apiclient.CHANGE_LANGUAGE.getParams(), Collections.singletonList(lang), null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    //----------------------------------------------------------------------------------------------


    public void check_phone_number(final String phone) {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.CHECK_PHONE_NUMBER.getURL(), Request.Method.POST, Apiclient.CHECK_PHONE_NUMBER.getParams(), Collections.singletonList(phone), null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //----------------------------------------------------------------------------------------------


    public void delete_User_Car(final String car_id) {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.DELETE_CAR.getURL(), Request.Method.POST, Apiclient.DELETE_CAR.getParams(), Collections.singletonList(car_id), null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    //----------------------------------------------------------------------------------------------


    public void get_Side_Menu() {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.SIDE_MENU.getURL(), Request.Method.GET, Apiclient.SIDE_MENU.getParams(), null, null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    //----------------------------------------------------------------------------------------------


    public void cancel_Appointment(final String appointment_id) {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.CANCEL_APPOINTMENT.getURL(), Request.Method.POST, Apiclient.CANCEL_APPOINTMENT.getParams(), Collections.singletonList(appointment_id), null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //----------------------------------------------------------------------------------------------


    public void get_All_Shops_By_Category(final String category) {

        apiRouter.performRequest(Apiclient.SELECT_ALL_SHOPS_BY_CATEGORY.getURL(), Apiclient.SELECT_ALL_SHOPS_BY_CATEGORY.getParams(), Collections.singletonList(category), Request.Method.POST, Apiclient.SELECT_ALL_SHOPS_BY_CATEGORY.getCode());

    }

//----------------------------------------------------------------------------------------------


    /**
     * @func Main Activity Ads
     */

    public void get_All_Sub_Category_Shops(final String id_sub_category) {

        apiRouter.performRequest(Apiclient.GET_ALL_SUB_CATEGORY_SHOPS.getURL(), Apiclient.GET_ALL_SUB_CATEGORY_SHOPS.getParams(), Arrays.asList(id_sub_category), Request.Method.POST, Apiclient.GET_ALL_SUB_CATEGORY_SHOPS.getCode());

    }

    //----------------------------------------------------------------------------------------------


    /**
     * @func Main Activity Ads
     */

    public void get_All_Category_Of_Shops(final String id_suppliers) {

        apiRouter.performRequest(Apiclient.GET_ALL_CATEGORY_OF_SHOPS.getURL(), Apiclient.GET_ALL_CATEGORY_OF_SHOPS.getParams(), Collections.singletonList(id_suppliers), Request.Method.POST, Apiclient.GET_ALL_CATEGORY_OF_SHOPS.getCode());

    }
//----------------------------------------------------------------------------------------------


    /**
     * @func Main Activity Ads
     */

    public void selecte_product_of_category_shops(final String id_category_suppliers,final String user) {

        apiRouter.performRequest(Apiclient.GET_ALL_PRODUCT_OF_CATEGORY_SHOP.getURL(), Apiclient.GET_ALL_PRODUCT_OF_CATEGORY_SHOP.getParams(), Arrays.asList(id_category_suppliers, user), Request.Method.POST, Apiclient.GET_ALL_SUB_CATEGORY_SHOPS.getCode());

    }


    //----------------------------------------------------------------------------------------------


    /**
     * @func Main Activity Ads
     */

    public void get_Extra_Requests(final String id_product) {

        apiRouter.performRequest(Apiclient.GET_EXTRA_REQUESTS.getURL(), Apiclient.GET_EXTRA_REQUESTS.getParams(), Collections.singletonList(id_product), Request.Method.POST, Apiclient.GET_EXTRA_REQUESTS.getCode());

    }


    //----------------------------------------------------------------------------------------------


    public void get_Shops_Home() {

        apiRouter.performRequest(Apiclient.GET_SHOPS_Home.getURL(), Apiclient.GET_SHOPS_Home.getParams(), null, Request.Method.POST, Apiclient.GET_SHOPS_Home.getCode());

    }


}