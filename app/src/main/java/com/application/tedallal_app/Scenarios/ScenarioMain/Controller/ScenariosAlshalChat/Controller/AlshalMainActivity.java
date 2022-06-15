package com.application.tedallal_app.Scenarios.ScenarioMain.Controller.ScenariosAlshalChat.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.application.tedallal_app.R;
import com.application.tedallal_app.Scenarios.ScenarioMain.Pattrens.IFOnBackPressed;
import com.application.tedallal_app.Utils.TinyDB;

public class AlshalMainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    TinyDB tinyDB;
    public static LinearLayout loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alshal_main);

        tinyDB = new TinyDB(this);
        loading = findViewById(R.id.loading);
        fragmentManager = getSupportFragmentManager();


        Alshal_Messages_Fragment alshal_messages_fragment = new Alshal_Messages_Fragment();
        loadFragment(alshal_messages_fragment);

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

}