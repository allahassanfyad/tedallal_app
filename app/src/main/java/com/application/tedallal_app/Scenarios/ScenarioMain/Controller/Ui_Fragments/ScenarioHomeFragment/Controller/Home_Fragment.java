package com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Controller;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.android.volley.VolleyError;
import com.application.tedallal_app.NetworkLayer.Apicalls;
import com.application.tedallal_app.NetworkLayer.NetworkInterface;
import com.application.tedallal_app.NetworkLayer.ResponseModel;
import com.application.tedallal_app.R;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioAddCart.Controller.Add_Cart_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioAddCart.Model.mode_introduction;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioOffers.Controller.Offers_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioOffers.Patterns.Rcy_Offers_Adapter;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioShops.Controller.Shops_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioShops.Patterns.Rcy_Shops_Product_Adapter;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.MainActivity;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Controller.UI_Home_Fragment.ScenarioHomeDetails.Model.ModelAllProductByCategoreyResponse;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Model.ModelAllCategoryResponse;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Model.ModelHomeSlider;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Pattrens.Rcy_Best_Selling_Adapter;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Pattrens.Rcy_Home_Adapter;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Pattrens.Rcy_Recently_Added_Adapter;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Pattrens.Rcy_Slider_Home_adapter;
import com.application.tedallal_app.Scenarios.ScenarioMain.Pattrens.IFOnBackPressed;
import com.application.tedallal_app.Utils.TinyDB;
import com.application.tedallal_app.local_data.saved_data;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class Home_Fragment extends Fragment implements IFOnBackPressed, NetworkInterface {

    private View view;
    RecyclerView rcyHome, rcybestselling, rcyrecentadded;
    ModelAllCategoryResponse[] allCategoryResponses;
    List<ModelAllCategoryResponse> homeList = new ArrayList<>();
    ModelAllProductByCategoreyResponse[] homeDetailsResponses;
    List<ModelAllProductByCategoreyResponse> bestSellingList = new ArrayList<>();
    List<ModelAllProductByCategoreyResponse> recentlyAddedList = new ArrayList<>();
    ViewPager2 viewPager;
    List<mode_introduction> models = new ArrayList<>();
    Rcy_Slider_Home_adapter adapter;
    private LinearLayout linearindecator;
    ModelHomeSlider[] homeSliders;
    TinyDB tinyDB;
    Button btnoffers, btnshopd;
    int currentPage = 0;
    int NUM_PAGES = 0;
    public static Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000; // time in milliseconds between successive task executions.
    Handler handler;
    Runnable Update;
//    TextView txthometitle;


    int x = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, container, false);

        tinyDB = new TinyDB(getContext());

        rcyHome = view.findViewById(R.id.rcyHome);
        rcybestselling = view.findViewById(R.id.rcyBestSelling);
        rcyrecentadded = view.findViewById(R.id.rcyRecentlyAdded);
        btnoffers = view.findViewById(R.id.btnOffers);
        btnshopd = view.findViewById(R.id.btnShops);

        linearindecator = view.findViewById(R.id.linearIndicator);
