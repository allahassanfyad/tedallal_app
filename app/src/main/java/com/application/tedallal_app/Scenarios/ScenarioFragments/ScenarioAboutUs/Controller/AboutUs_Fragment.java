package com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioAboutUs.Controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.application.tedallal_app.R;
import com.application.tedallal_app.Scenarios.ScenarioMain.Pattrens.IFOnBackPressed;

public class AboutUs_Fragment extends Fragment implements IFOnBackPressed {

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.aboutus_fragment, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public boolean onBackPressed() {

//        FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
//        fr.replace(R.id.fragment_container, new Home_Fragment());
//        fr.addToBackStack(null);
//        fr.commit();
//        MainActivity.navigation.setSelectedItemId(R.id.home_nav);
        return true;
    }

}
