package com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioProfileFragment.Controller.UI_Profile_Fragment.ScenarioProfileOrders.Controller;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.application.tedallal_app.NetworkLayer.Apicalls;
import com.application.tedallal_app.NetworkLayer.NetworkInterface;
import com.application.tedallal_app.NetworkLayer.ResponseModel;
import com.application.tedallal_app.R;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioOrders.Model.ModelAllOrdersResponse;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioOrders.Model.ModelOrdersDetailsResponse;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioOrders.Pattrens.Rcy_Order_Product_Adapter;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.MainActivity;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Controller.Home_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioProfileFragment.Controller.Profile_Fragment;
import com.application.tedallal_app.Utils.TinyDB;
import com.application.tedallal_app.local_data.saved_data;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import es.dmoral.toasty.Toasty;
import io.realm.Realm;

public class Profile_Order_Details extends Fragment implements NetworkInterface {

    private View view;
    RecyclerView rcyorderdetails;
    TextView txtordernumber, txtprice, txtstatus, txttime, txtdate, txtdeliverycmpany, txtshipmentnumber, txtaddressname, txtaddress;
    ModelAllOrdersResponse[] allOrdersResponses;
    ModelOrdersDetailsResponse[] ordersDetailsResponses;
    List<ModelOrdersDetailsResponse> ordersDetailsList = new ArrayList<>();
    TinyDB tinyDB;
    int x = 0;
    String order_id;
    Button btnreorder;
    String copun = "none";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.profile_order_details_fragment, container, false);

        Profile_Fragment.back = "Order_Details";
        tinyDB = new TinyDB(getContext());

        rcyorderdetails = view.findViewById(R.id.rcyOrderDetails);

        txtaddress = view.findViewById(R.id.txtAddress);
//        txtaddressname = view.findViewById(R.id.txtAddressName);

        txtdate = view.findViewById(R.id.txtDate);
//        txtdeliverycmpany = view.findViewById(R.id.txtDeliveryCompany);
        txtordernumber = view.findViewById(R.id.txtOrderNumber);
        txtprice = view.findViewById(R.id.txtPrice);
