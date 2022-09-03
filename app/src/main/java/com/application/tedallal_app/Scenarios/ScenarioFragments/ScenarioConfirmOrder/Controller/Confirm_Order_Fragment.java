package com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioConfirmOrder.Controller;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
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
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioAllAddress.Controller.All_Address_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioCart.Controller.Cart_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioCart.Model.Rcy_Cart_Model;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioCart.Pattrens.Rcy_Cart_Adapter;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioConfirmOrder.Pattrens.Rcy_Confirm_Order_Adapter;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.MainActivity;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Controller.Home_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioProfileFragment.Controller.UI_Profile_Fragment.ScenarioDeliveryAddress.Controller.Profile_Delivery_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioMain.Pattrens.IFOnBackPressed;
import com.application.tedallal_app.Utils.Realm_adapter;
import com.application.tedallal_app.Utils.TinyDB;
import com.application.tedallal_app.local_data.saved_data;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import es.dmoral.toasty.Toasty;
import io.realm.Realm;
import io.realm.RealmResults;

public class Confirm_Order_Fragment extends Fragment implements IFOnBackPressed, NetworkInterface {

    private View view;

    TinyDB tinyDB;
    Button btnbuy, btnusepromo, btnbuycash;
    TextView txtnaigbourstret, txtcityCountry, txtcodePhone, txttotal, txtfinaltotal, txtdeliveryprice, txttaxesprice, txtDiscountprice, txtname;
    EditText editpromo;
    Realm realm;
    Realm_adapter adapter;
    RecyclerView rcyConfirmorder;
    List<Rcy_Cart_Model> cart_models;
    LinearLayout lineareditaddress;
    RelativeLayout relativeDiscount;

    String copun = "none";
    int delivary = 0;
    double taxes = 0;
    double discount = 0;
    public static int editaddress = 0;

