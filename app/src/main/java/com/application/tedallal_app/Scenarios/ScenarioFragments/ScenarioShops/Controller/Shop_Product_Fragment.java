package com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioShops.Controller;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioShops.Models.ModelSubCategoryShopsResponse;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioShops.Patterns.Rcy_Shops_Adapter;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioShops.Patterns.Rcy_Shops_Product_Adapter;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioShops.Patterns.Rcy_Sub_Category_Shop_Adapter;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.MainActivity;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Controller.Home_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Controller.UI_Home_Fragment.ScenarioHomeDetails.Controller.Home_Details_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Controller.UI_Home_Fragment.ScenarioHomeDetails.Controller.Home_Details_Fragment2;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Controller.UI_Home_Fragment.ScenarioHomeDetails.Model.ModelAllProductByCategoreyResponse;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Controller.UI_Home_Fragment.ScenarioHomeDetails.Model.ModelSubCategoryResponse;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Controller.UI_Home_Fragment.ScenarioHomeDetails.Pattrens.Rcy_Sub_Category_Adapter;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Pattrens.Rcy_Shops_Home_Adapter;
import com.application.tedallal_app.Scenarios.ScenarioMain.Pattrens.IFOnBackPressed;
import com.application.tedallal_app.Utils.TinyDB;
import com.application.tedallal_app.local_data.saved_data;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Shop_Product_Fragment extends Fragment implements NetworkInterface, IFOnBackPressed {


    private View view;
    TinyDB tinyDB;
    TextView txtTitle, txtOpeningTime, txtClosedTime;
    ImageView imgLogo, imgCover;
    RelativeLayout relativeStatus;
    RecyclerView rcyShopsProduct, rcySubCategory;
    List<ModelSubCategoryShopsResponse> subCategoryList = new ArrayList<>();
    ModelSubCategoryShopsResponse[] modelSubCategoryResponses;
    ModelAllProductByCategoreyResponse[] homeDetailsResponses;
    List<ModelAllProductByCategoreyResponse> homeDetailsList = new ArrayList<>();
    int x = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.shop_product_fragment, container, false);

        tinyDB = new TinyDB(getContext());
        rcyShopsProduct = view.findViewById(R.id.rcyShopsProduct);
        rcySubCategory = view.findViewById(R.id.rcySubCategoryShop);

        txtTitle = view.findViewById(R.id.txtTitle);
        txtOpeningTime = view.findViewById(R.id.txtOpenTime);
        txtClosedTime = view.findViewById(R.id.txtClosedTime);
        imgLogo = view.findViewById(R.id.imgLogo);
        relativeStatus = view.findViewById(R.id.relativeStatus);
        imgCover = view.findViewById(R.id.imgCover);

        String status = tinyDB.getString("Type_Shop");
        String title = tinyDB.getString("Company_Name");
        String closedDate = tinyDB.getString("Closing_Time");
        String openDate = tinyDB.getString("Opening_Time");
        String logo = tinyDB.getString("Shop_Logo");
        String cover = tinyDB.getString("cover_suppliers");

        txtTitle.setText(title);
        txtClosedTime.setText(closedDate);
        txtOpeningTime.setText(openDate);

        Glide.with(Objects.requireNonNull(getContext()))
                .load(logo)
                .placeholder(R.drawable.holder_png)
                .into(imgLogo);

        Glide.with(Objects.requireNonNull(getContext()))
                .load(cover)
                .placeholder(R.drawable.holder_png)
                .into(imgCover);

        if (status.equals("Opening")) {

            final int sdk = android.os.Build.VERSION.SDK_INT;

            if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {

                relativeStatus.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.circle_background_green_small));

            } else {

                relativeStatus.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.circle_background_green_small));
            }


        } else if (status.equals("Closed")) {

            final int sdk = android.os.Build.VERSION.SDK_INT;

            if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {

                relativeStatus.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.circle_background_gray_small));

            } else {

                relativeStatus.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.circle_background_gray_small));
            }

        }


        x = 1;
        String id_supplier = tinyDB.getString("Id_Supplier");
        MainActivity.loading.setVisibility(View.VISIBLE);
        new Apicalls(getContext(), Shop_Product_Fragment.this).get_All_Product_By_Shops(saved_data.get_user_id(Objects.requireNonNull(getContext())), id_supplier);


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

            rcyShopsProduct.setHasFixedSize(true);
            rcyShopsProduct.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
            rcyShopsProduct.setAdapter(new Rcy_Shops_Product_Adapter(homeDetailsList, getActivity()));
            DividerItemDecoration verticalDecoration = new DividerItemDecoration(rcyShopsProduct.getContext(),
                    DividerItemDecoration.HORIZONTAL);
            Drawable verticalDivider = ContextCompat.getDrawable(getContext(), R.drawable.vertical_divider);
            verticalDecoration.setDrawable(verticalDivider);
            rcyShopsProduct.addItemDecoration(verticalDecoration);

            x = 2;
            String id_supplier = tinyDB.getString("Id_Supplier");
            new Apicalls(getContext(), Shop_Product_Fragment.this).get_All_Category_Of_Shops(id_supplier);


        } else if (x == 2) {
            MainActivity.loading.setVisibility(View.GONE);

            Gson gson = new Gson();
            modelSubCategoryResponses = gson.fromJson(model.getResponse(), ModelSubCategoryShopsResponse[].class);

            for (int i = 0; i < modelSubCategoryResponses.length; i++) {

                ModelSubCategoryShopsResponse subCategoryResponse = new ModelSubCategoryShopsResponse();

                subCategoryResponse.setId(modelSubCategoryResponses[i].getId());
                subCategoryResponse.setId_admin(modelSubCategoryResponses[i].getId_admin());
                subCategoryResponse.setTitle_ar(modelSubCategoryResponses[i].getTitle_ar());
                subCategoryResponse.setTitle_en(modelSubCategoryResponses[i].getTitle_en());
                subCategoryResponse.setId_suppliers(modelSubCategoryResponses[i].getId_suppliers());
                subCategoryResponse.setId_category(modelSubCategoryResponses[i].getId_category());
                subCategoryResponse.setDatee(modelSubCategoryResponses[i].getDatee());
                subCategoryResponse.setImg_category(modelSubCategoryResponses[i].getImg_category());
                subCategoryResponse.setName_category(modelSubCategoryResponses[i].getName_category());

                subCategoryList.add(subCategoryResponse);
            }

            rcySubCategory.setHasFixedSize(true);
            rcySubCategory.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.HORIZONTAL, false));
            rcySubCategory.setAdapter(new Rcy_Sub_Category_Shop_Adapter(subCategoryList, getActivity(), rcyShopsProduct));


        }

    }

    @Override
    public void OnError(VolleyError error) {
        MainActivity.loading.setVisibility(View.GONE);
        Log.e("error", error.toString());
    }

    @Override
    public boolean onBackPressed() {

        if (Rcy_Shops_Home_Adapter.shops_Home == 1) {

            Rcy_Shops_Home_Adapter.shops_Home = 0;

            FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
            fr.replace(R.id.fragment_container, new Home_Fragment());
            fr.addToBackStack(null);
            fr.commit();

        }else {

            FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
            fr.replace(R.id.fragment_container, new Home_Details_Fragment2());
            fr.addToBackStack(null);
            fr.commit();
        }


        return true;
    }
}
