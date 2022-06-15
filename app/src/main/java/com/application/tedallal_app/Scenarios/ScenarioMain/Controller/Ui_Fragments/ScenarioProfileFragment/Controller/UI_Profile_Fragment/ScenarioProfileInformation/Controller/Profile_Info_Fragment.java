package com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioProfileFragment.Controller.UI_Profile_Fragment.ScenarioProfileInformation.Controller;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.VolleyError;
import com.application.tedallal_app.NetworkLayer.Apicalls;
import com.application.tedallal_app.NetworkLayer.NetworkInterface;
import com.application.tedallal_app.NetworkLayer.ResponseModel;
import com.application.tedallal_app.R;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioAddAddress.Model.ModelAllCountries;
import com.application.tedallal_app.Scenarios.ScenarioHome.Model.ModelRegisterResponse;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.MainActivity;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioHomeFragment.Controller.Home_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioProfileFragment.Controller.Profile_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioMain.Pattrens.IFOnBackPressed;
import com.application.tedallal_app.local_data.saved_data;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.dmoral.toasty.Toasty;

public class Profile_Info_Fragment extends Fragment implements IFOnBackPressed, NetworkInterface {


    private View view;
    Button btnsave, btnback, btnedit;
    EditText editname, editmail, editphone, editpassword, editconfirmpassword;
    LinearLayout linearenable, lineardisable;
    LinearLayout loading;
    int x = 0;
    ModelRegisterResponse[] registerResponses;
    ArrayAdapter<String> countryAdapter;
    Spinner spinnercountry;
    List<String> countryList = new ArrayList<>();
    ModelAllCountries[] allCountries;
    String country = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.profile_info_fragment, container, false);

        Profile_Fragment.back = "back";
        btnback = view.findViewById(R.id.btnBack);
        btnedit = view.findViewById(R.id.btnEditProfile);
        btnsave = view.findViewById(R.id.btnSave);
        editname = view.findViewById(R.id.editUserName);
        editmail = view.findViewById(R.id.editEmail);
        editpassword = view.findViewById(R.id.editPassword);
        editconfirmpassword = view.findViewById(R.id.editConfirmPassword);
        editphone = view.findViewById(R.id.editPhone);
        lineardisable = view.findViewById(R.id.linearDisableEdit);
        linearenable = view.findViewById(R.id.linearEnableEdit);
        loading = view.findViewById(R.id.loading);
        spinnercountry = (Spinner) view.findViewById(R.id.spinnerCountry);


        lineardisable.setVisibility(View.GONE);
        linearenable.setVisibility(View.VISIBLE);


        editconfirmpassword.setEnabled(false);
        editpassword.setEnabled(false);
        editphone.setEnabled(false);
        editname.setEnabled(false);
        editmail.setEnabled(false);
        spinnercountry.setEnabled(false);

