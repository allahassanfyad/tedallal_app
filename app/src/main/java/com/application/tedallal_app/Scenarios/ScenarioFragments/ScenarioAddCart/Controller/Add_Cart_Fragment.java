package com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioAddCart.Controller;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.android.volley.VolleyError;

import com.application.tedallal_app.NetworkLayer.Apicalls;
import com.application.tedallal_app.NetworkLayer.NetworkInterface;
import com.application.tedallal_app.NetworkLayer.ResponseModel;
import com.application.tedallal_app.R;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioAddCart.Model.ModelProductDetailsResponse;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioAddCart.Model.Model_Product_Color;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioAddCart.Model.Model_Product_Sizes;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioAddCart.Model.mode_introduction;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioAddCart.Pattrens.Rcy_adapter;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioAllSizes.Controller.All_Sizes_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioCart.Controller.Cart_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioCart.Model.Rcy_Cart_Model;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioOrders.Pattrens.Rcy_Order_Product_Adapter;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.MainActivity;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioFavouriteFragment.Controller.Favourite_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Controller.Home_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Controller.UI_Home_Fragment.ScenarioHomeDetails.Controller.Home_Details_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Pattrens.Rcy_Best_Selling_Adapter;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioProfileFragment.Controller.UI_Profile_Fragment.ScenarioProfileOrders.Pattrens.Rcy_Profile_Order_Product_Adapter;
import com.application.tedallal_app.Scenarios.ScenarioMain.Pattrens.IFOnBackPressed;
import com.application.tedallal_app.Utils.Realm_adapter;
import com.application.tedallal_app.Utils.TinyDB;
import com.application.tedallal_app.local_data.saved_data;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import es.dmoral.toasty.Toasty;
import io.realm.Realm;

public class Add_Cart_Fragment extends Fragment implements IFOnBackPressed, NetworkInterface {


    private View view;
    ViewPager2 viewPager;
    List<mode_introduction> models = new ArrayList<>();
    Rcy_adapter adapter;
    private LinearLayout linearindecator;
    TinyDB tinyDB;
    List<String> images = new ArrayList<>();
    TextView txttitle, txtprice, txtdesc,txtpriceDis;
    Button btnaddtocart;
    Realm realm;
    EditText editquntity;

    ArrayAdapter<String> sizeAdapter;
    Spinner spinnersize;
    List<String> sizeList = new ArrayList<>();
    String size = "";

    ArrayAdapter<String> colorAdapter;
    Spinner spinnerColor;
    List<String> colorList = new ArrayList<>();
    String color = "";

    ArrayAdapter<String> quntityAdapter;
    Spinner spinnerquntity;
    List<String> quntityList = new ArrayList<>();
    String quntty = "";

    String Imagecart;
    String title, Id, price;


    ModelProductDetailsResponse[] productDetailsResponses;
    Model_Product_Sizes[] product_sizes;
    Model_Product_Color[] product_colors;
    List<ModelProductDetailsResponse> productDetailsList = new ArrayList<>();

    int currentPage = 0;
    int NUM_PAGES = 0;
    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000; // time in milliseconds between successive task executions.
    ImageView imgFav;
    String productId;
    int x = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.add_cart_fragment, container, false);

        tinyDB = new TinyDB(getContext());
        linearindecator = view.findViewById(R.id.linearIndicator);

//        title = tinyDB.getString("ProductTitle");
//        Id = tinyDB.getString("ProductId");
//        price = tinyDB.getString("ProductPrice");

        realm = Realm.getDefaultInstance();

        txttitle = view.findViewById(R.id.txtTitle);
        txtdesc = view.findViewById(R.id.txtdesc);
        txtpriceDis = view.findViewById(R.id.txtPriceDiscount);
        txtprice = view.findViewById(R.id.txtPrice);



        spinnerColor = view.findViewById(R.id.spinnerColor);
//        txtmaterial = view.findViewById(R.id.txtMaterial);
//        txtmodel = view.findViewById(R.id.txtModel);
//        txtstyle = view.findViewById(R.id.txtStyle);
//        txttypearm = view.findViewById(R.id.txtTypeArm);

        btnaddtocart = view.findViewById(R.id.btnAddToCart);
        spinnersize = (Spinner) view.findViewById(R.id.spinnerSize);
        spinnerquntity = (Spinner) view.findViewById(R.id.spinnerQuntity);
//        txtshowtable = view.findViewById(R.id.txtShowTable);
//        txtcustomize = view.findViewById(R.id.txtCustomize);
        imgFav = view.findViewById(R.id.imgFav);


        quntityList.add("1");
        quntityList.add("2");
        quntityList.add("3");
        quntityList.add("4");
        quntityList.add("5");
        quntityList.add("6");


        quntityAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_text1, quntityList);
        quntityAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);
        spinnerquntity.setAdapter(quntityAdapter);

