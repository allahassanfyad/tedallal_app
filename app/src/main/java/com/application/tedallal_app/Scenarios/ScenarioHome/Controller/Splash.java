package com.application.tedallal_app.Scenarios.ScenarioHome.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;


import com.application.tedallal_app.R;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.MainActivity;
import com.application.tedallal_app.local_data.saved_data;
import com.application.tedallal_app.local_data.send_data;

public class Splash extends AppCompatActivity {

    String language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        language = saved_data.get_lang_num(Splash.this);

        if (language.equals("") || language == null) {

//            language = Locale.getDefault().getDisplayLanguage();
//            Log.e("language", language);
//
//            if (language.equals("العربية")) {

                send_data.SET_LANGUAGE_NUM(Splash.this, String.valueOf("ar"));
                changeLang(Splash.this, "ar");
//            } else {
//
//                send_data.SET_LANGUAGE_NUM(Splash.this, String.valueOf("en"));
//                changeLang(Splash.this, "en");
//
//            }

            Log.e("lang", String.valueOf(Locale.getDefault().getDisplayLanguage()));
            Log.e("SavedLang", saved_data.get_lang_num(Splash.this));

        } else {

            loadLocale();

        }

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

//                if (saved_data.get_user_check(Splash.this) == true) {
//
//                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                    finish();
//
//                }else {

                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();

//                }


            }
        };
        new Timer().schedule(timerTask, 3000);

    }


    public static void changeLang(Context context, String lang) {
        Locale myLocale = new Locale(lang);
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = myLocale;
//        send_data.SET_LANGUAGE_NUM(Splash.this, config.locale);
        Log.e("languageeeee", String.valueOf(config.locale));
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
    }


    private void loadLocale() {

        String languae = saved_data.get_lang_num(this);
        changeLang(Splash.this, languae);

    }

}