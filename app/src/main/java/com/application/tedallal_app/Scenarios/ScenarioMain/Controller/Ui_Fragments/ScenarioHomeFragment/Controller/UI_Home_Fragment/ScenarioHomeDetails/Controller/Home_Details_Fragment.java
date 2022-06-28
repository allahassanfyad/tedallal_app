package com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Controller.UI_Home_Fragment.ScenarioHomeDetails.Controller;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.application.tedallal_app.NetworkLayer.Apicalls;
import com.application.tedallal_app.NetworkLayer.NetworkInterface;
import com.application.tedallal_app.NetworkLayer.ResponseModel;
import com.application.tedallal_app.R;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.MainActivity;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Controller.Home_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Controller.UI_Home_Fragment.ScenarioHomeDetails.Model.ModelAllProductByCategoreyResponse;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Controller.UI_Home_Fragment.ScenarioHomeDetails.Model.ModelSubCategoryResponse;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Controller.UI_Home_Fragment.ScenarioHomeDetails.Pattrens.Rcy_Home_Details_Adapter;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Controller.UI_Home_Fragment.ScenarioHomeDetails.Pattrens.Rcy_Sub_Category_Adapter;
import com.application.tedallal_app.Scenarios.ScenarioMain.Pattrens.IFOnBackPressed;
import com.application.tedallal_app.Utils.TinyDB;
import com.application.tedallal_app.local_data.saved_data;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Home_Details_Fragment extends Fragment implements IFOnBackPressed, NetworkInterface {


    private View view;
    public static RecyclerView rcyhomedetails;
    RecyclerView rcySubCategory;
    ModelAllProductByCategoreyResponse[] homeDetailsResponses;
    ModelSubCategoryResponse[] modelSubCategoryResponses;
    List<ModelAllProductByCategoreyResponse> homeDetailsList = new ArrayList<>();
    List<ModelSubCategoryResponse> subCategoryList = new ArrayList<>();
    TinyDB tinyDB;
    TextView txtcategoryname;
    int x = 0;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_details_fragment, container, false);

        tinyDB = new TinyDB(getContext());
        rcyhomedetails = view.findViewById(R.id.rcyHomeDetails);
        rcySubCategory = view.findViewById(R.id.rcySubCategory);
        txtcategoryname = view.findViewById(R.id.txtCategoryName);


        txtcategoryname.setText(tinyDB.getString("CategoryName"));

//
//        if (saved_data.get_user_id(Objects.requireNonNull(getContext())).equals("0") || saved_data.get_user_id(Objects.requireNonNull(getContext())).equals("")) {
//
//            MainActivity.loading.setVisibility(View.VISIBLE);
//            new Apicalls(getContext(), Home_Details_Fragment.this).select_All_Product("0");
//
//        } else {
//
//            MainActivity.loading.setVisibility(View.VISIBLE);
//            new Apicalls(getContext(), Home_Details_Fragment.this).select_All_Product(saved_data.get_user_id(getContext()));
//
//        }


        x = 1;
        String category = tinyDB.getString("CategoryIdd");
        MainActivity.loading.setVisibility(View.VISIBLE);
        new Apicalls(getContext(), Home_Details_Fragment.this).get_All_Product_By_Category(category, saved_data.get_user_id(getContext()));


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
        Log.e("response", model.getResponse());

        if (x == 1) {
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

            rcyhomedetails.setHasFixedSize(true);
            rcyhomedetails.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
            rcyhomedetails.setAdapter(new Rcy_Home_Details_Adapter(homeDetailsList, getActivity()));
            DividerItemDecoration verticalDecoration = new DividerItemDecoration(rcyhomedetails.getContext(),
                    DividerItemDecoration.HORIZONTAL);
            Drawable verticalDivider = ContextCompat.getDrawable(getContext(), R.drawable.vertical_divider);
            verticalDecoration.setDrawable(verticalDivider);
            rcyhomedetails.addItemDecoration(verticalDecoration);

            x = 2;
            String category_idd = tinyDB.getString("CategoryIdd");
            new Apicalls(getContext(), Home_Details_Fragment.this).get_All_Sub_Category(category_idd);


        } else if (x == 2) {

            MainActivity.loading.setVisibility(View.GONE);

            Gson gson = new Gson();
            modelSubCategoryResponses = gson.fromJson(model.getResponse(), ModelSubCategoryResponse[].class);

            for (int i = 0; i < modelSubCategoryResponses.length; i++) {

                ModelSubCategoryResponse subCategoryResponse = new ModelSubCategoryResponse();

                subCategoryResponse.setId_category(modelSubCategoryResponses[i].getId_category());
                subCategoryResponse.setNamesub_en(modelSubCategoryResponses[i].getNamesub_en());
                subCategoryResponse.setId(modelSubCategoryResponses[i].getId());
                subCategoryResponse.setNamesub(modelSubCategoryResponses[i].getNamesub());
                subCategoryResponse.setDateregister(modelSubCategoryResponses[i].getDateregister());
                subCategoryResponse.setUrlsub(modelSubCategoryResponses[i].getUrlsub());
                subCategoryResponse.setImgsub(modelSubCategoryResponses[i].getImgsub());
                subCategoryResponse.setId_admin(modelSubCategoryResponses[i].getId_admin());

                subCategoryList.add(subCategoryResponse);
            }

            rcySubCategory.setHasFixedSize(true);
            rcySubCategory.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.HORIZONTAL, false));
            rcySubCategory.setAdapter(new Rcy_Sub_Category_Adapter(subCategoryList, getActivity(), rcyhomedetails));


//            DividerItemDecoration verticalDecoration = new DividerItemDecoration(rcyhomedetails.getContext(),
//                    DividerItemDecoration.HORIZONTAL);
//            Drawable verticalDivider = ContextCompat.getDrawable(getContext(), R.drawable.vertical_divider);
//            verticalDecoration.setDrawable(verticalDivider);
//            rcySubCategory.addItemDecoration(verticalDecoration);


        }


    }

    @Override
    public void OnError(VolleyError error) {
        MainActivity.loading.setVisibility(View.GONE);
        Log.e("error", error.toString());

    }
}
