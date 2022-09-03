package com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioAddAddress.Controller;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioAllAddress.Controller.All_Address_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.MainActivity;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioProfileFragment.Controller.Profile_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.Ui_Fragments.ScenarioProfileFragment.Controller.UI_Profile_Fragment.ScenarioDeliveryAddress.Controller.Profile_Delivery_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioMain.Pattrens.IFOnBackPressed;
import com.application.tedallal_app.Utils.TinyDB;
import com.application.tedallal_app.local_data.saved_data;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import es.dmoral.toasty.Toasty;

public class Add_Address_Fragment extends Fragment implements IFOnBackPressed, NetworkInterface {

    private View view;

    EditText editname, editcountryCode, editcity, editArea, editstreat, editphone, editblock, editbuilding, editapartment, editfloor, edittypeofDelivery, editavenve;
    Button btnContinue, btnBack;
    ArrayAdapter<String> countryAdapter;
    Spinner spinnercountry;
    List<String> countryList = new ArrayList<>();
    List<String> countryCodeList = new ArrayList<>();
    String country = "";
    TinyDB tinyDB;
    ModelAllCountries[] allCountries;
    int x = 0;
    String phone = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.add_address_fragment, container, false);

        Profile_Fragment.back = "add_address";
        btnContinue = view.findViewById(R.id.btnContinue);

        tinyDB = new TinyDB(getContext());
        btnBack = view.findViewById(R.id.btnBack);
        editcity = view.findViewById(R.id.editCity);
        spinnercountry = view.findViewById(R.id.spinnerCountry);
        editArea = view.findViewById(R.id.editArea);
        editname = view.findViewById(R.id.editName);
        editphone = view.findViewById(R.id.editPhone);
        editblock = view.findViewById(R.id.editBlock);
        editbuilding = view.findViewById(R.id.editBuildingNumber);
        editapartment = view.findViewById(R.id.editApartmentNumber);
        editfloor = view.findViewById(R.id.editFloorNumber);
        edittypeofDelivery = view.findViewById(R.id.editTypeOfDeliveryPlace);
        editavenve = view.findViewById(R.id.editAvenve);
        editstreat = view.findViewById(R.id.editStreat);
        editcountryCode = view.findViewById(R.id.editCountryCode);


