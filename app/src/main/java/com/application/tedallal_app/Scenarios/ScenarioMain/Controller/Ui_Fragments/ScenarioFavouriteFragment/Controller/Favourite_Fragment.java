package com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioFavouriteFragment.Controller;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.MainActivity;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioFavouriteFragment.Model.ModelAllFavouriteResponse;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioFavouriteFragment.Pattrens.Rcy_All_Favourite_Adapter;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Controller.Home_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioMain.Pattrens.IFOnBackPressed;
import com.application.tedallal_app.Utils.TinyDB;
import com.application.tedallal_app.local_data.saved_data;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Favourite_Fragment extends Fragment implements IFOnBackPressed, NetworkInterface {


    private View view;
    RecyclerView rcyFavourite;
    TinyDB tinyDB;
    ModelAllFavouriteResponse[] allFavouriteResponses;
    List<ModelAllFavouriteResponse> allfavouriteList = new ArrayList<>();
    public static int favBack = 0;
    TextView txtnodata;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.favourite_fragment, container, false);

        tinyDB = new TinyDB(getContext());

        rcyFavourite = view.findViewById(R.id.rcyFavourite);
        txtnodata = view.findViewById(R.id.txtNoData);

        MainActivity.loading.setVisibility(View.VISIBLE);
        new Apicalls(getContext(), Favourite_Fragment.this).get_All_Fav(saved_data.get_user_id(Objects.requireNonNull(getContext())));


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public boolean onBackPressed() {

        FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
        fr.replace(R.id.fragment_container, new Home_Fragment());
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
        MainActivity.loading.setVisibility(View.GONE);
        Log.e("response", model.getResponse());

        allfavouriteList.clear();

        Gson gson = new Gson();
        allFavouriteResponses = gson.fromJson(model.getResponse(), ModelAllFavouriteResponse[].class);
        if (allFavouriteResponses.length == 0) {


            txtnodata.setVisibility(View.VISIBLE);

        } else {


            for (int i = 0; i < allFavouriteResponses.length; i++) {

                ModelAllFavouriteResponse favouriteResponse = new ModelAllFavouriteResponse();

                favouriteResponse.setCategory(allFavouriteResponses[i].getCategory());
                favouriteResponse.setDatee(allFavouriteResponses[i].getDatee());
                favouriteResponse.setDes(allFavouriteResponses[i].getDes());
                favouriteResponse.setDesEn(allFavouriteResponses[i].getDesEn());
                favouriteResponse.setId(allFavouriteResponses[i].getId());
                favouriteResponse.setImg1(allFavouriteResponses[i].getImg1());
                favouriteResponse.setImg2(allFavouriteResponses[i].getImg2());
                favouriteResponse.setImg3(allFavouriteResponses[i].getImg3());
                favouriteResponse.setPrice(allFavouriteResponses[i].getPrice());
                favouriteResponse.setPriceDiscount(allFavouriteResponses[i].getPriceDiscount());
                favouriteResponse.setRate(allFavouriteResponses[i].getRate());
                favouriteResponse.setSlider(allFavouriteResponses[i].getSlider());
                favouriteResponse.setTitle(allFavouriteResponses[i].getTitle());
                favouriteResponse.setTitleEn(allFavouriteResponses[i].getTitleEn());


                favouriteResponse.setIdAdmin(allFavouriteResponses[i].getIdAdmin());
                favouriteResponse.setNumberRate(allFavouriteResponses[i].getNumberRate());
                favouriteResponse.setNumberStar(allFavouriteResponses[i].getNumberStar());
                favouriteResponse.setSort(allFavouriteResponses[i].getSort());
                favouriteResponse.setIdProduct(allFavouriteResponses[i].getIdProduct());


//            if (homeDetailsResponse.getIsfav().equals("yes")) {
//
//                homeDetailsResponse.setIsFav(true);
//
//            }

                allfavouriteList.add(favouriteResponse);

            }

            rcyFavourite.setHasFixedSize(true);
            rcyFavourite.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
            rcyFavourite.setAdapter(new Rcy_All_Favourite_Adapter(allfavouriteList, getActivity()));


        }

    }

    @Override
    public void OnError(VolleyError error) {
        MainActivity.loading.setVisibility(View.GONE);
        Log.e("error", error.toString());

    }
}
