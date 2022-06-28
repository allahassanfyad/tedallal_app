package com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioOffers.Controller;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

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
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioOffers.Patterns.Rcy_Offers_Adapter;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.MainActivity;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Controller.Home_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Controller.UI_Home_Fragment.ScenarioHomeDetails.Controller.Home_Details_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Controller.UI_Home_Fragment.ScenarioHomeDetails.Model.ModelAllProductByCategoreyResponse;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Controller.UI_Home_Fragment.ScenarioHomeDetails.Pattrens.Rcy_Home_Details_Adapter;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioSearchFragment.Controller.Search_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioMain.Pattrens.IFOnBackPressed;
import com.application.tedallal_app.Utils.TinyDB;
import com.application.tedallal_app.local_data.saved_data;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Offers_Fragment extends Fragment implements NetworkInterface, IFOnBackPressed {


    private View view;
    TinyDB tinyDB;
    RecyclerView rcyOffers;
    ModelAllProductByCategoreyResponse[] homeDetailsResponses;
    List<ModelAllProductByCategoreyResponse> homeDetailsList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.offers_fragment, container, false);

        tinyDB = new TinyDB(getContext());
        rcyOffers = view.findViewById(R.id.rcyOffers);


        MainActivity.loading.setVisibility(View.VISIBLE);
        new Apicalls(getContext(), Offers_Fragment.this).get_All_Product_By_Offers(saved_data.get_user_id(Objects.requireNonNull(getContext())));


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
        homeDetailsResponses = gson.fromJson(model.getResponse(), ModelAllProductByCategoreyResponse[].class);

        for (int i = 0; i < homeDetailsResponses.length; i++) {

            ModelAllProductByCategoreyResponse homeDetailsResponse = new ModelAllProductByCategoreyResponse();

            homeDetailsResponse.setCategory(homeDetailsResponses[i].getCategory());
            homeDetailsResponse.setDatee(homeDetailsResponses[i].getDatee());
            homeDetailsResponse.setDes(homeDetailsResponses[i].getDes());
            homeDetailsResponse.setDesEn(homeDetailsResponses[i].getDesEn());
            homeDetailsResponse.setId(homeDetailsResponses[i].getId());
            homeDetailsResponse.setImg1(homeDetailsResponses[i].getImg1());
            homeDetailsResponse.setImg2(homeDetailsResponses[i].getImg2());
            homeDetailsResponse.setImg3(homeDetailsResponses[i].getImg3());
            homeDetailsResponse.setPrice(homeDetailsResponses[i].getPrice());
            homeDetailsResponse.setIsfav(homeDetailsResponses[i].getIsfav());
            homeDetailsResponse.setPriceDiscount(homeDetailsResponses[i].getPriceDiscount());
            homeDetailsResponse.setRate(homeDetailsResponses[i].getRate());
            homeDetailsResponse.setSlider(homeDetailsResponses[i].getSlider());
            homeDetailsResponse.setTitle(homeDetailsResponses[i].getTitle());
            homeDetailsResponse.setTitleEn(homeDetailsResponses[i].getTitleEn());
            homeDetailsResponse.setNumberRate(homeDetailsResponses[i].getNumberRate());
            homeDetailsResponse.setNumberStar(homeDetailsResponses[i].getNumberStar());

            if (homeDetailsResponse.getIsfav().equals("yes")) {

                homeDetailsResponse.setFavouret(true);

            }

            homeDetailsList.add(homeDetailsResponse);

        }

        rcyOffers.setHasFixedSize(true);
        rcyOffers.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
        rcyOffers.setAdapter(new Rcy_Offers_Adapter(homeDetailsList, getActivity()));
        DividerItemDecoration verticalDecoration = new DividerItemDecoration(rcyOffers.getContext(),
                DividerItemDecoration.HORIZONTAL);
        Drawable verticalDivider = ContextCompat.getDrawable(getContext(), R.drawable.vertical_divider);
        verticalDecoration.setDrawable(verticalDivider);
        rcyOffers.addItemDecoration(verticalDecoration);

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