    int x = 0;
    int d = 0;
    int l = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.confirm_order_fragment, container, false);

        tinyDB = new TinyDB(getContext());
        Realm.init(Objects.requireNonNull(getContext()));

        realm = Realm.getDefaultInstance();

        adapter = new Realm_adapter(realm);
        cart_models = adapter.retrieve();

        btnbuy = view.findViewById(R.id.btnBuy);
        btnbuycash = view.findViewById(R.id.btnBuyCash);
        txtnaigbourstret = view.findViewById(R.id.txtNaigbourStreet);
        txtcityCountry = view.findViewById(R.id.txtCityCountry);
        txtcodePhone = view.findViewById(R.id.txtCodePhone);
        rcyConfirmorder = view.findViewById(R.id.rcyConfirmOrder);
        editpromo = view.findViewById(R.id.editPromoCode);
        btnusepromo = view.findViewById(R.id.btnUsePromoCode);
        txttotal = view.findViewById(R.id.txtTotal);
        txtfinaltotal = view.findViewById(R.id.txtFinalTotal);
        txtdeliveryprice = view.findViewById(R.id.txtDeliveryPrice);
        txttaxesprice = view.findViewById(R.id.txtTaxesPrice);
        lineareditaddress = view.findViewById(R.id.linearEditAddress);
        txtname = view.findViewById(R.id.txtName);
        relativeDiscount = view.findViewById(R.id.relativeDiscount);
        txtDiscountprice = view.findViewById(R.id.txtPriceDiscount);

        relativeDiscount.setVisibility(View.GONE);

        txtnaigbourstret.setText(tinyDB.getString("addressNaigbourStreet"));
        txtcityCountry.setText(tinyDB.getString("addressCountryCity"));
        txtcodePhone.setText(tinyDB.getString("addressPhone"));
        txtname.setText(tinyDB.getString("addressName"));

        rcyConfirmorder.setHasFixedSize(true);
        rcyConfirmorder.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.HORIZONTAL, false));
        Rcy_Confirm_Order_Adapter adabter = new Rcy_Confirm_Order_Adapter(cart_models, getContext());
        rcyConfirmorder.setAdapter(adabter);

        lineareditaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editaddress = 1;
                Profile_Delivery_Fragment.address = 0;
                FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new All_Address_Fragment());
                fr.addToBackStack(null);
                fr.commit();

            }
        });


        btnusepromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (l == 1) {

                    Toasty.error(Objects.requireNonNull(getContext()), R.string.already_have_a_coupouncode, Toast.LENGTH_LONG).show();


                } else {
                    if (editpromo.getText().toString().equals("")) {


                        editpromo.setError(getString(R.string.please_enter_copoun_code));

                        YoYo.with(Techniques.Shake)
                                .duration(700)
                                .repeat(1)
                                .playOn(view.findViewById(R.id.editPromoCode));

                    } else {


                        x = 1;

                        MainActivity.loading.setVisibility(View.VISIBLE);
                        new Apicalls(getContext(), Confirm_Order_Fragment.this).add_Promo_Code(editpromo.getText().toString(), txtfinaltotal.getText().toString());


                    }

                }


            }
        });

        MainActivity.loading.setVisibility(View.VISIBLE);
        x = 2;
        new Apicalls(getContext(), Confirm_Order_Fragment.this).get_Delivery_price();


        btnbuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                tinyDB.putString("finalAmount", txtfinaltotal.getText().toString());
                tinyDB.putString("TxtTotal", txttotal.getText().toString());
                tinyDB.putString("txtTaxesPrice", txttaxesprice.getText().toString());
                tinyDB.putString("txtDiscountPrice", txtDiscountprice.getText().toString());
                tinyDB.putString("copun", copun);


                FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new Payment_Fragment());
                fr.addToBackStack(null);
                fr.commit();

            }
        });

        btnbuycash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity.loading.setVisibility(View.VISIBLE);
                send_order(copun);

            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public boolean onBackPressed() {

        FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
        fr.replace(R.id.fragment_container, new Cart_Fragment());
        fr.addToBackStack(null);
        fr.commit();
        MainActivity.navigation.setSelectedItemId(R.id.home_nav);
        return true;
    }


    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {
        Log.e("response", model.getResponse());

        if (x == 1) {
            MainActivity.loading.setVisibility(View.GONE);
            copun = editpromo.getText().toString();


            Toasty.success(Objects.requireNonNull(getContext()), R.string.promo_code_added, Toast.LENGTH_LONG).show();

            String totalAfterDisc = model.getResponse();

            d = 1;
            l = 1;
            txtfinaltotal.setText(totalAfterDisc);
            totalPrices(txttotal, txtfinaltotal);

        } else if (x == 2) {

            txtdeliveryprice.setText(model.getResponse().toString());
            delivary = Integer.parseInt(model.getResponse());

            x = 3;
            new Apicalls(getContext(), Confirm_Order_Fragment.this).get_Taxes_Price();

        } else if (x == 3) {
            MainActivity.loading.setVisibility(View.GONE);

            taxes = Double.parseDouble(model.getResponse());
            taxes = taxes / 100;

            totalPrices(txttotal, txtfinaltotal);
        }


    }

    @Override
    public void OnError(VolleyError error) {
        MainActivity.loading.setVisibility(View.GONE);
        Log.e("error", error.toString());

    }


    public void totalPrices(TextView textotal, TextView textfinaltotal) {

        realm = Realm.getDefaultInstance();
        adapter = new Realm_adapter(realm);
        cart_models = adapter.retrieve();
        int toatalprice = 0;
        for (int x = 0; x < adapter.retrieve().size(); x++) {
            double pricedecrease = 0;
            if (cart_models.get(x).getTxtprice().equals("") || cart_models.get(x).getTxtprice() == null) {

                pricedecrease = 10;

            } else {

                pricedecrease = Double.parseDouble(cart_models.get(x).getTxtprice());

            }
            int number = Integer.parseInt(cart_models.get(x).getTxtnumberchoose());

            double totalpricebefortax = pricedecrease * number;

            toatalprice += totalpricebefortax;

        }
        textotal.setText("" + toatalprice);

        delivary = Integer.parseInt(txtdeliveryprice.getText().toString());


        double taxxxxes = toatalprice * taxes;

        txttaxesprice.setText("" + taxxxxes);

        double totalPrices = toatalprice + delivary + taxxxxes;

        if (d == 1) {
            d = 0;

            relativeDiscount.setVisibility(View.VISIBLE);
            double finalprice = Double.parseDouble(txtfinaltotal.getText().toString());
            discount = finalprice - totalPrices;

            txtDiscountprice.setText("" + String.format("%.2f", discount));


        } else {

            relativeDiscount.setVisibility(View.GONE);
            txtfinaltotal.setText("" + totalPrices);

        }


    }


    private void send_order(String copun) {
        realm = Realm.getDefaultInstance();
        adapter = new Realm_adapter(realm);

        List<Rcy_Cart_Model> all = adapter.retrieve();
        String id = saved_data.get_user_id(Objects.requireNonNull(getContext()));
        String address = tinyDB.getString("addressChoose");
        Log.e("address", address);


        StringBuilder url =
                new StringBuilder("http://tadallal.com/store_app.asmx/insert_orders?id_user=" +
                        id + "&address=" + address + "&totle_price=" + txttotal.getText().toString() + "&final_totle_price=" + txtfinaltotal.getText().toString() + "&taxes=" + txttaxesprice.getText().toString() + "&discount=" + discount + "&typepay=" + "cash" + "&copon_code=" + copun);


        Log.i("functionVolly: ", id + "/" + address + "/" + txttotal.getText().toString() + "/" +
                copun);

        for (int i = 0; i <= all.size() - 1; i++) {
            assert all.get(i) != null;
            url.append("&id_product=").append(Objects.requireNonNull(all.get(i)).getProductid());

        }

        for (int a = 0; a <= all.size() - 1; a++) {
            assert all.get(a) != null;

            url.append("&item_number=").append(Objects.requireNonNull(all.get(a)).getTxtnumberchoose());

        }

        for (int s = 0; s <= all.size() - 1; s++) {
            assert all.get(s) != null;

            url.append("&item_price=").append(Objects.requireNonNull(all.get(s)).getTxtprice());

        }


        for (int s = 0; s <= all.size() - 1; s++) {
            assert all.get(s) != null;

            if (all.get(s).getExtraRequest() != null) {

                if (all.get(s).getExtraRequest().equals("")) {

                    url.append("&extra_request=").append("none");
                } else {
                    url.append("&extra_request=").append(Objects.requireNonNull(all.get(s)).getExtraRequest());
                }


            } else {

                url.append("&extra_request=").append("none");

            }
        }

        for (int s = 0; s <= all.size() - 1; s++) {
            assert all.get(s) != null;

            if (all.get(s).getTxtColor() != null) {

                url.append("&item_color=").append(Objects.requireNonNull(all.get(s)).getTxtColor());

            } else {

                url.append("&item_color=").append("none");

            }


        }


        for (int s = 0; s <= all.size() - 1; s++) {
            assert all.get(s) != null;
            if (all.get(s).getTxtsize() != null) {

                url.append("&item_size=").append(Objects.requireNonNull(all.get(s)).getTxtsize());

            } else {

                url.append("&item_size=").append("none");

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


                            adapter.delete_all();
                            MainActivity mainActivity = new MainActivity();
                            mainActivity.setcartcount();

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
