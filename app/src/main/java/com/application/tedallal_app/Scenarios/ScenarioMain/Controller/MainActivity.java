package com.application.tedallal_app.Scenarios.ScenarioMain.Controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.application.tedallal_app.R;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioAboutUs.Controller.AboutUs_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioCart.Controller.Cart_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioCart.Model.Rcy_Cart_Model;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioOrders.Controller.Order_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioTermsAndConditions.Controller.Terms_And_Conditions_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioHome.Controller.Register;
import com.application.tedallal_app.Scenarios.ScenarioHome.Controller.SignIn;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioFavouriteFragment.Controller.Favourite_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHelpFragment.Controller.Help_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Controller.Home_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioProfileFragment.Controller.Profile_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioSearchFragment.Controller.Search_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioMain.Pattrens.IFOnBackPressed;
import com.application.tedallal_app.Utils.Realm_adapter;
import com.application.tedallal_app.Utils.TinyDB;
import com.application.tedallal_app.local_data.saved_data;
import com.application.tedallal_app.local_data.send_data;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

import es.dmoral.toasty.Toasty;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    public static BottomNavigationView navigation;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    public static DrawerLayout drawerLayout;
    TinyDB tinyDB;
    public static LinearLayout loading;
    public static int login = 0;
    Realm realm;
    public static TextView txtcartcounter;
    ArrayList<Rcy_Cart_Model> cartModels = new ArrayList<>();
    RelativeLayout linearCartImage;
    public static Button txtlogout;
    LinearLayout linearWhatsApp, linearInstgram, linearSnap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Realm.init(this);
        Realm_adapter realm_adapter = new Realm_adapter(realm);
        cartModels = realm_adapter.retrieve();


        Log.e("cartModel", cartModels.toString());


        tinyDB = new TinyDB(this);
        loading = findViewById(R.id.loading);
        navigation = findViewById(R.id.navigation);
        fragmentManager = getSupportFragmentManager();
        drawerLayout = findViewById(R.id.drawer);
        txtcartcounter = findViewById(R.id.txtCartCount);
        linearCartImage = findViewById(R.id.linearCartImage);
        txtlogout = findViewById(R.id.btnLogout);
        linearWhatsApp = findViewById(R.id.linearWhatsApp);
        linearInstgram = findViewById(R.id.linearInstegram);
//        linearTwitter = findViewById(R.id.linearTwitter);
        linearSnap = findViewById(R.id.linearSnapChat);


        linearWhatsApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = "+96565173953";


                String url = "https://api.whatsapp.com/send?phone=" + phone;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);


            }
        });


//        linearTwitter.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                try {
//                    Intent intent = new Intent(Intent.ACTION_VIEW,
//                            Uri.parse("twitter://alshal_abaya"));
//                    startActivity(intent);
//                } catch (Exception e) {
//                    startActivity(new Intent(Intent.ACTION_VIEW,
//                            Uri.parse("https://twitter.com/alshal_abaya")));
//                }
//            }
//        });


        linearInstgram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Uri uri = Uri.parse("http://instagram.com/_u/tadallalkwt");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                likeIng.setPackage("com.instagram.android");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://instagram.com/tadallalkwt")));
                }


            }
        });

        linearSnap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                "https://snapchat.com/add/username"

                String snapchatId = "tadallal.app";

                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://snapchat.com/add/" + snapchatId));
                    intent.setPackage("com.snapchat.android");
                    startActivity(intent);
                } catch (Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://snapchat.com/add/" + snapchatId)));
                }

            }
        });


        if (saved_data.get_switch_checked(MainActivity.this) == true) {

            txtlogout.setText(getString(R.string.logout));

        } else {

            txtlogout.setText(getString(R.string.login));

        }

        if (cartModels.size() == 0) {

            txtcartcounter.setVisibility(View.GONE);


        } else {

//            txtcartcounter.setVisibility(View.VISIBLE);
//            txtcartcounter.setText("" + cartModels.size());
//

            setcartcount();
        }


        linearCartImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cart_Fragment cart_fragment = new Cart_Fragment();
                loadFragment(cart_fragment);

            }
        });


        Home_Fragment home_fragment = new Home_Fragment();
        loadFragment(home_fragment);


        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home_nav:

                        Home_Fragment home_fragment = new Home_Fragment();
                        loadFragment(home_fragment);