//        txthometitle = view.findViewById(R.id.txtHomeTitle);
        timer = new Timer(); // This will create a new Thread

        Rcy_Offers_Adapter.offers = 0;
        Rcy_Shops_Product_Adapter.shops = 0;

        x = 1;
        MainActivity.loading.setVisibility(View.VISIBLE);
        new Apicalls(getContext(), Home_Fragment.this).get_Home_Title();

        btnoffers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new Offers_Fragment());
                fr.addToBackStack(null);
                fr.commit();

            }
        });

        btnshopd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new Shops_Fragment());
                fr.addToBackStack(null);
                fr.commit();

            }
        });


        return view;
    }


    ViewPager2.OnPageChangeCallback onPageChangeCallback = new ViewPager2.OnPageChangeCallback() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels);

        }

        @Override
        public void onPageSelected(int position) {
            super.onPageSelected(position);
            Log.e("position", String.valueOf(position));
            int pos = position % linearindecator.getChildCount();
            updateIndicator(pos);
            Log.e("position", String.valueOf(pos));


        }

        @Override
        public void onPageScrollStateChanged(int state) {
            super.onPageScrollStateChanged(state);
        }
    };

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public boolean onBackPressed() {
        timer.cancel();
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getActivity().startActivity(a);
        getActivity().finish();
        return true;

    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {


        Log.e("response", model.getResponse());

        if (x == 1) {

            if (model.getResponse() != null) {

//                txthometitle.setText("" + model.getResponse());
                Log.e("responnnnse", model.getResponse());
            }


            x = 2;
            new Apicalls(getContext(), Home_Fragment.this).get_All_Category();

        } else if (x == 2) {
            homeList.clear();

            Gson gson = new Gson();

            allCategoryResponses = gson.fromJson(model.getResponse(), ModelAllCategoryResponse[].class);

            for (int i = 0; i < allCategoryResponses.length; i++) {

                ModelAllCategoryResponse categoryResponse = new ModelAllCategoryResponse();

                categoryResponse.setId(allCategoryResponses[i].getId());
                categoryResponse.setDateregister(allCategoryResponses[i].getDateregister());
                categoryResponse.setIdAdmin(allCategoryResponses[i].getIdAdmin());
                categoryResponse.setImg(allCategoryResponses[i].getImg());
                categoryResponse.setName(allCategoryResponses[i].getName());
                categoryResponse.setNameEn(allCategoryResponses[i].getNameEn());

                homeList.add(categoryResponse);

            }

            rcyHome.setHasFixedSize(true);
            rcyHome.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
            rcyHome.setAdapter(new Rcy_Home_Adapter(homeList, getActivity()));
            DividerItemDecoration verticalDecoration = new DividerItemDecoration(rcyHome.getContext(),
                    DividerItemDecoration.HORIZONTAL);
            Drawable verticalDivider = ContextCompat.getDrawable(getContext(), R.drawable.vertical_divider);
            verticalDecoration.setDrawable(verticalDivider);
            rcyHome.addItemDecoration(verticalDecoration);


            x = 3;
            new Apicalls(getContext(), Home_Fragment.this).get_Home_Slider();


        } else if (x == 3) {
            models.clear();

            Gson gson = new Gson();

            homeSliders = gson.fromJson(model.getResponse(), ModelHomeSlider[].class);

            if (homeSliders.length != 0) {

                for (int i = 0; i < homeSliders.length; i++) {

                    models.add(new mode_introduction(homeSliders[i].getImg()));
                    NUM_PAGES = homeSliders.length;

                }


                adapter = new Rcy_Slider_Home_adapter(models, getContext());

                viewPager = view.findViewById(R.id.viewPager);
//        recyclerView = findViewById(R.id.rcyintroduction);
                viewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
                viewPager.setAdapter(adapter);
//        viewPager.setPadding(150, 0, 150, 0);
                viewPager.setClipToPadding(false);
                viewPager.setClipChildren(false);
                viewPager.setClickable(false);


                setupIndicator();
                updateIndicator(0);


                viewPager.registerOnPageChangeCallback(onPageChangeCallback);

                /*After setting the adapter use the timer */
                handler = new Handler();
                Update = new Runnable() {
                    public void run() {
                        if (currentPage == NUM_PAGES) {
                            currentPage = 0;
                        }
//                        int pos = currentPage % linearindecator.getChildCount();
                        updateIndicator(currentPage);
//                        updateIndicator(currentPage);
                        viewPager.setCurrentItem(currentPage++, true);

                    }
                };
                timer = new Timer(); // This will create a new Thread

                timer.schedule(new TimerTask() { // task to be scheduled
                    @Override
                    public void run() {
                        handler.post(Update);
                    }
                }, DELAY_MS, PERIOD_MS);


            }

            x = 4;
            if (saved_data.get_user_id(Objects.requireNonNull(getContext())).equals("0") || saved_data.get_user_id(Objects.requireNonNull(getContext())) == null) {

                new Apicalls(getContext(), Home_Fragment.this).get_Best_Selling("0");

            } else {

                String user_id = saved_data.get_user_id(getContext());
                new Apicalls(getContext(), Home_Fragment.this).get_Best_Selling(user_id);

            }


        } else if (x == 4) {
            bestSellingList.clear();

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

                bestSellingList.add(homeDetailsResponse);

            }

            rcybestselling.setHasFixedSize(true);
            rcybestselling.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.HORIZONTAL, false));
            rcybestselling.setAdapter(new Rcy_Best_Selling_Adapter(bestSellingList, getActivity()));

            x = 5;
            if (saved_data.get_user_id(Objects.requireNonNull(getContext())).equals("0") || saved_data.get_user_id(Objects.requireNonNull(getContext())) == null) {

                new Apicalls(getContext(), Home_Fragment.this).get_Recently_Added("0");

            } else {

                String user_id = saved_data.get_user_id(getContext());
                new Apicalls(getContext(), Home_Fragment.this).get_Recently_Added(user_id);

            }


        } else if (x == 5) {
            MainActivity.loading.setVisibility(View.GONE);
            recentlyAddedList.clear();
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

                recentlyAddedList.add(homeDetailsResponse);

            }

            rcyrecentadded.setHasFixedSize(true);
            rcyrecentadded.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.HORIZONTAL, false));
            rcyrecentadded.setAdapter(new Rcy_Recently_Added_Adapter(recentlyAddedList, getActivity()));


        }


    }

    @Override
    public void OnError(VolleyError error) {
        MainActivity.loading.setVisibility(View.GONE);
        Log.e("error", error.toString());


    }

    @Override
    public void onPause() {
        super.onPause();
        if (timer != null) {
            timer.cancel();
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        if (timer != null) {
            timer.cancel();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();

    }

    private void setupIndicator() {

        if (getContext() != null) {
            try {
                ImageView[] dots = new ImageView[models.size()];
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(5, 0, 5, 0);
                params.gravity = Gravity.CENTER;
                for (int i = 0; i < dots.length; i++) {
                    dots[i] = new ImageView(getContext());
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.indicator_unselect));
                    dots[i].setLayoutParams(params);
                    linearindecator.addView(dots[i]);
                }
            } catch (ActivityNotFoundException e) {

//                    Toasty.error(Objects.requireNonNull(getContext()), "catch", Toast.LENGTH_LONG).show();
                Log.e("e", e.toString());

            }
        }
    }

    private void updateIndicator(int idx) {

        if (getContext() != null) {

            try {
                int childCount = linearindecator.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    ImageView imageView = (ImageView) linearindecator.getChildAt(i);
                    if (i == idx) {
                        imageView.setImageDrawable(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.indicator_select));
                    } else {
                        imageView.setImageDrawable(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.indicator_unselect));
                    }
                }
            } catch (ActivityNotFoundException e) {

//                    Toasty.error(Objects.requireNonNull(getContext()), "catch", Toast.LENGTH_LONG).show();
                Log.e("e", e.toString());

            }
        }
    }
}