//        countryList.add("     ");
//        countryList.add(getString(R.string.Saudi));


        x = 1;
        MainActivity.loading.setVisibility(View.VISIBLE);
        new Apicalls(getContext(), Add_Address_Fragment.this).get_All_Countries();


        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tinyDB = new TinyDB(getContext());

                if (editname.getText().toString().equals("")) {

                    editname.setError(getString(R.string.please_enter_fullname));
                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .repeat(1)
                            .playOn(view.findViewById(R.id.editName));


                } else if (editphone.getText().toString().equals("")) {

                    editphone.setError(getString(R.string.please_enter_phone_number));
                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .repeat(1)
                            .playOn(view.findViewById(R.id.editPhone));


                } else if (editArea.getText().toString().equals("")) {

                    editArea.setError(getString(R.string.please_enter_ىeighborhood_name));
                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .repeat(1)
                            .playOn(view.findViewById(R.id.editNabour));


                } else if (editstreat.getText().toString().equals("")) {

                    editstreat.setError(getString(R.string.please_enter_street_name));
                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .repeat(1)
                            .playOn(view.findViewById(R.id.editStreat));


                } else if (editblock.getText().toString().equals("")) {

                    editblock.setError("Please enter the block number");
                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .repeat(1)
                            .playOn(view.findViewById(R.id.editBlock));


                } else if (editbuilding.getText().toString().equals("")) {

                    editbuilding.setError("Please enter the building number");
                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .repeat(1)
                            .playOn(view.findViewById(R.id.editBuildingNumber));


                }  else {

                    if (Profile_Delivery_Fragment.address == 2) {

                        MainActivity.loading.setVisibility(View.VISIBLE);

//                        if (!editcountryCode.getText().toString().equals("")) {
//                            phone = editcountryCode.getText().toString() + editphone.getText().toString();
//                        } else {
//
//                            phone = editphone.getText().toString();
//                        }
                        phone = editphone.getText().toString();


                        String address_id = tinyDB.getString("AddressId");
                        String address = editstreat.getText().toString() + " " + country + " " + editcity.getText().toString() + " " + editArea.getText().toString() + " " + editcountryCode.getText().toString() + " " + editphone.getText().toString();
                        new Apicalls(getContext(), Add_Address_Fragment.this).edit_Address(editname.getText().toString(), country, editcity.getText().toString(), editArea.getText().toString(), editstreat.getText().toString(), "", phone, saved_data.get_user_id(Objects.requireNonNull(getContext())), address_id, editblock.getText().toString(), editbuilding.getText().toString(), editapartment.getText().toString(), editfloor.getText().toString(), editavenve.getText().toString());


                    } else {

                        MainActivity.loading.setVisibility(View.VISIBLE);
//                        if (!editcountryCode.getText().toString().equals("")) {
//                            phone = editcountryCode.getText().toString() + editphone.getText().toString();
//                        } else {
//
//                            phone = editphone.getText().toString();
//                        }
                        phone = editphone.getText().toString();

                        String address = editstreat.getText().toString() + " " + country + " " + editcity.getText().toString() + " " + editArea.getText().toString() + " " + editcountryCode.getText().toString() + " " + editphone.getText().toString() + " " + editblock.getText().toString() + " " + editbuilding.getText().toString() + " " + editapartment.getText().toString() + " " + editfloor.getText().toString();
                        new Apicalls(getContext(), Add_Address_Fragment.this).add_Address(editname.getText().toString(), country, editcity.getText().toString(), editArea.getText().toString(), editstreat.getText().toString(), "", phone, editblock.getText().toString(), editbuilding.getText().toString(), editapartment.getText().toString(), editfloor.getText().toString(), edittypeofDelivery.getText().toString(), saved_data.get_user_id(Objects.requireNonNull(getContext())), editavenve.getText().toString(), address);


                    }

                }


            }
        });


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Profile_Delivery_Fragment.address == 1 || Profile_Delivery_Fragment.address == 2) {
                    Profile_Delivery_Fragment.address = 0;

                    FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                    fr.replace(R.id.fragment_container2, new Profile_Delivery_Fragment());
                    fr.addToBackStack(null);
                    fr.commit();

                } else {

                    FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                    fr.replace(R.id.fragment_container, new All_Address_Fragment());
                    fr.addToBackStack(null);
                    fr.commit();
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

        if (Profile_Delivery_Fragment.address == 1 || Profile_Delivery_Fragment.address == 2) {
            Profile_Delivery_Fragment.address = 0;

            FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
            fr.replace(R.id.fragment_container2, new Profile_Delivery_Fragment());
            fr.addToBackStack(null);
            fr.commit();

        } else {

            FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
            fr.replace(R.id.fragment_container, new All_Address_Fragment());
            fr.addToBackStack(null);
            fr.commit();
        }

//        MainActivity.navigation.setSelectedItemId(R.id.home_nav);
        return true;
    }


    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {
        MainActivity.loading.setVisibility(View.GONE);
        Log.e("response", model.getResponse());
        tinyDB = new TinyDB(getContext());
        if (x == 1) {
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
                    if (allCountries[i].getCodeCountry() != null) {

                        countryCodeList.add(allCountries[i].getCodeCountry());

                    } else {

                        countryCodeList.add("");

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
                    editcountryCode.setText(countryCodeList.get(position));


                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    Log.e("nothingSelected", "nothing");
                }
            });

            if (Profile_Delivery_Fragment.address == 2) {

                editstreat.setText(tinyDB.getString("AddressStreet"));
                editphone.setText(tinyDB.getString("AddressPhone"));
                editname.setText(tinyDB.getString("AddressName"));
                editArea.setText(tinyDB.getString("AddressArea"));
                editcity.setText(tinyDB.getString("AddressCity"));
                String counntry = tinyDB.getString("AddressCountry");
                spinnercountry.setSelection(countryAdapter.getPosition(counntry));

            }


        } else {


            Toasty.success(Objects.requireNonNull(getContext()), R.string.add_address_completed_successfully, Toast.LENGTH_LONG).show();

            if (Profile_Delivery_Fragment.address == 1 || Profile_Delivery_Fragment.address == 2) {
                Profile_Delivery_Fragment.address = 0;

                FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container2, new Profile_Delivery_Fragment());
                fr.addToBackStack(null);
                fr.commit();

            } else {

                FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new All_Address_Fragment());
                fr.addToBackStack(null);
                fr.commit();
            }

        }
    }

    @Override
    public void OnError(VolleyError error) {
        MainActivity.loading.setVisibility(View.GONE);
        Log.e("error", error.toString());

    }
}