//                        FragmentTransaction fr = MainActivity.this.getSupportFragmentManager().beginTransaction();
//                        fr.replace(R.id.fragment_container, new Home_Fragment(), "User_Fragment");
//                        fr.addToBackStack(null);
//                        fr.commit();

                        return true;


                    case R.id.profile:

                        if (saved_data.get_switch_checked(MainActivity.this) == false) {

                            finish();

                            startActivity(new Intent(MainActivity.this, SignIn.class));

                        } else {

                            Profile_Fragment profile_fragment = new Profile_Fragment();
                            loadFragment(profile_fragment);
                        }
                        return true;


                    case R.id.favourite:

                        if (saved_data.get_switch_checked(MainActivity.this) == false) {

                            finish();
                            startActivity(new Intent(MainActivity.this, SignIn.class));

                        } else {

                            Favourite_Fragment favourite_fragment = new Favourite_Fragment();
                            loadFragment(favourite_fragment);
                        }

                        return true;


                    case R.id.search:

                        Search_Fragment search_fragment = new Search_Fragment();
                        loadFragment(search_fragment);

                        return true;


                    case R.id.help:

                        Help_Fragment help_fragment = new Help_Fragment();
                        loadFragment(help_fragment);

                        return true;


                    default:
                        return true;
                }
            }
        });
        Log.e("langMain", Locale.getDefault().getLanguage());

        // OPEN AND CLOSE DRAWER
        menu();

        //ON CLICK NAVIGATION
        onClick_navigation();

    }


    void onClick_navigation() {

        //Previous Washes
        LinearLayout linearhome = findViewById(R.id.linearHome);
        linearhome.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {

                if (drawerLayout.isDrawerOpen(Gravity.START)) {
                    drawerLayout.closeDrawer(Gravity.START);

                } else {
                    drawerLayout.openDrawer(Gravity.START);
                }

                Home_Fragment home_fragment = new Home_Fragment();
                loadFragment(home_fragment);

            }
        });


        //Appointment

        LinearLayout linearorders = findViewById(R.id.linearOrders);
        linearorders.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {

                if (drawerLayout.isDrawerOpen(Gravity.START)) {
                    drawerLayout.closeDrawer(Gravity.START);

                } else {
                    drawerLayout.openDrawer(Gravity.START);
                }


                if (saved_data.get_switch_checked(MainActivity.this) == false) {

                    finish();
                    startActivity(new Intent(MainActivity.this, SignIn.class));

                } else {

                    Order_Fragment order_fragment = new Order_Fragment();
                    loadFragment(order_fragment);
                }


            }
        });
//
//
        //Complaint

        LinearLayout linearFavourite = findViewById(R.id.linearFavourite);
        linearFavourite.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {

                if (drawerLayout.isDrawerOpen(Gravity.START)) {
                    drawerLayout.closeDrawer(Gravity.START);

                } else {
                    drawerLayout.openDrawer(Gravity.START);
                }


                if (saved_data.get_switch_checked(MainActivity.this) == false) {

                    finish();
                    startActivity(new Intent(MainActivity.this, SignIn.class));

                } else {

                    Favourite_Fragment favourite_fragment = new Favourite_Fragment();
                    loadFragment(favourite_fragment);
                }


            }
        });
//

        //Car Information

        LinearLayout linearRegister = findViewById(R.id.linearRegister);
        linearRegister.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {

                if (drawerLayout.isDrawerOpen(Gravity.START)) {
                    drawerLayout.closeDrawer(Gravity.START);

                } else {
                    drawerLayout.openDrawer(Gravity.START);
                }

//                Register_Fragment register_fragment = new Register_Fragment();
//                loadFragment(register_fragment);
                startActivity(new Intent(MainActivity.this, Register.class));

            }
        });
