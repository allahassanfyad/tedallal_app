package com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHelpFragment.Controller;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.application.tedallal_app.R;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.MainActivity;
import com.application.tedallal_app.Scenarios.ScenarioMain.Pattrens.IFOnBackPressed;
import com.application.tedallal_app.Utils.TinyDB;
import com.application.tedallal_app.local_data.saved_data;

import java.util.Objects;

public class FAQ_WebView_Fragment extends Fragment implements IFOnBackPressed {

    private View view;
    TinyDB tinyDB;
    WebView webView;
    String language = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.faq_webview_fragment, container, false);

        tinyDB = new TinyDB(getContext());


        webView = view.findViewById(R.id.webview);


        if (saved_data.get_lang_num(Objects.requireNonNull(getContext())).equals("ar")) {

            webView.loadUrl("https://alshal.sa/app/faq-ar/");

        } else if (saved_data.get_lang_num(getContext()).equals("en")) {

            webView.loadUrl("https://alshal.sa/app/faq-en/");

        }

        language = saved_data.get_lang_num(Objects.requireNonNull(getContext()));
        MainActivity.changeLang222(getContext(), language);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public boolean onBackPressed() {

        FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
        fr.replace(R.id.fragment_container, new Help_Fragment());
        fr.addToBackStack(null);
        fr.commit();

        return true;
    }


}
