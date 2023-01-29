package com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioShops.Controller;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.application.tedallal_app.NetworkLayer.Apicalls;
import com.application.tedallal_app.NetworkLayer.NetworkInterface;
import com.application.tedallal_app.NetworkLayer.ResponseModel;
import com.application.tedallal_app.R;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioOffers.Controller.Offers_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioOffers.Patterns.Rcy_Offers_Adapter;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioShops.Models.Model_Shops;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioShops.Patterns.Rcy_Shops_Adapter;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.MainActivity;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Controller.Home_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Controller.UI_Home_Fragment.ScenarioHomeDetails.Model.ModelAllProductByCategoreyResponse;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Controller.UI_Home_Fragment.ScenarioHomeDetails.Pattrens.Rcy_Sub_Category_Adapter;
import com.application.tedallal_app.Scenarios.ScenarioMain.Pattrens.IFOnBackPressed;
import com.application.tedallal_app.Utils.TinyDB;
import com.application.tedallal_app.local_data.saved_data;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Shops_Fragment extends Fragment implements NetworkInterface, IFOnBackPressed {


    private View view;
    TinyDB tinyDB;
    RecyclerView rcyShops;
    Model_Shops[] model_shops;
    List<Model_Shops> shops_list = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.shops_fragment, container, false);

        tinyDB = new TinyDB(getContext());
        rcyShops = view.findViewById(R.id.rcyShops);


        MainActivity.loading.setVisibility(View.VISIBLE);
        new Apicalls(getContext(), Shops_Fragment.this).get_All_Shops();


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
        MainActivity.loading.setVisibility(View.GONE);

        Gson gson = new Gson();
        model_shops = gson.fromJson(model.getResponse(), Model_Shops[].class);

        for (int i = 0; i < model_shops.length; i++) {

            Model_Shops shop = new Model_Shops();
            shop.setId(model_shops[i].getId());
            shop.setIdSuppliers(model_shops[i].getIdSuppliers());
            shop.setIdAdmin(model_shops[i].getIdAdmin());
            shop.setNameSuppliers(model_shops[i].getNameSuppliers());
            shop.setAddress(model_shops[i].getAddress());
            shop.setNameCompany(model_shops[i].getNameCompany());
            shop.setPhone(model_shops[i].getPhone());
            shop.setEmail(model_shops[i].getEmail());
            shop.setPassword(model_shops[i].getPassword());
            shop.setImg(model_shops[i].getImg());
            shop.setDatee(model_shops[i].getDatee());
            shop.setPercent(model_shops[i].getPercent());
            shop.setTypeSuppliers(model_shops[i].getTypeSuppliers());
            shop.setNameEmploy(model_shops[i].getNameEmploy());
            shop.setDateeStart(model_shops[i].getDateeStart());
            shop.setDateeEnd(model_shops[i].getDateeEnd());
            shop.setStatusSuppliers(model_shops[i].getStatusSuppliers());
            shop.setTypeShop(model_shops[i].getTypeShop());
            shop.setClosingTime(model_shops[i].getClosingTime());
            shop.setOpeningTime(model_shops[i].getOpeningTime());
            shop.setCover_suppliers(model_shops[i].getCover_suppliers());
            shop.setWhatsapp(model_shops[i].getWhatsapp());
            shop.setInstagram(model_shops[i].getInstagram());
            shop.setSnapchat(model_shops[i].getSnapchat());
            shops_list.add(shop);

        }

        rcyShops.setHasFixedSize(true);
        rcyShops.setLayoutManager(new GridLayoutManager(getContext(), 3, GridLayoutManager.VERTICAL, false));
        rcyShops.setAdapter(new Rcy_Shops_Adapter(shops_list, getActivity()));


    }

    @Override
    public void OnError(VolleyError error) {
        MainActivity.loading.setVisibility(View.GONE);
        Log.e("error", error.toString());
    }


    @Override
    public boolean onBackPressed() {

        FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
        fr.replace(R.id.fragment_container, new Home_Fragment());
        fr.addToBackStack(null);
        fr.commit();

        return true;
    }

}
