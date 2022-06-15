package com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioProfileFragment.Controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.addisonelliott.segmentedbutton.SegmentedButton;
import com.addisonelliott.segmentedbutton.SegmentedButtonGroup;
import com.application.tedallal_app.R;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.MainActivity;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Controller.Home_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioProfileFragment.Controller.UI_Profile_Fragment.ScenarioDeliveryAddress.Controller.Profile_Delivery_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioProfileFragment.Controller.UI_Profile_Fragment.ScenarioProfileInformation.Controller.Profile_Info_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioProfileFragment.Controller.UI_Profile_Fragment.ScenarioProfileOrders.Controller.Profile_Orders_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioMain.Pattrens.IFOnBackPressed;

import java.util.Objects;

public class Profile_Fragment extends Fragment implements IFOnBackPressed {

    private View view;


    SegmentedButtonGroup segmentedButtonGroup;
    SegmentedButton segmentedButton1, segmentedButton2, segmentedButton3, segmentedButton4;
    public static TextView txtprofiename, txtprofilecountryname;
    public static String back = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.profile_fragment, container, false);


        segmentedButtonGroup = view.findViewById(R.id.buttonGroupSegmented);


        segmentedButton2 = view.findViewById(R.id.segmented2);
        segmentedButton3 = view.findViewById(R.id.segmented3);
        segmentedButton4 = view.findViewById(R.id.segmented4);

        txtprofiename = view.findViewById(R.id.txtProfileName);
        txtprofilecountryname = view.findViewById(R.id.txtProfileCountry);


        FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
        fr.replace(R.id.fragment_container2, new Profile_Info_Fragment());
        fr.addToBackStack(null);
        fr.commit();


        segmentedButtonGroup.setOnPositionChangedListener(new SegmentedButtonGroup.OnPositionChangedListener() {
            @Override
            public void onPositionChanged(int position) {

                if (position == 0) {


                    FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                    fr.replace(R.id.fragment_container2, new Profile_Delivery_Fragment());
                    fr.addToBackStack(null);
                    fr.commit();

//                    Toast.makeText(getContext(), "000000000000000000", Toast.LENGTH_SHORT).show();

                } else if (position == 1) {

                    FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                    fr.replace(R.id.fragment_container2, new Profile_Orders_Fragment());
                    fr.addToBackStack(null);
                    fr.commit();

//                    Toast.makeText(getContext(), "1111111111111111", Toast.LENGTH_SHORT).show();

                } else if (position == 2) {

                    FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                    fr.replace(R.id.fragment_container2, new Profile_Info_Fragment());
                    fr.addToBackStack(null);
                    fr.commit();


//                    Toast.makeText(getContext(), "22222222222222222", Toast.LENGTH_SHORT).show();

                }

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

        if (back.equals("add_address")) {

            FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
            fr.replace(R.id.fragment_container2, new Profile_Delivery_Fragment());
            fr.addToBackStack(null);
            fr.commit();

        } else if (back.equals("Order_Details")) {

            FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
            fr.replace(R.id.fragment_container2, new Profile_Orders_Fragment());
            fr.addToBackStack(null);
            fr.commit();



        } else if (back.equals("back")) {

            FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
            fr.replace(R.id.fragment_container, new Home_Fragment());
            fr.addToBackStack(null);
            fr.commit();
            MainActivity.navigation.setSelectedItemId(R.id.home_nav);


        }

        return true;

    }

}