//        spinnercountry.setSelection(countryAdapter.getPosition("مصر"));

        spinnerquntity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                quntty = String.valueOf(quntityList.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.e("nothingSelected", "nothing");
            }
        });

        MainActivity.loading.setVisibility(View.VISIBLE);
        String product_id = tinyDB.getString("ProductId");
        x = 1;
        new Apicalls(getContext(), Add_Cart_Fragment.this).get_single_Product_Details(product_id);

//        txtshowtable.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
//                fr.replace(R.id.fragment_container, new All_Sizes_Fragment());
//                fr.addToBackStack(null);
//                fr.commit();
//
//            }
//        });

//        txtcustomize.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (quntty.equals("")) {
//
//                    Toasty.error(Objects.requireNonNull(getContext()), getString(R.string.please_enter_your_quantity), Toast.LENGTH_LONG).show();
//
//                } else {
//
//                    tinyDB = new TinyDB(getContext());
//
//                    tinyDB.putString("ProductQuntaty", quntty);
//                    tinyDB.putString("ProductSize", size);
//
//                    FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
//                    fr.replace(R.id.fragment_container, new Customize_Order_Fragment());
//                    fr.addToBackStack(null);
//                    fr.commit();
//                }
//
//            }
//        });

        btnaddtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (quntty.equals("")) {


                    Toasty.error(Objects.requireNonNull(getContext()), getString(R.string.please_enter_your_quantity), Toast.LENGTH_LONG).show();

                } else {


                    Rcy_Cart_Model c = new Rcy_Cart_Model();


                    c.setProductid(Id);
                    c.setTxttitle(title);
                    c.setTxtprice(price);
                    c.setTxtnumberchoose(quntty);
                    c.setImghome(Imagecart);
                    c.setTxtsize(size);

                    Realm_adapter adapter = new Realm_adapter(realm);
                    adapter.save(c);

                    Toasty.success(Objects.requireNonNull(getContext()), R.string.your_request_addeed_to_cart_successfully, Toast.LENGTH_LONG).show();

                    Rcy_Best_Selling_Adapter.getback = 0;
                    Favourite_Fragment.favBack = 0;

                    FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                    fr.replace(R.id.fragment_container, new Cart_Fragment());
                    fr.addToBackStack(null);
                    fr.commit();
                    MainActivity mainActivity1 = new MainActivity();
                    mainActivity1.setcartcount();

                }


            }
        });

        if (tinyDB.getBoolean("isfav") == true) {

            imgFav.setImageResource(R.drawable.heart_selected_png);

        } else {

            imgFav.setImageResource(R.drawable.heart_unselected_png);

        }

        imgFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x = 2;
                MainActivity.loading.setVisibility(View.VISIBLE);
                new Apicalls(getContext(), Add_Cart_Fragment.this).insert_Fav(saved_data.get_user_id(Objects.requireNonNull(getContext())), product_id);
            }
        });