//

        //Car Information

        LinearLayout linearaboutus = findViewById(R.id.linearAboutUs);
        linearaboutus.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {

                if (drawerLayout.isDrawerOpen(Gravity.START)) {
                    drawerLayout.closeDrawer(Gravity.START);

                } else {
                    drawerLayout.openDrawer(Gravity.START);
                }

                AboutUs_Fragment aboutUs_fragment = new AboutUs_Fragment();
                loadFragment(aboutUs_fragment);

            }
        });
//
//
        //GO TO Share
        LinearLayout linearTerms = findViewById(R.id.linearTermsAndConditions);
        linearTerms.setOnClickListener(new View.OnClickListener() {
            //            @SuppressLint("WrongConstant")
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerOpen(Gravity.START)) {
                    drawerLayout.closeDrawer(Gravity.START);

                } else {
                    drawerLayout.openDrawer(Gravity.START);
                }

                Terms_And_Conditions_Fragment terms_and_conditions_fragment = new Terms_And_Conditions_Fragment();
                loadFragment(terms_and_conditions_fragment);


            }
        });
//

        //GO TO LOGOUT
//        LinearLayout linearlogout = findViewById(R.id.linearLogout);
        txtlogout.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {

                if (drawerLayout.isDrawerOpen(Gravity.START)) {
                    drawerLayout.closeDrawer(Gravity.START);

                } else {
                    drawerLayout.openDrawer(Gravity.START);
                }

                if (saved_data.get_switch_checked(MainActivity.this) == false) {

                    finish();
                    startActivity(new Intent(MainActivity.this, SignIn.class));


                } else {


                    AlertDialog.Builder alertDialogBulder = new AlertDialog.Builder(MainActivity.this);

                    alertDialogBulder.setTitle(R.string.logout);

                    alertDialogBulder
                            .setMessage(R.string.are_you_sure_to_logout)
                            .setCancelable(false)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    txtlogout.setText(R.string.login);
                                    send_data.SET_USER_ID(MainActivity.this, "0");
                                    send_data.switch_checked(MainActivity.this, false);

                                    Home_Fragment home_fragment = new Home_Fragment();
                                    loadFragment(home_fragment);

                                    Toasty.success(Objects.requireNonNull(MainActivity.this), R.string.logout_successfully, Toast.LENGTH_LONG).show();


                                }
                            }).setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.cancel();
                        }
                    });

                    //create it
                    AlertDialog alertDialog = alertDialogBulder.create();
                    // show it
                    alertDialog.show();

                }
            }

        });

        //GO TO Setting
        LinearLayout linearlanguge = findViewById(R.id.linearChangeLanguage);
        linearlanguge.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerOpen(Gravity.START)) {
                    drawerLayout.closeDrawer(Gravity.START);

                } else {
                    drawerLayout.openDrawer(Gravity.START);
                }

////                Login_Fragment login_fragment = new Login_Fragment();
////                loadFragment(login_fragment);
//                startActivity(new Intent(MainActivity.this, SignIn.class));

                Change_Language_Popup change_language_popup = new Change_Language_Popup();
                change_language_popup.dialog(MainActivity.this, R.layout.change_language_popup, .90);


            }
        });