//        txtshipmentnumber = view.findViewById(R.id.txtShipmentNumber);
        txtstatus = view.findViewById(R.id.txtStatus);
        txttime = view.findViewById(R.id.txtTime);
        btnreorder = view.findViewById(R.id.btnReorder);

        x = 1;
        order_id = tinyDB.getString("orderID");
        MainActivity.loading.setVisibility(View.VISIBLE);
        new Apicalls(getContext(), Profile_Order_Details.this).get_Order_Details(order_id);

        btnreorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ordersDetailsList.size() == 0) {

                    Log.e("noProduct", "No Product To Reorder");

                } else {
                    MainActivity.loading.setVisibility(View.VISIBLE);
                    send_order1(copun);
                }


            }
        });


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {

        Log.e("response", model.getResponse());

        if (x == 1) {
            Gson gson = new Gson();

            allOrdersResponses = gson.fromJson(model.getResponse(), ModelAllOrdersResponse[].class);


//        ModelAllOrdersResponse categoryResponse = new ModelAllOrdersResponse();

            txttime.setText(allOrdersResponses[0].getTimee());
            txtstatus.setText(allOrdersResponses[0].getStatus());
            txtprice.setText(allOrdersResponses[0].getTotlePrice());
            txtordernumber.setText("" + allOrdersResponses[0].getId());
            copun = allOrdersResponses[0].getCoponCode();

//            String datetime = allOrdersResponses[0].getDatee();
//            String[] splitingdate = datetime.split(" ");
//            String date = splitingdate[0];
//            String time = splitingdate[1];
//            Log.e("dateTime", date + "......." + time);


            txtdate.setText(allOrdersResponses[0].getDateeeeee());

            txtaddress.setText(allOrdersResponses[0].getAddress());

//        categoryResponse.setId(allOrdersResponses[0].getId());
//        categoryResponse.setAddress(allOrdersResponses[0].getAddress());
//        categoryResponse.setIdAdmin(allOrdersResponses[0].getIdAdmin());
//        categoryResponse.setArea(allOrdersResponses[0].getArea());
//        categoryResponse.setBranch(allOrdersResponses[0].getBranch());
//        categoryResponse.setCity(allOrdersResponses[0].getCity());
//        categoryResponse.setCoponCode(allOrdersResponses[0].getCoponCode());
//        categoryResponse.setDatee(allOrdersResponses[0].getDatee());
//        categoryResponse.setDeliverCode(allOrdersResponses[0].getDeliverCode());
//        categoryResponse.setDeliverDetails(allOrdersResponses[0].getDeliverDetails());
//        categoryResponse.setIdAgent(allOrdersResponses[0].getIdAgent());
//        categoryResponse.setIdProduct(allOrdersResponses[0].getIdProduct());
//        categoryResponse.setIdUser(allOrdersResponses[0].getIdUser());
//        categoryResponse.setSitelan(allOrdersResponses[0].getSitelan());
//        categoryResponse.setSitelon(allOrdersResponses[0].getSitelon());
//        categoryResponse.setStatus(allOrdersResponses[0].getStatus());
//        categoryResponse.setTimee(allOrdersResponses[0].getTimee());
//        categoryResponse.setTotlePrice(allOrdersResponses[0].getTotlePrice());

            x = 2;
            new Apicalls(getContext(), Profile_Order_Details.this).get_All_Product_Of_order(order_id);


        } else if (x == 2) {
            MainActivity.loading.setVisibility(View.GONE);
            Gson gson = new Gson();

            ordersDetailsResponses = gson.fromJson(model.getResponse(), ModelOrdersDetailsResponse[].class);

            for (int i = 0; i < ordersDetailsResponses.length; i++) {

                ModelOrdersDetailsResponse ordersDetails = new ModelOrdersDetailsResponse();

                ordersDetails.setCategory(ordersDetailsResponses[i].getCategory());

                ordersDetails.setColor(ordersDetailsResponses[i].getColor());
                ordersDetails.setColorEn(ordersDetailsResponses[i].getColorEn());
                ordersDetails.setDatee(ordersDetailsResponses[i].getDatee());
                ordersDetails.setDateeeeee(ordersDetailsResponses[i].getDateeeeee());
                ordersDetails.setDes(ordersDetailsResponses[i].getDes());
                ordersDetails.setDesEn(ordersDetailsResponses[i].getDesEn());
                ordersDetails.setExtraRequest(ordersDetailsResponses[i].getExtraRequest());
                ordersDetails.setId(ordersDetailsResponses[i].getId());
                ordersDetails.setId1(ordersDetailsResponses[i].getId1());
                ordersDetails.setIdAdmin(ordersDetailsResponses[i].getIdAdmin());
                ordersDetails.setIdOrder(ordersDetailsResponses[i].getIdOrder());
                ordersDetails.setIdProduct(ordersDetailsResponses[i].getIdProduct());
                ordersDetails.setImg1(ordersDetailsResponses[i].getImg1());
                ordersDetails.setImg2(ordersDetailsResponses[i].getImg2());
                ordersDetails.setImg3(ordersDetailsResponses[i].getImg3());
                ordersDetails.setItemAlkalosh(ordersDetailsResponses[i].getItemAlkalosh());
                ordersDetails.setItemAlkhasr(ordersDetailsResponses[i].getItemAlkhasr());
                ordersDetails.setItemArm(ordersDetailsResponses[i].getItemArm());
                ordersDetails.setItemLength(ordersDetailsResponses[i].getItemLength());
                ordersDetails.setItemNumber(ordersDetailsResponses[i].getItemNumber());
                ordersDetails.setItemPrice(ordersDetailsResponses[i].getItemPrice());
                ordersDetails.setItemSize(ordersDetailsResponses[i].getItemSize());
                ordersDetails.setItemWidth(ordersDetailsResponses[i].getItemWidth());
                ordersDetails.setMeteral(ordersDetailsResponses[i].getMeteral());
                ordersDetails.setMeteralEn(ordersDetailsResponses[i].getMeteralEn());
                ordersDetails.setModel(ordersDetailsResponses[i].getModel());
                ordersDetails.setModelEn(ordersDetailsResponses[i].getModelEn());
                ordersDetails.setNumberRate(ordersDetailsResponses[i].getNumberRate());
                ordersDetails.setNumberStar(ordersDetailsResponses[i].getNumberStar());
                ordersDetails.setPrice(ordersDetailsResponses[i].getPrice());
                ordersDetails.setPriceDiscount(ordersDetailsResponses[i].getPriceDiscount());
                ordersDetails.setSlider(ordersDetailsResponses[i].getSlider());
                ordersDetails.setSort(ordersDetailsResponses[i].getSort());
                ordersDetails.setStyles(ordersDetailsResponses[i].getStyles());
                ordersDetails.setStylesEn(ordersDetailsResponses[i].getStylesEn());
                ordersDetails.setTitle(ordersDetailsResponses[i].getTitle());
                ordersDetails.setTitleEn(ordersDetailsResponses[i].getTitleEn());
                ordersDetails.setType(ordersDetailsResponses[i].getType());
                ordersDetails.setTypeArmEn(ordersDetailsResponses[i].getTypeArmEn());
                ordersDetails.setTypeArmEn(ordersDetailsResponses[i].getTypeArmEn());


                ordersDetailsList.add(ordersDetails);

            }

            rcyorderdetails.setHasFixedSize(true);
            rcyorderdetails.setLayoutManager(new LinearLayoutManager(getContext()));
            Rcy_Order_Product_Adapter adabter = new Rcy_Order_Product_Adapter(ordersDetailsList, getContext());
            rcyorderdetails.setAdapter(adabter);


        }


    }

    @Override
    public void OnError(VolleyError error) {
        MainActivity.loading.setVisibility(View.GONE);
        Log.e("error", error.toString());
    }


