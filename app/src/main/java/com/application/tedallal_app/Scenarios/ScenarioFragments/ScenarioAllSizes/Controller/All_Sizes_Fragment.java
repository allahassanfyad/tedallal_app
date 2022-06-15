package com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioAllSizes.Controller;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.application.tedallal_app.R;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioAddCart.Controller.Add_Cart_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioMain.Pattrens.IFOnBackPressed;

import java.util.Objects;

import es.dmoral.toasty.Toasty;

public class All_Sizes_Fragment extends Fragment implements IFOnBackPressed {


    private View view;
    TextView txtopenvideo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.all_sizes_fragment, container, false);

        txtopenvideo = view.findViewById(R.id.txtOpenVideo);

        txtopenvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Toasty.error(Objects.requireNonNull(getContext()), "none", Toast.LENGTH_LONG).show();
                try {

                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=yaxaOXjVhlE"));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setPackage("com.google.android.youtube");
                    startActivity(intent);
//                    Toasty.error(Objects.requireNonNull(getContext()), "try", Toast.LENGTH_LONG).show();


                } catch (ActivityNotFoundException e) {

//                    Toasty.error(Objects.requireNonNull(getContext()), "catch", Toast.LENGTH_LONG).show();
                    Log.e("e", e.toString());
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=yaxaOXjVhlE")));

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

        FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
        fr.replace(R.id.fragment_container, new Add_Cart_Fragment());
        fr.addToBackStack(null);
        fr.commit();
//        MainActivity.navigation.setSelectedItemId(R.id.home_nav);
        return true;
    }


}
