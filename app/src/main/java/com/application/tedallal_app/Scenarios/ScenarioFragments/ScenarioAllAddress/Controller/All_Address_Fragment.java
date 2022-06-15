package com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioAllAddress.Controller;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.application.tedallal_app.NetworkLayer.Apicalls;
import com.application.tedallal_app.NetworkLayer.NetworkInterface;
import com.application.tedallal_app.NetworkLayer.ResponseModel;
import com.application.tedallal_app.R;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioAddAddress.Controller.Add_Address_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioAllAddress.Model.ModelAllAddressResponse;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioAllAddress.Pattrens.Rcy_All_Address_Adapter;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioCart.Controller.Cart_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioConfirmOrder.Controller.Confirm_Order_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.MainActivity;
import com.application.tedallal_app.Scenarios.ScenarioMain.Pattrens.IFOnBackPressed;
import com.application.tedallal_app.local_data.saved_data;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class All_Address_Fragment extends Fragment implements IFOnBackPressed, NetworkInterface {

    private View view;

    Button btnaddaddress;
    RecyclerView rcyallAddress;
    ModelAllAddressResponse[] allAddressResponses;
    List<ModelAllAddressResponse> allAddressList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.all_address_fragment, container, false);


        btnaddaddress = view.findViewById(R.id.btnAddAddress);
        rcyallAddress = view.findViewById(R.id.rcyAllAddress);

        btnaddaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new Add_Address_Fragment());
                fr.addToBackStack(null);
                fr.commit();

            }
        });


        MainActivity.loading.setVisibility(View.VISIBLE);
        new Apicalls(getContext(), All_Address_Fragment.this).get_all_address(saved_data.get_user_id(getContext()));


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public boolean onBackPressed() {

        if (Confirm_Order_Fragment.editaddress == 1) {
            Confirm_Order_Fragment.editaddress = 0;

            FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
            fr.replace(R.id.fragment_container, new Confirm_Order_Fragment());
            fr.addToBackStack(null);
            fr.commit();


        } else {

            FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
            fr.replace(R.id.fragment_container, new Cart_Fragment());
            fr.addToBackStack(null);
            fr.commit();
//        MainActivity.navigation.setSelectedItemId(R.id.home_nav);
        }
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
        allAddressResponses = gson.fromJson(model.getResponse(), ModelAllAddressResponse[].class);

        for (int i = 0; i < allAddressResponses.length; i++) {

            ModelAllAddressResponse modelAllAddressResponse = new ModelAllAddressResponse();

            modelAllAddressResponse.setAddress(allAddressResponses[i].getAddress());
            modelAllAddressResponse.setDatee(allAddressResponses[i].getDatee());
            modelAllAddressResponse.setId(allAddressResponses[i].getId());
            modelAllAddressResponse.setIdUser(allAddressResponses[i].getIdUser());
            modelAllAddressResponse.setName(allAddressResponses[i].getName());
            modelAllAddressResponse.setCountry(allAddressResponses[i].getCountry());
            modelAllAddressResponse.setCity(allAddressResponses[i].getCity());
            modelAllAddressResponse.setArea(allAddressResponses[i].getArea());
            modelAllAddressResponse.setStreet(allAddressResponses[i].getStreet());
            modelAllAddressResponse.setPosta(allAddressResponses[i].getPosta());
            modelAllAddressResponse.setPhone(allAddressResponses[i].getPhone());

            allAddressList.add(modelAllAddressResponse);

        }

        rcyallAddress.setHasFixedSize(true);
        rcyallAddress.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));
        rcyallAddress.setAdapter(new Rcy_All_Address_Adapter(allAddressList, getActivity()));
    }

    @Override
    public void OnError(VolleyError error) {
        MainActivity.loading.setVisibility(View.GONE);
        Log.e("error", error.toString());

    }
}