//
//        images = tinyDB.getListString("listImage");
//
////        models.add(new mode_introduction(R.drawable.image1));
////        models.add(new mode_introduction(R.drawable.image2));
////        models.add(new mode_introduction(R.drawable.image1));
//
//        for (int i = 0; i < images.size(); i++) {
//
//            mode_introduction mode_introduction = new mode_introduction();
//
//            mode_introduction.setImghome(images.get(i));
//            models.add(mode_introduction);
//
//        }
//        Log.e("size", String.valueOf(models.size()));
//
//

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
            int pos = position % linearindecator.getChildCount();
            updateIndicator(pos);


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

        if (Favourite_Fragment.favBack == 1) {
            Favourite_Fragment.favBack = 0;

            FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
            fr.replace(R.id.fragment_container, new Favourite_Fragment());
            fr.addToBackStack(null);
            fr.commit();

        } else if (Rcy_Best_Selling_Adapter.getback == 1) {

            Rcy_Best_Selling_Adapter.getback = 0;

            FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
            fr.replace(R.id.fragment_container, new Home_Fragment());
            fr.addToBackStack(null);
            fr.commit();

        } else if (Rcy_Profile_Order_Product_Adapter.profile_order_back == 1) {

            Rcy_Profile_Order_Product_Adapter.profile_order_back = 0;

            FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
            fr.replace(R.id.fragment_container, new Home_Fragment());
            fr.addToBackStack(null);
            fr.commit();

        } else if (Rcy_Order_Product_Adapter.order_back == 1) {

            Rcy_Order_Product_Adapter.order_back = 0;

            FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
            fr.replace(R.id.fragment_container, new Home_Fragment());
            fr.addToBackStack(null);
            fr.commit();

        } else {

            FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
            fr.replace(R.id.fragment_container, new Home_Details_Fragment());
            fr.addToBackStack(null);
            fr.commit();
        }


        return true;
    }


    private void setupIndicator() {
        ImageView[] dots = new ImageView[models.size()];
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(8, 0, 8, 0);
        params.gravity = Gravity.CENTER;
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new ImageView(getContext());
            dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.indicator_unselect));
            dots[i].setLayoutParams(params);
            linearindecator.addView(dots[i]);
        }
    }

    private void updateIndicator(int idx) {
        int childCount = linearindecator.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) linearindecator.getChildAt(i);
            if (i == idx) {
                imageView.setImageDrawable(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.indicator_select));
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.indicator_unselect));
            }
        }
    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {

        Log.e("Response", model.getResponse());

        if (x == 1) {
            Gson gson = new Gson();
            productDetailsResponses = gson.fromJson(model.getResponse(), ModelProductDetailsResponse[].class);

            if (productDetailsResponses.length != 0) {


//        for (int i = 0; i < productDetailsResponses.length; i++) {
//
//            ModelProductDetailsResponse productDetailsResponse = new ModelProductDetailsResponse();
//
//            productDetailsResponse.setCategory(productDetailsResponses[i].getCategory());
//            productDetailsResponse.setDatee(productDetailsResponses[i].getDatee());
//            productDetailsResponse.setDes(productDetailsResponses[i].getDes());
//            productDetailsResponse.setDesEn(productDetailsResponses[i].getDesEn());
//            productDetailsResponse.setId(productDetailsResponses[i].getId());
//            productDetailsResponse.setImg1(productDetailsResponses[i].getImg1());
//            productDetailsResponse.setImg2(productDetailsResponses[i].getImg2());
//            productDetailsResponse.setImg3(productDetailsResponses[i].getImg3());
//            productDetailsResponse.setPrice(productDetailsResponses[i].getPrice());
//            productDetailsResponse.setPriceDiscount(productDetailsResponses[i].getPriceDiscount());
//            productDetailsResponse.setPriceDiscount(productDetailsResponses[i].getPriceDiscount());
//            productDetailsResponse.setRate(productDetailsResponses[i].getRate());
//            productDetailsResponse.setSlider(productDetailsResponses[i].getSlider());
//            productDetailsResponse.setTitle(productDetailsResponses[i].getTitle());
//            productDetailsResponse.setTitleEn(productDetailsResponses[i].getTitleEn());
//            productDetailsResponse.setNumberRate(productDetailsResponses[i].getNumberRate());
//            productDetailsResponse.setNumberStar(productDetailsResponses[i].getNumberStar());
//            productDetailsResponse.setTitleEn(productDetailsResponses[i].getTitleEn());
//
//
//            productDetailsList.add(productDetailsResponse);
//
//        }


                if (saved_data.get_lang_num(Objects.requireNonNull(getContext())).equals("ar")) {


                    txttitle.setText(productDetailsResponses[0].getTitle());
//                    txtcolor.setText(productDetailsResponses[0].getColor());
//                    txttypearm.setText(productDetailsResponses[0].getType());
//                    txtstyle.setText(productDetailsResponses[0].getStyles());
//                    txtmodel.setText(productDetailsResponses[0].getModel());
//                    txtmaterial.setText(productDetailsResponses[0].getMeteral());

                    title = productDetailsResponses[0].getTitle();
                    Id = String.valueOf(productDetailsResponses[0].getId());
                    price = productDetailsResponses[0].getPrice();
                    price = productDetailsResponses[0].getPrice();

                    tinyDB.putString("ProductTitle", productDetailsResponses[0].getTitle());
                    tinyDB.putString("ProductId", String.valueOf(productDetailsResponses[0].getId()));
                    tinyDB.putString("ProductPrice", productDetailsResponses[0].getPrice());

                } else if (saved_data.get_lang_num(getContext()).equals("en")) {


                    txttitle.setText(productDetailsResponses[0].getTitleEn());
//                    txtcolor.setText(productDetailsResponses[0].getColorEn());
//                    txttypearm.setText(productDetailsResponses[0].getTypeArmEn());
//                    txtstyle.setText(productDetailsResponses[0].getStylesEn());
//                    txtmodel.setText(productDetailsResponses[0].getModelEn());
//                    txtmaterial.setText(productDetailsResponses[0].getMeteralEn());

                    title = productDetailsResponses[0].getTitleEn();
                    Id = String.valueOf(productDetailsResponses[0].getId());
                    price = productDetailsResponses[0].getPrice();

                    tinyDB.putString("ProductTitle", productDetailsResponses[0].getTitleEn());
                    tinyDB.putString("ProductId", String.valueOf(productDetailsResponses[0].getId()));
                    tinyDB.putString("ProductPrice", productDetailsResponses[0].getPrice());

                }


                Imagecart = productDetailsResponses[0].getImg1();

                tinyDB.putString("ProductImage", productDetailsResponses[0].getImg1());


                txtprice.setText(productDetailsResponses[0].getPrice());
                txtpriceDis.setText(productDetailsResponses[0].getPriceDiscount());
                productId = String.valueOf(productDetailsResponses[0].getId());

                models.add(new mode_introduction(productDetailsResponses[0].getImg1()));
                models.add(new mode_introduction(productDetailsResponses[0].getImg2()));
                models.add(new mode_introduction(productDetailsResponses[0].getImg3()));
                models.add(new mode_introduction(productDetailsResponses[0].getSlider()));

                NUM_PAGES = 4;

                adapter = new Rcy_adapter(models, getContext());

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
                final Handler handler = new Handler();
                final Runnable Update = new Runnable() {
                    public void run() {
                        if (currentPage == NUM_PAGES) {
                            currentPage = 0;
                        }
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


            String product_id = tinyDB.getString("ProductId");
            x = 3;
            new Apicalls(getContext(), Add_Cart_Fragment.this).get_Product_Sizes(product_id);


        } else if (x == 2) {
            MainActivity.loading.setVisibility(View.GONE);
            if (tinyDB.getBoolean("isfav") == true) {

                Toasty.success(Objects.requireNonNull(getContext()), R.string.product_removed_fav_successfully, Toast.LENGTH_LONG).show();
                imgFav.setImageResource(R.drawable.heart_unselected_png);

            } else {

                Toasty.success(Objects.requireNonNull(getContext()), R.string.product_addd_to_favourite, Toast.LENGTH_LONG).show();
                imgFav.setImageResource(R.drawable.heart_selected_png);

            }

        } else if (x == 3) {
            Log.e("response11", model.getResponse().toString());

            Gson gson = new Gson();
            product_sizes = gson.fromJson(model.getResponse(), Model_Product_Sizes[].class);

            if (product_sizes != null) {

                for (int i = 0; i < product_sizes.length; i++) {
                    Model_Product_Sizes size = new Model_Product_Sizes();

                    size.setDatee(product_sizes[i].getDatee());
                    size.setId(product_sizes[i].getId());
                    size.setSizesAr(product_sizes[i].getSizesAr());
                    size.setSizesEn(product_sizes[i].getSizesEn());
                    size.setIdAdmin(product_sizes[i].getIdAdmin());
                    size.setIdProduct(product_sizes[i].getIdProduct());
//

                    Log.e("product_size", product_sizes[i].getSizesEn().toString());
                    sizeList.add(product_sizes[i].getSizesAr());

                }

                Log.e("sizess", sizeList.toString());
            }
            sizeAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_text1, sizeList);
            sizeAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);
            spinnersize.setAdapter(sizeAdapter);

//        spinnercountry.setSelection(countryAdapter.getPosition("مصر"));

            spinnersize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                    size = String.valueOf(sizeList.get(position));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    Log.e("nothingSelected", "nothing");
                }
            });


            String product_id = tinyDB.getString("ProductId");
            x = 4;
            new Apicalls(getContext(), Add_Cart_Fragment.this).get_Product_Color(product_id);

        } else if (x == 4) {
            MainActivity.loading.setVisibility(View.GONE);

            Log.e("response22", model.getResponse().toString());

            Gson gson = new Gson();
            product_colors = gson.fromJson(model.getResponse(), Model_Product_Color[].class);

            if (product_colors != null) {

                for (int i = 0; i < product_colors.length; i++) {
                    Model_Product_Color size = new Model_Product_Color();

                    size.setId(product_colors[i].getId());
                    size.setId_admin(product_colors[i].getId_admin());
                    size.setId_product(product_colors[i].getId_product());
                    size.setColor_code(product_colors[i].getColor_code());
                    size.setColor_en(product_colors[i].getColor_en());
                    size.setColor_ar(product_colors[i].getColor_ar());
                    size.setDatee(product_colors[i].getDatee());
//

                    Log.e("product_size", product_colors[i].getColor_en().toString());
                    colorList.add(product_colors[i].getColor_en());

                }

                Log.e("sizess", colorList.toString());
            }

            colorAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_text1, colorList);
            colorAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);
            spinnerColor.setAdapter(colorAdapter);

//        spinnercountry.setSelection(countryAdapter.getPosition("مصر"));

            spinnerColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                    color = String.valueOf(colorList.get(position));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    Log.e("nothingSelected", "nothing");
                }
            });

        }


    }

    @Override
    public void OnError(VolleyError error) {
        MainActivity.loading.setVisibility(View.GONE);
        Log.e("error", error.toString());


    }
}