//
//        //GO TO PERSONAL INFO
//        LinearLayout linearpersonal = findViewById(R.id.linearPersonalInfo);
//        linearpersonal.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("WrongConstant")
//            @Override
//            public void onClick(View view) {
//                if (drawerLayout.isDrawerOpen(Gravity.END)) {
//                    drawerLayout.closeDrawer(Gravity.END);
//
//                } else {
//                    drawerLayout.openDrawer(Gravity.END);
//                }
//
//
//                startActivity(new Intent(MainActivity.this, Personal_Info.class));
////                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
////                sharingIntent.setType("text/plain");
////                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,"http://www.tamraat.com" );
////                startActivity(Intent.createChooser(sharingIntent, "Share via"));
//
//            }
//        });
//
//
//        //GO TO SAVED PLACES
//        LinearLayout linearsavedplaces = findViewById(R.id.linearSavedPlaces);
//        linearsavedplaces.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("WrongConstant")
//            @Override
//            public void onClick(View view) {
//                if (drawerLayout.isDrawerOpen(Gravity.END)) {
//                    drawerLayout.closeDrawer(Gravity.END);
//
//                } else {
//                    drawerLayout.openDrawer(Gravity.END);
//                }
//
//                startActivity(new Intent(MainActivity.this, Address.class));
//
////                ParserTask parserTask = new ParserTask();
////                parserTask.cancel(true);
////                PlacesTask placesTask = new PlacesTask();
////                placesTask.cancel(true);
////                startActivity(new Intent(MainActivity.this, Cart.class));
////                finish();
//
//            }
//        });
//
//        //Go To Information PREVIOUS ORDERS
//
//        LinearLayout linearpreviousorder = findViewById(R.id.linearPreviousOrder);
//        linearpreviousorder.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("WrongConstant")
//            @Override
//            public void onClick(View view) {
//                if (drawerLayout.isDrawerOpen(Gravity.END)) {
//                    drawerLayout.closeDrawer(Gravity.END);
//
//                } else {
//                    drawerLayout.openDrawer(Gravity.END);
//                }
//
//                startActivity(new Intent(MainActivity.this, Previous_Orders.class));
//
////                ParserTask parserTask = new ParserTask();
////                parserTask.cancel(true);
////                PlacesTask placesTask = new PlacesTask();
////                placesTask.cancel(true);
////                startActivity(new Intent(MainActivity.this, Previous_Orders.class));
////                finish();
//
//            }
//        });
//
//
//        //GO TO VOUCHERS
//        LinearLayout linearvouchers = findViewById(R.id.linearVouchers);
//        linearvouchers.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("WrongConstant")
//            @Override
//            public void onClick(View view) {
//                if (drawerLayout.isDrawerOpen(Gravity.END)) {
//                    drawerLayout.closeDrawer(Gravity.END);
//
//                } else {
//                    drawerLayout.openDrawer(Gravity.END);
//                }
//
//                startActivity(new Intent(MainActivity.this, Vouchers.class));
//
////                ParserTask parserTask = new ParserTask();
////                parserTask.cancel(true);
////                PlacesTask placesTask = new PlacesTask();
////                placesTask.cancel(true);
////                startActivity(new Intent(MainActivity.this, Previous_Orders.class));
////                finish();
//
//            }
//        });


    }


    public void menu() {
        LinearLayout menu = findViewById(R.id.linearMenuImage);
        menu.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(Gravity.START)) {
                    drawerLayout.closeDrawer(Gravity.START);

                } else {
                    drawerLayout.openDrawer(Gravity.START);
                }
            }
        });
    }


    private void loadFragment(Fragment fragment) {

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }


    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (!(fragment instanceof IFOnBackPressed) || !((IFOnBackPressed) fragment).onBackPressed()) {
            super.onBackPressed();
        }
    }

    public void setcartcount() {

        Realm.init(this);
        Realm_adapter realm_adapter = new Realm_adapter(realm);
        cartModels = realm_adapter.retrieve();
        int productNumber = 0;
        if (cartModels.size() == 0) {

            txtcartcounter.setVisibility(View.GONE);


        } else {

            txtcartcounter.setVisibility(View.VISIBLE);
            for (int i = 0; i < cartModels.size(); i++) {

                int number = Integer.parseInt(cartModels.get(i).getTxtnumberchoose());
                productNumber = productNumber + number;

            }
            Log.e("number", String.valueOf(productNumber));
            txtcartcounter.setText("" + productNumber);

        }

    }

    public static void changeLang(Context context, String lang) {
        Locale myLocale = new Locale(lang);
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = myLocale;
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());

        send_data.SET_LANGUAGE_NUM(context, lang);

        context.startActivity(new Intent(context, MainActivity.class));
    }

    public static void changeLang222(Context context, String lang) {
        Locale myLocale = new Locale(lang);
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = myLocale;
//        send_data.SET_LANGUAGE_NUM(Splash.this, config.locale);
        Log.e("languageeeee", String.valueOf(config.locale));
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
    }

}