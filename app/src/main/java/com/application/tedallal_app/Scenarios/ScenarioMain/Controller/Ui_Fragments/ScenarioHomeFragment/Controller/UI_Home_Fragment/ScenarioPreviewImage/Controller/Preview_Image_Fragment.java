package com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Controller.UI_Home_Fragment.ScenarioPreviewImage.Controller;

import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.application.tedallal_app.R;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioAddCart.Controller.Add_Cart_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Controller.Home_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Pattrens.Rcy_Slider_Home_adapter;
import com.application.tedallal_app.Scenarios.ScenarioMain.Pattrens.IFOnBackPressed;
import com.application.tedallal_app.Utils.TinyDB;
import com.bumptech.glide.Glide;

import java.util.Objects;

public class Preview_Image_Fragment extends Fragment implements IFOnBackPressed {


    private View view;
    TinyDB tinyDB;
    ImageView imgpreview;
    private ScaleGestureDetector scaleGestureDetector;
    private float mScaleFactor = 1.0f;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.preview_image_fragment, container, false);

        tinyDB = new TinyDB(getContext());

        imgpreview = view.findViewById(R.id.imgPreview);

        Glide.with(Objects.requireNonNull(getContext()))
                .load(tinyDB.getString("imageSelected"))
                .placeholder(R.drawable.gray_img)
                .into(imgpreview);

//        scaleGestureDetector = new ScaleGestureDetector(getContext(), new ScaleListener());

        return view;
    }

//    @Override
//    public boolean onTouch(View v, MotionEvent event) {
//
//        scaleGestureDetector.onTouchEvent(event);
//
//
//        return true;
//    }
//
//
//    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
//        @Override
//        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
//            mScaleFactor *= scaleGestureDetector.getScaleFactor();
//            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 10.0f));
//            imgpreview.setScaleX(mScaleFactor);
//            imgpreview.setScaleY(mScaleFactor);
//            return true;
//        }
//    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public boolean onBackPressed() {

        if (Rcy_Slider_Home_adapter.sliderBack == 1) {
            Rcy_Slider_Home_adapter.sliderBack = 0;

            FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
            fr.replace(R.id.fragment_container, new Home_Fragment());
            fr.addToBackStack(null);
            fr.commit();

        } else {

            FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
            fr.replace(R.id.fragment_container, new Add_Cart_Fragment());
            fr.addToBackStack(null);
            fr.commit();

        }


        return true;
    }


}
