package com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioConfirmOrder.Controller;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.application.tedallal_app.NetworkLayer.Config;
import com.application.tedallal_app.R;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioCart.Model.Rcy_Cart_Model;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.MainActivity;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Controller.Home_Fragment;
import com.application.tedallal_app.Utils.Realm_adapter;
import com.application.tedallal_app.Utils.TinyDB;
import com.application.tedallal_app.local_data.saved_data;
import com.google.gson.Gson;
import com.myfatoorah.sdk.entity.executepayment.MFExecutePaymentRequest;
import com.myfatoorah.sdk.entity.initiatepayment.MFInitiatePaymentRequest;
import com.myfatoorah.sdk.entity.initiatepayment.MFInitiatePaymentResponse;
import com.myfatoorah.sdk.entity.initiatesession.MFInitiateSessionResponse;
import com.myfatoorah.sdk.entity.paymentstatus.MFGetPaymentStatusResponse;
import com.myfatoorah.sdk.utils.MFAPILanguage;
import com.myfatoorah.sdk.utils.MFCountry;
import com.myfatoorah.sdk.utils.MFCurrencyISO;
import com.myfatoorah.sdk.utils.MFEnvironment;
import com.myfatoorah.sdk.views.MFResult;
import com.myfatoorah.sdk.views.MFSDK;
import com.myfatoorah.sdk.views.embeddedpayment.MFPaymentCardView;

import java.util.List;
import java.util.Objects;

import es.dmoral.toasty.Toasty;
import io.realm.Realm;
import kotlin.Unit;

public class Payment_Fragment extends Fragment {

    private View view;
    private MFPaymentCardView mfPaymentView;
    private Button btPay;
    TextView txtamount;
    Realm realm;
    Realm_adapter adapter;
    TinyDB tinyDB;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.payment_fragment, container, false);


        tinyDB = new TinyDB(getContext());

        MFSDK.INSTANCE.init(Config.API_KEY, MFCountry.KUWAIT, MFEnvironment.LIVE);

        if (Config.API_KEY.isEmpty()) {
            showAlertDialog("Missing API Key.. You can get it from here: https://myfatoorah.readme.io/docs/demo-information");
        }

        txtamount = view.findViewById(R.id.txtAmount);
        btPay = view.findViewById(R.id.btPayEmbeddedPayment);
        mfPaymentView = view.findViewById(R.id.mfPaymentView);


        // You can custom your action bar, but this is optional not required to set this line
        MFSDK.INSTANCE.setUpActionBar("MyFatoorah Payment", R.color.colorPrimaryDark,
                R.color.colorGray2, true);
        initiatePayment();
        initiateSession();

        btPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                payWithEmbeddedPayment();
            }
        });

        Realm.init(Objects.requireNonNull(getContext()));

        realm = Realm.getDefaultInstance();

        adapter = new Realm_adapter(realm);

        String finalAmount = tinyDB.getString("finalAmount");
        txtamount.setText(finalAmount);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    private void showAlertDialog(String text) {
        new AlertDialog.Builder(Objects.requireNonNull(getContext()))
                .setMessage(text)
                .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void initiatePayment() {
        MainActivity.loading.setVisibility(View.VISIBLE);

        double invoiceAmount = Double.parseDouble(txtamount.getText().toString());
        MFInitiatePaymentRequest request = new MFInitiatePaymentRequest(
                invoiceAmount, MFCurrencyISO.KUWAIT_KWD);

        MFSDK.INSTANCE.initiatePayment(request, MFAPILanguage.EN,
                (MFResult<MFInitiatePaymentResponse> result) -> {
                    if (result instanceof MFResult.Success) {
                        Log.d("success", "Response: " + new Gson().toJson(
                                ((MFResult.Success<MFInitiatePaymentResponse>) result).getResponse()));

                    } else if (result instanceof MFResult.Fail) {
                        Log.d("faild", "Error: " + new Gson().toJson(((MFResult.Fail) result).getError()));
                    }
                    MainActivity.loading.setVisibility(View.GONE);

                    return Unit.INSTANCE;
                });
    }

    private void initiateSession() {
        MainActivity.loading.setVisibility(View.VISIBLE);

        /**
         * If you want to use saved card option with embedded payment, send the parameter
         * "customerIdentifier" with a unique value for each customer. This value cannot be used
         * for more than one Customer. Like this:
         */
//        MFInitiateSessionRequest request = new MFInitiateSessionRequest("12332212");
//        MFSDK.INSTANCE.initiateSession(request, (MFResult<MFInitiateSessionResponse> result) -> {

        /**
         * If not, sent it with null. Like this.
         */
        MFSDK.INSTANCE.initiateSession(null, (MFResult<MFInitiateSessionResponse> result) -> {
            if (result instanceof MFResult.Success) {
                Log.d("success", "Response: " + new Gson().toJson(((MFResult.Success<MFInitiateSessionResponse>) result).getResponse()));
                mfPaymentView.load(((MFResult.Success<MFInitiateSessionResponse>) result).getResponse());
            }
            if (result instanceof MFResult.Fail) {
                Log.d("faild", "Fail: " + new Gson().toJson(((MFResult.Fail) result).getError()));
            }

            MainActivity.loading.setVisibility(View.GONE);

            return Unit.INSTANCE;
        });
    }


    private void payWithEmbeddedPayment() {
        MainActivity.loading.setVisibility(View.VISIBLE);

        double invoiceAmount = Double.parseDouble(txtamount.getText().toString());
        MFExecutePaymentRequest request = new MFExecutePaymentRequest(invoiceAmount);

        mfPaymentView.pay(
                Objects.requireNonNull(getActivity()),
                request,
                MFAPILanguage.EN,
                (String invoiceId) -> {
                    Log.d("invoiceId", "invoiceId: " + invoiceId);
                    return Unit.INSTANCE;
                },
                (String invoiceId, MFResult<MFGetPaymentStatusResponse> result) -> {
                    if (result instanceof MFResult.Success) {
                        Log.d("success", "Response: " + new Gson().toJson(((MFResult.Success<MFGetPaymentStatusResponse>) result).getResponse()));
//                        showAlertDialog("Payment done successfully");

                        MainActivity.loading.setVisibility(View.VISIBLE);
                        String copun = tinyDB.getString("copun");
                        send_order(copun);

                    } else if (result instanceof MFResult.Fail) {
                        String error = new Gson().toJson(((MFResult.Fail) result).getError());
                        Log.d("fail", "Fail: " + error);
                        showAlertDialog(error);
                    }

                    MainActivity.loading.setVisibility(View.GONE);

                    return Unit.INSTANCE;
                });
    }


    private void send_order(String copun) {
        realm = Realm.getDefaultInstance();
        adapter = new Realm_adapter(realm);

        List<Rcy_Cart_Model> all = adapter.retrieve();
        String id = saved_data.get_user_id(Objects.requireNonNull(getContext()));
        String address = tinyDB.getString("addressChoose");
        Log.e("address", address);


        String txttotal = tinyDB.getString("TxtTotal");
        String txttaxprice = tinyDB.getString("txtTaxesPrice");
        String txtdiscountprice = tinyDB.getString("txtDiscountPrice");


        StringBuilder url =
                new StringBuilder("http://tadallal.com/store_app.asmx/insert_orders?id_user=" +
                        id + "&address=" + address + "&totle_price=" + txttotal + "&final_totle_price=" + txtamount.getText().toString() + "&taxes=" + txttaxprice + "&discount=" + txtdiscountprice + "&typepay=" + "cash" + "&copon_code=" + copun);


        Log.i("functionVolly: ", id + "/" + address + "/" + txttotal + "/" +
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