//    private void send_order(String copun) {
//
//        String id = saved_data.get_user_id(Objects.requireNonNull(getContext()));
//
//
//
//        StringBuilder url =
//                new StringBuilder("http://app.alshal.sa/alshal.asmx/insert_orders?id_user=" +
//                        id + "&address=" + txtaddress.getText().toString() + "&totle_price=" + txtprice.getText().toString() + "&copon_code=" + copun);
//
//
//        Log.i("functionVolly: ", id + "/" + txtaddress.getText().toString() + "/" + txtprice.getText().toString() + "/" +
//                copun);
//
//        for (int i = 0; i <= ordersDetailsList.size() - 1; i++) {
//            assert ordersDetailsList.get(i) != null;
//            url.append("&id_product=").append(Objects.requireNonNull(ordersDetailsList.get(i)).getIdProduct());
//
//        }
//
//        for (int a = 0; a <= ordersDetailsList.size() - 1; a++) {
//            assert ordersDetailsList.get(a) != null;
//
//            url.append("&item_number=").append(Objects.requireNonNull(ordersDetailsList.get(a)).getItemNumber());
//
//        }
//
//        for (int s = 0; s <= ordersDetailsList.size() - 1; s++) {
//            assert ordersDetailsList.get(s) != null;
//
//            url.append("&item_price=").append(Objects.requireNonNull(ordersDetailsList.get(s)).getItemPrice());
//
//        }
//
//        for (int s = 0; s <= ordersDetailsList.size() - 1; s++) {
//            assert ordersDetailsList.get(s) != null;
//
//            url.append("&item_length=").append("none");
//
//        }
//
//        for (int s = 0; s <= ordersDetailsList.size() - 1; s++) {
//            assert ordersDetailsList.get(s) != null;
//
//            url.append("&item_size=").append("none");
//
//
//        }
//
//        for (int s = 0; s <= ordersDetailsList.size() - 1; s++) {
//            assert ordersDetailsList.get(s) != null;
//
//            url.append("&item_width=").append("none");
//
//        }
//
//
//        for (int s = 0; s <= ordersDetailsList.size() - 1; s++) {
//            assert ordersDetailsList.get(s) != null;
//
//
//            url.append("&item_arm=").append("none");
//
//
//        }
//
//
//        for (int s = 0; s <= ordersDetailsList.size() - 1; s++) {
//            assert ordersDetailsList.get(s) != null;
//
//            url.append("&item_alkhasr=").append("none");
//
//        }
//
//
//        for (int s = 0; s <= ordersDetailsList.size() - 1; s++) {
//            assert ordersDetailsList.get(s) != null;
//
//            url.append("&item_alkalosh=").append("none");
//
//        }
//
//
//        Log.i("sdsdsdsdsdsdsdsdsd", "functionVolly: " + url.toString());
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url.toString(),
//
//                new com.android.volley.Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        MainActivity.loading.setVisibility(View.GONE);
//
//                        if (response.equals("True")) {
//
//
////                            Toasty.error(Objects.requireNonNull(getContext()), "تم ارسال الطلب", Toast.LENGTH_LONG).show();
//
//
//                            Toasty.success(Objects.requireNonNull(getContext()), R.string.your_order_completed_successfully, Toast.LENGTH_LONG).show();
////                            FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
////                            fr.replace(R.id.fragment_container, new Home_Fragment());
////                            fr.addToBackStack(null);
////                            fr.commit();
////                            MainActivity.navigation.setSelectedItemId(R.id.home_nav);
//
////                                if (advSearchRadioBtn.isChecked()) {
////                                    Payment();
////                                } else {
////                                    Toast.makeText(getContext(), "تم ارسال الطلب", Toast.LENGTH_LONG).show();
////                                    startActivity(new Intent(getContext(), HomeActivity.class));
////                                    Objects.requireNonNull(getActivity()).finish();
////                                }
//                        }
//
//                    }
//                }, new com.android.volley.Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                MainActivity.loading.setVisibility(View.GONE);
//
//                Log.i("sdsdsdsdsd", "onErrorResponse: " + error.toString());
//                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
//
//            }
//
//        });
//
//        stringRequest.setShouldCache(false);
//        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
//                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 7,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        Volley.newRequestQueue(getContext()).add(stringRequest);
////        RequestHandler.getInstance(getActivity()).addToRequestQueue(stringRequest);
//
//
//    }


    private void send_order1(String copun) {

        String id = saved_data.get_user_id(Objects.requireNonNull(getContext()));
//        realm = Realm.getDefaultInstance();
//        adapter = new Realm_adapter(realm);

//        List<Rcy_Cart_Model> all = adapter.retrieve();
//        String id = saved_data.get_user_id(Objects.requireNonNull(getContext()));
        String address = tinyDB.getString("addressChoose");
        Log.e("address", address);


        StringBuilder url =
                new StringBuilder("http://app.alshal.sa/alshal.asmx/insert_orders?id_user=" +
                        id + "&address=" + txtaddress.getText().toString() + "&totle_price=" + txtprice.getText().toString() + "&copon_code=" + copun);


        Log.i("functionVolly: ", id + "/" + txtaddress.getText().toString() + "/" + txtprice.getText().toString() + "/" +
                copun);

        for (int i = 0; i <= ordersDetailsList.size() - 1; i++) {
            assert ordersDetailsList.get(i) != null;
            url.append("&id_product=").append(Objects.requireNonNull(ordersDetailsList.get(i)).getIdProduct());

        }

        for (int a = 0; a <= ordersDetailsList.size() - 1; a++) {
            assert ordersDetailsList.get(a) != null;

            url.append("&item_number=").append(Objects.requireNonNull(ordersDetailsList.get(a)).getItemNumber());

        }

        for (int s = 0; s <= ordersDetailsList.size() - 1; s++) {
            assert ordersDetailsList.get(s) != null;

            url.append("&item_price=").append(Objects.requireNonNull(ordersDetailsList.get(s)).getItemPrice());

        }

        for (int s = 0; s <= ordersDetailsList.size() - 1; s++) {
            assert ordersDetailsList.get(s) != null;

            if (ordersDetailsList.get(s).getItemLength() != null) {

                url.append("&item_length=").append(Objects.requireNonNull(ordersDetailsList.get(s)).getItemLength());

            } else {

                url.append("&item_length=").append("none");

            }

        }

        for (int s = 0; s <= ordersDetailsList.size() - 1; s++) {
            assert ordersDetailsList.get(s) != null;
            if (ordersDetailsList.get(s).getItemSize() != null) {

                url.append("&item_size=").append(Objects.requireNonNull(ordersDetailsList.get(s)).getItemSize());

            } else {

                url.append("&item_size=").append("none");

            }

        }

        for (int s = 0; s <= ordersDetailsList.size() - 1; s++) {
            assert ordersDetailsList.get(s) != null;

            if (ordersDetailsList.get(s).getItemWidth() != null) {

                url.append("&item_width=").append(Objects.requireNonNull(ordersDetailsList.get(s)).getItemWidth());

            } else {

                url.append("&item_width=").append("none");

            }


        }


        for (int s = 0; s <= ordersDetailsList.size() - 1; s++) {
            assert ordersDetailsList.get(s) != null;

            if (ordersDetailsList.get(s).getItemArm() != null) {

                url.append("&item_arm=").append(Objects.requireNonNull(ordersDetailsList.get(s)).getItemArm());

            } else {

                url.append("&item_arm=").append("none");

            }


        }


        for (int s = 0; s <= ordersDetailsList.size() - 1; s++) {
            assert ordersDetailsList.get(s) != null;

            if (ordersDetailsList.get(s).getItemAlkhasr() != null) {

                url.append("&item_alkhasr=").append(Objects.requireNonNull(ordersDetailsList.get(s)).getItemAlkhasr());

            } else {

                url.append("&item_alkhasr=").append("none");

            }


        }


        for (int s = 0; s <= ordersDetailsList.size() - 1; s++) {
            assert ordersDetailsList.get(s) != null;

            if (ordersDetailsList.get(s).getItemAlkalosh() != null) {

                url.append("&item_alkalosh=").append(Objects.requireNonNull(ordersDetailsList.get(s)).getItemAlkalosh());

            } else {

                url.append("&item_alkalosh=").append("none");

            }


        }

        for (int s = 0; s <= ordersDetailsList.size() - 1; s++) {
            assert ordersDetailsList.get(s) != null;

            if (ordersDetailsList.get(s).getExtraRequest() != null) {

                if (ordersDetailsList.get(s).getExtraRequest().equals("")) {

                    url.append("&extra_request=").append("none");
                } else {
                    url.append("&extra_request=").append(Objects.requireNonNull(ordersDetailsList.get(s)).getExtraRequest());
                }


            } else {

                url.append("&extra_request=").append("none");

            }
        }


        Log.i("sdsdsdsdsdsdsdsdsd", "functionVolly: " + url.toString());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url.toString(),

                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        MainActivity.loading.setVisibility(View.GONE);

                        if (response.equals("True")) {


//                            Toasty.error(Objects.requireNonNull(getContext()), "تم ارسال الطلب", Toast.LENGTH_LONG).show();


                            Toasty.success(Objects.requireNonNull(getContext()), R.string.your_order_completed_successfully, Toast.LENGTH_LONG).show();
                            FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                            fr.replace(R.id.fragment_container, new Home_Fragment());
                            fr.addToBackStack(null);
                            fr.commit();
                            MainActivity.navigation.setSelectedItemId(R.id.home_nav);

//                                if (advSearchRadioBtn.isChecked()) {
//                                    Payment();
//                                } else {
//                                    Toast.makeText(getContext(), "تم ارسال الطلب", Toast.LENGTH_LONG).show();
//                                    startActivity(new Intent(getContext(), HomeActivity.class));
//                                    Objects.requireNonNull(getActivity()).finish();
//                                }
                        }

                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                MainActivity.loading.setVisibility(View.GONE);

                Log.i("sdsdsdsdsd", "onErrorResponse: " + error.toString());
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();

            }

        });

        stringRequest.setShouldCache(false);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 7,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(getContext()).add(stringRequest);
//        RequestHandler.getInstance(getActivity()).addToRequestQueue(stringRequest);


    }
}
