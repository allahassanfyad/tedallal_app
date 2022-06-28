package com.application.tedallal_app.NetworkLayer;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Apiclient {

    /**
     * @API ---> 1) URL OF API METHOD
     * <p>
     * ---> 2) ARRAY OF PARAMETERS KEYS
     */

    LOGIN_USER("login_user", Arrays.asList("email", "pass"), 1),
    Register_Uer("register", Arrays.asList("name", "email", "password", "phone", "img", "country"), 2),
    SELECT_ALL_PRODUCT("select_all_product", Collections.singletonList("id_user"), 3),
    FORGET_PASSWORD("forgete_password_by_email", Collections.singletonList("email"), 4),
    INSERT_FAV("insert_fav", Arrays.asList("id_user", "id_product"), 5),
    ADD_ADDRESS("insert_address_user", Arrays.asList("name", "country", "city", "area", "street", "posta", "phone", "id_user"), 6),
    GET_ALL_ADDRESS("select_address", Collections.singletonList("id_user"), 7),
    ADD_PROMO("insert_copoun_code", Arrays.asList("code", "totle_price"), 8),
    Get_All_FAVOURITE("selecte_all_fav", Collections.singletonList("id_user"), 9),
    USER_PROFILE("user_profile", Collections.singletonList("id_user"), 10),
    UPDATE_PROFILE("update_user", Arrays.asList("name", "email", "password", "phone", "img", "country", "id_user"), 11),
    SELECT_ALL_CATEGORY("selecte_all_category", null, 12),
    SELECT_ALL_PRODUCT_BY_CATEGORY("select_all_product_by_category", Arrays.asList("category", "id_user"), 13),
    SELECT_SINGLE_PRODUCT("select_single_product", Collections.singletonList("id_prodct"), 14),
    ALL_ORDER("selecte_all_orders", Collections.singletonList("id_user"), 15),
    ORDER_DETAILS("selecte_all_order_details", Collections.singletonList("id_order"), 16),
    GET_ALL_PRODUCT_OF_ORDER("selecte_all_product_of_orders", Collections.singletonList("id_order"), 17),
    GET_HOME_SLIDER("select_slider", null, 18),
    SEARCH("search_product", Arrays.asList("id_user", "search_text", "lan"), 19),
    DELETE_ADDRESS("delete_address_user", Collections.singletonList("id_address"), 20),
    EDIT_ADDRESS("update_address_user", Arrays.asList("name", "country", "city", "area", "street", "posta", "phone", "id_user", "id_address"), 21),
    GET_ALL_COUNTRIES("selecte_countries", null, 22),
    GET_BEST_SELLING("select_all_product_best_slides_saling", Collections.singletonList("id_user"), 23),
    GET_RECENTLY_ADDED("select_all_product_recently_added", Collections.singletonList("id_user"), 24),
    USER_CAR_DETAILS("client/user/car-details", Collections.singletonList("car_id"), 25),
    SEND_COMPLAINT("client/message", Arrays.asList("name", "email", "phone", "title", "message"), 26),
    PREVIOUS_APPOINTMENT("client/appointment/last-appointment", null, 27),
    GET_HOME_TITLE("select_title", null, 28),
    NEXT_APPOINTMENT_DETAILS("client/appointment/next-appointment-details", Collections.singletonList("appointment_id"), 29),
    NOTIFICATION("client/notification", null, 30),
    CHANGE_LANGUAGE("client/change-language", Collections.singletonList("lang"), 31),
    CHECK_PHONE_NUMBER("client/auth/check-phone", Collections.singletonList("phone"), 32),
    DELETE_CAR("client/car/delete", Collections.singletonList("car_id"), 33),
    SIDE_MENU("client/user/side-menu", null, 34),
    CANCEL_APPOINTMENT("client/appointment/cancel", Collections.singletonList("appointment_id"), 35),
    GET_ALL_SUB_CATEGORY("selecte_all_sub_category", Collections.singletonList("id_category"), 36),
    GET_ALL_SUB_CATEGORY_Product("select_all_product_by_sub_category", Arrays.asList("id_user", "id_subcategory"), 37),
    GET_Product_Sizes("selecte_size_of_product", Collections.singletonList("id_product"), 38),
    GET_Product_color("selecte_color_of_product", Collections.singletonList("id_product"), 39),
    GET_DELIVERY_PRICE("select_value_delivery", null, 40),
    GET_TAXES_PRICE("select_taxes", null, 41),
    GET_ALL_PRODUCT_BY_OFFER("select_all_product_offer", Collections.singletonList("id_user"), 42),
    GET_ALL_SHOPS("selecte_all_shopes", null, 43),
    GET_ALL_PRODUCT_BY_SHOPS("select_all_product_by_shops", Arrays.asList("id_user","id_supplier"), 44);


    //--------------------------------------

    /**
     * @BASE_URL
     */

    String BASE_URL = "http://tadallal.com/store_app.asmx/";

    private final String URL;
    private final List<String> params;
    private final int code;


    Apiclient(String URL, List<String> params, int code) {

        this.URL = URL;
        this.params = params;
        this.code = code;
    }

    public String getURL() {
        return BASE_URL + URL;
    }

    public List<String> getParams() {
        return params;
    }

    public int getCode() {
        return code;
    }
}