//        countryList.add("     ");
//        countryList.add(getString(R.string.Saudi));


        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmailValid(editmail.getText().toString()) == false) {

                    editmail.setError(getString(R.string.please_enter_valid_email));

                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .repeat(1)
                            .playOn(view.findViewById(R.id.editEmail));

                } else {

                    if (editname.getText().toString().equals("")) {
                        editname.setError(getString(R.string.please_enter_fullname));
                        YoYo.with(Techniques.Shake)
                                .duration(700)
                                .repeat(1)
                                .playOn(view.findViewById(R.id.editUserName));

                    } else if (editmail.getText().toString().equals("")) {

                        editmail.setError(getString(R.string.please_enter_email_address));

                        YoYo.with(Techniques.Shake)
                                .duration(700)
                                .repeat(1)
                                .playOn(view.findViewById(R.id.editEmail));

                    } else if (!editmail.getText().toString().contains("@") && !editmail.getText().toString().contains(".com")) {

                        editmail.setError(getString(R.string.please_enter_valid_email));

                        YoYo.with(Techniques.Shake)
                                .duration(700)
                                .repeat(1)
                                .playOn(view.findViewById(R.id.editEmail));


                    } else if (editphone.getText().toString().equals("")) {

                        editphone.setError(getString(R.string.please_enter_phone_number));
                        YoYo.with(Techniques.Shake)
                                .duration(700)
                                .repeat(1)
                                .playOn(view.findViewById(R.id.editPhone));

                    } else if (editpassword.getText().toString().equals("")) {


                        editpassword.setError(getString(R.string.please_enter_password));
                        YoYo.with(Techniques.Shake)
                                .duration(700)
                                .repeat(1)
                                .playOn(view.findViewById(R.id.editPassword));

                    } else if (editconfirmpassword.getText().toString().equals("")) {

                        editconfirmpassword.setError(getString(R.string.please_enter_confirm_password));
                        YoYo.with(Techniques.Shake)
                                .duration(700)
                                .repeat(1)
                                .playOn(view.findViewById(R.id.editConfirmPassword));

                    } else if (!editpassword.getText().toString().equals(editconfirmpassword.getText().toString())) {


                        editpassword.setError(getString(R.string.sorry_your_password_dose_not_match));
                        YoYo.with(Techniques.Shake)
                                .duration(700)
                                .repeat(1)
                                .playOn(view.findViewById(R.id.editPassword));


                        editconfirmpassword.setError(getString(R.string.sorry_your_password_dose_not_match));
                        YoYo.with(Techniques.Shake)
                                .duration(700)
                                .repeat(1)
                                .playOn(view.findViewById(R.id.editConfirmPassword));


                    } else if (country.equals("") || country.equals("     ")) {

                        Toasty.error(Objects.requireNonNull(getContext()), getString(R.string.please_enter_your_country), Toast.LENGTH_LONG).show();


                    } else {
                        String user_id = saved_data.get_user_id(Objects.requireNonNull(getContext()));
                        x = 2;
                        MainActivity.loading.setVisibility(View.VISIBLE);
                        new Apicalls(getContext(), Profile_Info_Fragment.this).update_User_Profile(editname.getText().toString(), editmail.getText().toString(), editpassword.getText().toString(), editphone.getText().toString(), "picture", country, user_id);


                    }

                }
            }
        });


        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                lineardisable.setVisibility(View.VISIBLE);
                linearenable.setVisibility(View.GONE);

                spinnercountry.setEnabled(true);
                editconfirmpassword.setEnabled(true);
                editpassword.setEnabled(true);
                editphone.setEnabled(true);
                editname.setEnabled(true);
                editmail.setEnabled(true);


            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lineardisable.setVisibility(View.GONE);
                linearenable.setVisibility(View.VISIBLE);

                spinnercountry.setEnabled(false);
                editconfirmpassword.setEnabled(false);
                editpassword.setEnabled(false);
                editphone.setEnabled(false);
                editname.setEnabled(false);
                editmail.setEnabled(false);


            }
        });

        x = 1;
        MainActivity.loading.setVisibility(View.VISIBLE);
        new Apicalls(getContext(), Profile_Info_Fragment.this).get_All_Countries();


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public boolean onBackPressed() {

        FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
        fr.replace(R.id.fragment_container, new Home_Fragment());
        fr.addToBackStack(null);
        fr.commit();
        MainActivity.navigation.setSelectedItemId(R.id.home_nav);
        return true;
    }


    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {
        MainActivity.loading.setVisibility(View.GONE);
        Log.e("response", model.getResponse());

        if (x == 3) {
            x = 0;
            Gson gson = new Gson();

            registerResponses = gson.fromJson(model.getResponse(), ModelRegisterResponse[].class);

            if (registerResponses.length !=0){

                editphone.setText(registerResponses[0].getPhone());


                String country = registerResponses[0].getCountry();
                if (!country.equals(getString(R.string.Saudi))) {

                    country = "     ";

                }
                spinnercountry.setSelection(countryAdapter.getPosition(country));


                editmail.setText(registerResponses[0].getEmail());
                editname.setText(registerResponses[0].getName());


                Profile_Fragment.txtprofiename.setText((registerResponses[0].getName()));
                Profile_Fragment.txtprofilecountryname.setText((registerResponses[0].getCountry()));

            }




        } else if (x == 2) {
            x = 0;
            Log.e("response", model.getResponse());

            Toasty.success(Objects.requireNonNull(getContext()), R.string.your_account_edited_successfully, Toast.LENGTH_LONG).show();

            FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
            fr.replace(R.id.fragment_container2, new Profile_Info_Fragment());
            fr.addToBackStack(null);
            fr.commit();

        } else if (x == 1) {

            x = 0;
            Gson gson = new Gson();
            allCountries = gson.fromJson(model.getResponse(), ModelAllCountries[].class);
            if (allCountries.length != 0) {

                for (int i = 0; i < allCountries.length; i++) {

                    if (saved_data.get_lang_num(Objects.requireNonNull(getContext())).equals("ar")) {


                        countryList.add(allCountries[i].getAr());


                    } else if (saved_data.get_lang_num(getContext()).equals("en")) {


                        countryList.add(allCountries[i].getEn());

                    }

                }

            }
            countryAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_text, countryList);
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
            x = 3;
            MainActivity.loading.setVisibility(View.VISIBLE);
            String user_id = saved_data.get_user_id(Objects.requireNonNull(getContext()));
            new Apicalls(getContext(), Profile_Info_Fragment.this).get_User_Profile(user_id);
        }


    }

    @Override
    public void OnError(VolleyError error) {
        MainActivity.loading.setVisibility(View.GONE);
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
