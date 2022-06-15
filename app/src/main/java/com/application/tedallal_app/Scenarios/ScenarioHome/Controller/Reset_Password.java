package com.application.tedallal_app.Scenarios.ScenarioHome.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.application.tedallal_app.NetworkLayer.Apicalls;
import com.application.tedallal_app.NetworkLayer.NetworkInterface;
import com.application.tedallal_app.NetworkLayer.ResponseModel;
import com.application.tedallal_app.R;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.Objects;

import es.dmoral.toasty.Toasty;

public class Reset_Password extends AppCompatActivity implements NetworkInterface {

    Button btnReset;
    EditText editemail;
    LinearLayout loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        btnReset = findViewById(R.id.btnReset);
        editemail = findViewById(R.id.editEmail);
        loading = findViewById(R.id.loading);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (editemail.getText().toString().equals("")) {


                    editemail.setError(getString(R.string.please_enter_email_address));
                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .repeat(1)
                            .playOn(findViewById(R.id.editEmail));


                }else  if (!editemail.getText().toString().contains("@") && !editemail.getText().toString().contains(".com")) {

                    editemail.setError(getString(R.string.please_enter_valid_email));

                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .repeat(1)
                            .playOn(findViewById(R.id.editEmail));


                }else {

                    loading.setVisibility(View.VISIBLE);
                    new Apicalls(Reset_Password.this,Reset_Password.this).get_Forget_Password(editemail.getText().toString());

                }


            }
        });


    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {
        loading.setVisibility(View.GONE);
        Log.e("response",model.getResponse());

        if (model.getResponse().equals("True")){

            Toasty.success(Objects.requireNonNull(Reset_Password.this), R.string.the_password_sent_to_mailbox, Toast.LENGTH_LONG).show();

            startActivity(new Intent(Reset_Password.this, SignIn.class));
            finish();
        }


    }

    @Override
    public void OnError(VolleyError error) {
        loading.setVisibility(View.GONE);
        Log.e("error",error.toString());

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}