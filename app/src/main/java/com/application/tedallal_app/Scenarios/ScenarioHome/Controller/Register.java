package com.application.tedallal_app.Scenarios.ScenarioHome.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.application.tedallal_app.NetworkLayer.Apicalls;
import com.application.tedallal_app.NetworkLayer.NetworkInterface;
import com.application.tedallal_app.NetworkLayer.ResponseModel;
import com.application.tedallal_app.R;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioAddAddress.Model.ModelAllCountries;
import com.application.tedallal_app.Scenarios.ScenarioHome.Model.ModelRegisterResponse;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.MainActivity;
import com.application.tedallal_app.local_data.saved_data;
import com.application.tedallal_app.local_data.send_data;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.dmoral.toasty.Toasty;

public class Register extends AppCompatActivity implements NetworkInterface {

    Button btncreateaccount;
    EditText editname, editphone, editpassword, editconfirmpassword, editemail;
    LinearLayout loading;
    ModelRegisterResponse[] registerResponses;

    ArrayAdapter<String> countryAdapter;
    Spinner spinnercountry;
    List<String> countryList = new ArrayList<>();
    ModelAllCountries[] allCountries;
    String country = "";
    int x = 0;
    CheckBox checkterms;
    LinearLayout linearcheck;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btncreateaccount = findViewById(R.id.btnCreateAccount);

        editname = findViewById(R.id.editName);

        editconfirmpassword = findViewById(R.id.editConfirmPassword);
        editpassword = findViewById(R.id.editPassword);
        editphone = findViewById(R.id.editPhone);
        editemail = findViewById(R.id.editEmail);
        loading = findViewById(R.id.loading);
        spinnercountry = (Spinner) findViewById(R.id.spinnerCountry);
        checkterms = findViewById(R.id.checkTermsAndConditions);
        linearcheck = findViewById(R.id.linearCheckTerms);


//        countryList.add("     ");
//        countryList.add(getString(R.string.Saudi));
//
//
//        countryAdapter = new ArrayAdapter<String>(this, R.layout.spinner_text, countryList);
//        countryAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);
//        spinnercountry.setAdapter(countryAdapter);
//
////        spinnercountry.setSelection(countryAdapter.getPosition("مصر"));
//
//        spinnercountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//
//                country = String.valueOf(countryList.get(position));
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                Log.e("nothingSelected", "nothing");
//            }
//        });

        linearcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Terms_Popup change_language_popup = new Terms_Popup();
                change_language_popup.dialog(Register.this, R.layout.terms_popup, .90, checkterms);

            }
        });


        btncreateaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isEmailValid(editemail.getText().toString()) == false) {

                    editemail.setError(getString(R.string.please_enter_valid_email));

                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .repeat(1)
                            .playOn(findViewById(R.id.editEmail));

                } else {


                    if (editname.getText().toString().equals("")) {

                        editname.setError(getString(R.string.please_enter_fullname));
                        YoYo.with(Techniques.Shake)
                                .duration(700)
                                .repeat(1)
                                .playOn(findViewById(R.id.editName));


                    } else if (editphone.getText().toString().equals("")) {

                        editphone.setError(getString(R.string.please_enter_phone_number));
                        YoYo.with(Techniques.Shake)
                                .duration(700)
                                .repeat(1)
                                .playOn(findViewById(R.id.editPhone));


                    } else if (editemail.getText().toString().equals("")) {

                        editemail.setError(getString(R.string.please_enter_email_address));

                        YoYo.with(Techniques.Shake)
                                .duration(700)
                                .repeat(1)
                                .playOn(findViewById(R.id.editEmail));


                    } else if (country.equals("") || country.equals("     ")) {

                        Toasty.error(Objects.requireNonNull(Register.this), R.string.please_enter_your_country, Toast.LENGTH_LONG).show();


                    } else if (editpassword.getText().toString().equals("")) {

                        editpassword.setError(getString(R.string.please_enter_password));
                        YoYo.with(Techniques.Shake)
                                .duration(700)
                                .repeat(1)
                                .playOn(findViewById(R.id.editPassword));


                    } else if (editconfirmpassword.getText().toString().equals("")) {

                        editconfirmpassword.setError(getString(R.string.please_enter_confirm_password));
                        YoYo.with(Techniques.Shake)
                                .duration(700)
                                .repeat(1)
                                .playOn(findViewById(R.id.editConfirmPassword));


                    } else if (!editpassword.getText().toString().equals(editconfirmpassword.getText().toString())) {


                        editpassword.setError(getString(R.string.sorry_your_password_dose_not_match));
                        YoYo.with(Techniques.Shake)
                                .duration(700)
                                .repeat(1)
                                .playOn(findViewById(R.id.editPassword));


                        editconfirmpassword.setError(getString(R.string.sorry_your_password_dose_not_match));
                        YoYo.with(Techniques.Shake)
                                .duration(700)
                                .repeat(1)
                                .playOn(findViewById(R.id.editConfirmPassword));


                    } else if (!checkterms.isChecked()) {

                        Toasty.error(Objects.requireNonNull(Register.this), R.string.please_agree_for_terms, Toast.LENGTH_LONG).show();


                    } else {

                        x = 2;
                        loading.setVisibility(View.VISIBLE);
                        new Apicalls(Register.this, Register.this).registerUser(editname.getText().toString(), editemail.getText().toString(), editpassword.getText().toString(), editphone.getText().toString(), "img", country);


                    }
                }

            }
        });

        x = 1;
        loading.setVisibility(View.VISIBLE);
        new Apicalls(this, Register.this).get_All_Countries();

    }


    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {
        loading.setVisibility(View.GONE);
        Log.e("response", model.getResponse());

        if (x == 1) {
            x = 0;
            Gson gson = new Gson();
            allCountries = gson.fromJson(model.getResponse(), ModelAllCountries[].class);
            if (allCountries.length != 0) {

                for (int i = 0; i < allCountries.length; i++) {

                    if (saved_data.get_lang_num(Objects.requireNonNull(this)).equals("ar")) {


                        countryList.add(allCountries[i].getAr());


                    } else if (saved_data.get_lang_num(this).equals("en")) {


                        countryList.add(allCountries[i].getEn());

                    }

                }

            }
            countryAdapter = new ArrayAdapter<String>(this, R.layout.spinner_text, countryList);
            countryAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);
            spinnercountry.setAdapter(countryAdapter);

//        spinnercountry.setSelection(countryAdapter.getPosition("مصر"));

            spinnercountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                    country = String.valueOf(countryList.get(position));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    Log.e("nothingSelected", "nothing");
                }
            });


        } else if (x == 2) {
            x = 0;

            Gson gson = new Gson();

            registerResponses = gson.fromJson(model.getResponse(), ModelRegisterResponse[].class);

            send_data.SET_USER_ID(Register.this, String.valueOf(registerResponses[0].getId()));
            send_data.switch_checked(Register.this, true);
            MainActivity.txtlogout.setText("تسجيل الخروج");
            login_dialog loginDialog = new login_dialog();
            loginDialog.dialog(Register.this, R.layout.welcome_dialog, .90);

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