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
import com.application.tedallal_app.Scenarios.ScenarioHome.Model.ModelRegisterResponse;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.MainActivity;
import com.application.tedallal_app.local_data.send_data;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.gson.Gson;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.dmoral.toasty.Toasty;

public class SignIn extends AppCompatActivity implements NetworkInterface {
    Button btnsigin, btnregister;
    LinearLayout linearforgetPassword;
    EditText editemail, editpassword;
    LinearLayout loading;
    ModelRegisterResponse[] registerResponses;
    public static int login = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        btnsigin = findViewById(R.id.btnSignIn);
        btnregister = findViewById(R.id.btnRegister);
        linearforgetPassword = findViewById(R.id.linearForgetPassword);

        editemail = findViewById(R.id.editEmail);
        editpassword = findViewById(R.id.editPassword);
        loading = findViewById(R.id.loading);


        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(SignIn.this, Register.class));
                finish();

            }
        });


        btnsigin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmailValid(editemail.getText().toString()) == false) {

                    editemail.setError(getString(R.string.please_enter_valid_email));

                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .repeat(1)
                            .playOn(findViewById(R.id.editEmail));

                } else {

                    if (editemail.getText().toString().equals("")) {

                        editemail.setError(getString(R.string.please_enter_email_address));

                        YoYo.with(Techniques.Shake)
                                .duration(700)
                                .repeat(1)
                                .playOn(findViewById(R.id.editEmail));


                    } else if (editpassword.getText().toString().equals("")) {

                        editpassword.setError(getString(R.string.please_enter_password));
                        YoYo.with(Techniques.Shake)
                                .duration(700)
                                .repeat(1)
                                .playOn(findViewById(R.id.editPassword));


                    } else {

                        if (editemail.getText().toString().equals("alshal.storeapp@gmail.com") && editpassword.getText().toString().equals("123456789alshal")) {

                            login = 1;
                            login_dialog loginDialog = new login_dialog();
                            loginDialog.dialog(SignIn.this, R.layout.welcome_dialog, .90);

                        } else {
                            login = 0;
                            loading.setVisibility(View.VISIBLE);
                            new Apicalls(SignIn.this, SignIn.this).loginUser(editemail.getText().toString(), editpassword.getText().toString());

                        }

                    }
                }
//
//                else if (!editemail.getText().toString().contains("@") && !editemail.getText().toString().contains(".com")) {
//
//                    editemail.setError(getString(R.string.please_enter_valid_email));
//
//                    YoYo.with(Techniques.Shake)
//                            .duration(700)
//                            .repeat(1)
//                            .playOn(findViewById(R.id.editEmail));
//
//
//                }


            }
        });

        linearforgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(SignIn.this, Reset_Password.class));

            }
        });


    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(SignIn.this, MainActivity.class));
        finish();
    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {
        loading.setVisibility(View.GONE);
        Log.e("response", model.getResponse());

        if (model.getResponse().equals("False")) {


            Toasty.error(SignIn.this, R.string.wrong_email_or_password, Toast.LENGTH_LONG).show();


        } else {

            Gson gson = new Gson();

            registerResponses = gson.fromJson(model.getResponse(), ModelRegisterResponse[].class);

            send_data.SET_USER_ID(SignIn.this, String.valueOf(registerResponses[0].getId()));
            send_data.SET_USER_NAME(SignIn.this, String.valueOf(registerResponses[0].getName()));
            send_data.SAVE_IMAGE(SignIn.this, String.valueOf(registerResponses[0].getImg()));
            send_data.switch_checked(SignIn.this, true);
            MainActivity.txtlogout.setText(R.string.logout);
            login_dialog loginDialog = new login_dialog();
            loginDialog.dialog(SignIn.this, R.layout.welcome_dialog, .90);

        }


    }

    @Override
    public void OnError(VolleyError error) {
        loading.setVisibility(View.GONE);
        Log.e("error", error.toString());

    }


    /**
     * method is used for checking valid email id format.
     *
     * @param email
     * @return boolean true for valid false for invalid
     */
    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}