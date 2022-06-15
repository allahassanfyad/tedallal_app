package com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioProfileFragment.Controller.UI_Profile_Fragment.ScenarioProfileOrders.Controller;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.application.tedallal_app.NetworkLayer.Apicalls;
import com.application.tedallal_app.NetworkLayer.NetworkInterface;
import com.application.tedallal_app.NetworkLayer.ResponseModel;
import com.application.tedallal_app.R;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioOrders.Model.ModelAllOrdersResponse;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.MainActivity;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioProfileFragment.Controller.Profile_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioProfileFragment.Controller.UI_Profile_Fragment.ScenarioProfileOrders.Pattrens.Rcy_Profile_Order_Adapter;
import com.application.tedallal_app.Scenarios.ScenarioMain.Pattrens.IFOnBackPressed;
import com.application.tedallal_app.local_data.saved_data;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Profile_Orders_Fragment extends Fragment implements IFOnBackPressed, NetworkInterface {



    private View view;

    RecyclerView rcyorders;
    ModelAllOrdersResponse[] allOrdersResponses;
    List<ModelAllOrdersResponse> allOrdersResponseList = new ArrayList<>();
    TextView txtnodata;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.profile_orders_fragment, container, false);
        Profile_Fragment.back = "back";

        rcyorders = view.findViewById(R.id.rcyAllOrder);
        txtnodata = view.findViewById(R.id.txtNoData);

        txtnodata.setVisibility(View.GONE);

        MainActivity.loading.setVisibility(View.VISIBLE);
        String id_user = saved_data.get_user_id(Objects.requireNonNull(getContext()));
        new Apicalls(getContext(), Profile_Orders_Fragment.this).get_All_Orders(id_user);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public boolean onBackPressed() {

//        FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
//        fr.replace(R.id.fragment_container, new Home_Fragment());
//        fr.addToBackStack(null);
//        fr.commit();
//        MainActivity.navigation.setSelectedItemId(R.id.home_nav);
        return true;
    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {
        MainActivity.loading.setVisibility(View.GONE);
        Log.e("response", model.getResponse());

        Gson gson = new Gson();

        allOrdersResponses = gson.fromJson(model.getResponse(), ModelAllOrdersResponse[].class);

        if (allOrdersResponses.length == 0) {


            txtnodata.setVisibility(View.VISIBLE);

        } else {


            for (int i = 0; i < allOrdersResponses.length; i++) {

                ModelAllOrdersResponse categoryResponse = new ModelAllOrdersResponse();

                categoryResponse.setId(allOrdersResponses[i].getId());
                categoryResponse.setAddress(allOrdersResponses[i].getAddress());
                categoryResponse.setIdAdmin(allOrdersResponses[i].getIdAdmin());
                categoryResponse.setArea(allOrdersResponses[i].getArea());
                categoryResponse.setBranch(allOrdersResponses[i].getBranch());
                categoryResponse.setCity(allOrdersResponses[i].getCity());
                categoryResponse.setCoponCode(allOrdersResponses[i].getCoponCode());
                categoryResponse.setDatee(allOrdersResponses[i].getDatee());
                categoryResponse.setDeliverCode(allOrdersResponses[i].getDeliverCode());
                categoryResponse.setDeliverDetails(allOrdersResponses[i].getDeliverDetails());
                categoryResponse.setIdAgent(allOrdersResponses[i].getIdAgent());
                categoryResponse.setIdProduct(allOrdersResponses[i].getIdProduct());
                categoryResponse.setIdUser(allOrdersResponses[i].getIdUser());
                categoryResponse.setSitelan(allOrdersResponses[i].getSitelan());
                categoryResponse.setSitelon(allOrdersResponses[i].getSitelon());
                categoryResponse.setStatus(allOrdersResponses[i].getStatus());
                categoryResponse.setTimee(allOrdersResponses[i].getTimee());
                categoryResponse.setTotlePrice(allOrdersResponses[i].getTotlePrice());


                allOrdersResponseList.add(categoryResponse);

            }

            rcyorders.setHasFixedSize(true);
            rcyorders.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));
            rcyorders.setAdapter(new Rcy_Profile_Order_Adapter(allOrdersResponseList, getActivity()));


        }


    }

    @Override
    public void OnError(VolleyError error) {
        MainActivity.loading.setVisibility(View.GONE);
        Log.e("error", error.toString());


    }
}
