package com.application.tedallal_app.Scenarios.ScenarioMain.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;

import com.application.tedallal_app.R;
import com.application.tedallal_app.Utils.TinyDB;
import com.application.tedallal_app.local_data.saved_data;

import java.util.Locale;
import java.util.Objects;

public class Webview_Faq extends AppCompatActivity {

    TinyDB tinyDB;
    WebView webView;
    String language ="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_faq);

        webView = findViewById(R.id.webview);



        if (saved_data.get_lang_num(this).equals("ar")) {
            Log.e("faq_ar",Locale.getDefault().getLanguage());
            webView.loadUrl("https://alshal.sa/app/faq-ar/");

        } else if (saved_data.get_lang_num(this).equals("en")) {

            Log.e("faq_en", Locale.getDefault().getLanguage());
            webView.loadUrl("https://alshal.sa/app/faq-en/");

        }

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}