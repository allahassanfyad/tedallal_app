package com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioCart.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.application.tedallal_app.R;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioAllAddress.Controller.All_Address_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioCart.Model.Rcy_Cart_Model;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioCart.Pattrens.Rcy_Cart_Adapter;
import com.application.tedallal_app.Scenarios.ScenarioHome.Controller.SignIn;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.MainActivity;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Controller.Home_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioProfileFragment.Controller.UI_Profile_Fragment.ScenarioDeliveryAddress.Controller.Profile_Delivery_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioMain.Pattrens.IFOnBackPressed;
import com.application.tedallal_app.Utils.Realm_adapter;
import com.application.tedallal_app.local_data.saved_data;


import java.util.ArrayList;
import java.util.Objects;

import io.realm.Realm;

public class Cart_Fragment extends Fragment implements IFOnBackPressed {

    private View view;
    Realm realm;
    ArrayList<Rcy_Cart_Model> cartModels = new ArrayList<>();
    Realm_adapter adapter;
    RecyclerView rcyCart;
    Button btncontinueorder, btncontinueShopping;
    TextView txtnodata;
    LinearLayout linearcart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.cart_fragment, container, false);
        Realm.init(Objects.requireNonNull(getContext()));
        realm = Realm.getDefaultInstance();

        adapter = new Realm_adapter(realm);
        cartModels = adapter.retrieve();

        rcyCart = view.findViewById(R.id.rcyCart);
        btncontinueorder = view.findViewById(R.id.btnContinueOrder);
        btncontinueShopping = view.findViewById(R.id.btnContinueShoppping);
        txtnodata = view.findViewById(R.id.txtNoData);
        linearcart = view.findViewById(R.id.linearCart);


        btncontinueorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (saved_data.get_user_id(Objects.requireNonNull(getContext())).equals("0")||(saved_data.get_user_id(getContext()).equals("0"))){


                    Objects.requireNonNull(getActivity()).finish();
                    Objects.requireNonNull(getActivity()).startActivity(new Intent(getActivity(), SignIn.class));


                }else {
                    Profile_Delivery_Fragment.address = 0;
                    FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                    fr.replace(R.id.fragment_container, new All_Address_Fragment());
                    fr.addToBackStack(null);
                    fr.commit();

                }
            }
        });


        btncontinueShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new Home_Fragment());
                fr.addToBackStack(null);
                fr.commit();
                MainActivity.navigation.setSelectedItemId(R.id.home_nav);


            }
        });


        if (cartModels.size() == 0) {

            linearcart.setVisibility(View.GONE);
            txtnodata.setVisibility(View.VISIBLE);

        } else {

            rcyCart.setHasFixedSize(true);
            rcyCart.setLayoutManager(new LinearLayoutManager(getContext()));
            Rcy_Cart_Adapter adabter = new Rcy_Cart_Adapter(cartModels, getContext());
            rcyCart.setAdapter(adabter);
        }


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


}
