package com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioSearchFragment.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
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
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Controller.Home_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Controller.UI_Home_Fragment.ScenarioHomeDetails.Model.ModelAllProductByCategoreyResponse;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioSearchFragment.Pattrens.Rcy_Search_Adapter;
import com.application.tedallal_app.Scenarios.ScenarioMain.Pattrens.IFOnBackPressed;
import com.application.tedallal_app.Utils.TinyDB;
import com.application.tedallal_app.local_data.saved_data;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Search_Fragment extends Fragment implements IFOnBackPressed, NetworkInterface {

    private View view;
    ModelAllProductByCategoreyResponse[] homeDetailsResponses;
    List<ModelAllProductByCategoreyResponse> searchList = new ArrayList<>();
    RecyclerView rcysearch;
    TinyDB tinyDB;
    TextView txtnoproduct;
    EditText editsearch;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.search_fragment, container, false);

        tinyDB = new TinyDB(getContext());

        txtnoproduct = view.findViewById(R.id.txtNoData);
        rcysearch = view.findViewById(R.id.rcySearch);
        editsearch = view.findViewById(R.id.editSearch);
        txtnoproduct.setVisibility(View.GONE);





        editsearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_SEARCH)) {

                    if (editsearch.getText().toString().equals("")) {

                        editsearch.setError(getString(R.string.please_enter_search_word));
                        YoYo.with(Techniques.Shake)
                                .duration(700)
                                .repeat(1)
                                .playOn(view.findViewById(R.id.editSearch));

                    } else {

                        tinyDB = new TinyDB(getContext());
                        tinyDB.putString("search_word", editsearch.getText().toString());
                        MainActivity.loading.setVisibility(View.VISIBLE);
                        String user_id = saved_data.get_user_id(Objects.requireNonNull(getContext()));
                        new Apicalls(getContext(),Search_Fragment.this).get_Search(user_id,editsearch.getText().toString());

                    }
                }


                return false;
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
        searchList.clear();
        MainActivity.loading.setVisibility(View.GONE);
        Log.e("response", model.getResponse());

        Gson gson = new Gson();
        homeDetailsResponses = gson.fromJson(model.getResponse(), ModelAllProductByCategoreyResponse[].class);

        if (homeDetailsResponses.length == 0){

            txtnoproduct.setVisibility(View.VISIBLE);


        }else {
            txtnoproduct.setVisibility(View.GONE);

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
                homeDetailsResponse.setPriceDiscount(homeDetailsResponses[i].getPriceDiscount());
                homeDetailsResponse.setRate(homeDetailsResponses[i].getRate());
                homeDetailsResponse.setSlider(homeDetailsResponses[i].getSlider());
                homeDetailsResponse.setTitle(homeDetailsResponses[i].getTitle());
                homeDetailsResponse.setTitleEn(homeDetailsResponses[i].getTitleEn());
                homeDetailsResponse.setNumberRate(homeDetailsResponses[i].getNumberRate());
                homeDetailsResponse.setNumberStar(homeDetailsResponses[i].getNumberStar());
                homeDetailsResponse.setTitleEn(homeDetailsResponses[i].getTitleEn());

                if (homeDetailsResponse.getIsfav().equals("yes")) {

                    homeDetailsResponse.setFavouret(true);

                }

                searchList.add(homeDetailsResponse);

            }

            rcysearch.setHasFixedSize(true);
            rcysearch.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
            rcysearch.setAdapter(new Rcy_Search_Adapter(searchList, getActivity()));
        }

    }

    @Override
    public void OnError(VolleyError error) {
        MainActivity.loading.setVisibility(View.GONE);
        Log.e("error", error.toString());

    }
}
