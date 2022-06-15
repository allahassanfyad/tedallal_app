package com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioAddCart.Controller;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.application.tedallal_app.R;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioCart.Controller.Cart_Fragment;
import com.application.tedallal_app.Scenarios.ScenarioFragments.ScenarioCart.Model.Rcy_Cart_Model;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.MainActivity;
import com.application.tedallal_app.Scenarios.ScenarioMain.Pattrens.IFOnBackPressed;
import com.application.tedallal_app.Utils.Realm_adapter;
import com.application.tedallal_app.Utils.TinyDB;
import com.bumptech.glide.Glide;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import es.dmoral.toasty.Toasty;
import io.realm.Realm;

public class Customize_Order_Fragment extends Fragment implements IFOnBackPressed {

    private View view;
    EditText edithieght, editoverallwidth, editwaist, editsleeve, editkalosh;
    EditText editdetails;
    Button btnback, btnaddtocart;
    TextView txttitle, txtopenvideo;


    ArrayAdapter<String> hieghtAdapter;
    ArrayAdapter<String> overallwidthAdapter;
    ArrayAdapter<String> waistAdapter;
    ArrayAdapter<String> sleeveAdapter;
    ArrayAdapter<String> kaloshAdapter;

    List<String> hieghtList = new ArrayList<>();
    List<String> overallwidthList = new ArrayList<>();
    List<String> waistList = new ArrayList<>();
    List<String> sleeveList = new ArrayList<>();
    List<String> kaloshList = new ArrayList<>();

    String hieght = "";
    String overallwidth = "";
    String waist = "";
    String sleeve = "";
    String kalosh = "";

    TinyDB tinyDB;
    Realm realm;
    ImageView imgcart;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.customize_order_fragment, container, false);

        tinyDB = new TinyDB(getContext());
        realm = Realm.getDefaultInstance();

        edithieght = view.findViewById(R.id.editHeight);
        editoverallwidth = view.findViewById(R.id.editOverallWidth);
        editwaist = view.findViewById(R.id.editTheWaist);
        editsleeve = view.findViewById(R.id.editSleeve);
        editkalosh = view.findViewById(R.id.editKlush);
        editdetails = view.findViewById(R.id.editDetails);
        btnback = view.findViewById(R.id.btnBack);
        btnaddtocart = view.findViewById(R.id.btnAddToCart);
        txttitle = view.findViewById(R.id.txtProductTitle);
        txtopenvideo = view.findViewById(R.id.txtOpenVideo);

        txttitle.setText(tinyDB.getString("ProductTitle"));

        imgcart = view.findViewById(R.id.imgCustomSize);

        txtopenvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=yaxaOXjVhlE"));
//                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent1.setPackage("com.google.android.youtube");
//                startActivity(intent1);
//                Toasty.error(Objects.requireNonNull(getContext()), "try", Toast.LENGTH_LONG).show();


                try {

                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=yaxaOXjVhlE"));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setPackage("com.google.android.youtube");
                    startActivity(intent);
//                    Toasty.error(Objects.requireNonNull(getContext()), "try", Toast.LENGTH_LONG).show();


                } catch (ActivityNotFoundException e) {

//                    Toasty.error(Objects.requireNonNull(getContext()), "catch", Toast.LENGTH_LONG).show();
                    Log.e("e", e.toString());
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=yaxaOXjVhlE")));

                }

            }
        });

        Glide.with(Objects.requireNonNull(getContext()))
                .load(tinyDB.getString("ProductImage"))
                .into(imgcart);

//        txtopenvideo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                try {
//
//                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=yaxaOXjVhlE"));
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    intent.setPackage("com.google.android.youtube");
//                    startActivity(intent);
//                    Toasty.error(Objects.requireNonNull(getContext()), "try", Toast.LENGTH_LONG).show();
//
//
//                } catch (ActivityNotFoundException e) {
//
//                    Toasty.error(Objects.requireNonNull(getContext()), "catch", Toast.LENGTH_LONG).show();
//                    Log.e("e", e.toString());
//                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=yaxaOXjVhlE")));
//
//                }
//
//            }
//        });

//        hieghtList.add("52");
//        hieghtList.add("54");
//        hieghtList.add("56");
//        hieghtList.add("58");
//        hieghtList.add("60");
//
//        overallwidthList.add("58");
//        overallwidthList.add("60");
//        overallwidthList.add("62");
//        overallwidthList.add("64");
//        overallwidthList.add("66");
//
//        waistList.add("22");
//        waistList.add("23");
//        waistList.add("24");
//        waistList.add("25");
//        waistList.add("26");
//
//        sleeveList.add("8");
//        sleeveList.add("8");
//        sleeveList.add("9");
//        sleeveList.add("9");
//        sleeveList.add("9.5");
//
//        kaloshList.add("36");
//        kaloshList.add("37");
//        kaloshList.add("38");
//        kaloshList.add("39");
//        kaloshList.add("40");


//        hieghtAdapter = new ArrayAdapter<String>(Objects.requireNonNull(getContext()), R.layout.spinner_text, hieghtList);
//        hieghtAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);
//        spinnerhieght.setAdapter(hieghtAdapter);
//
//
//        overallwidthAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_text, overallwidthList);
//        overallwidthAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);
//        spinneroverallwidth.setAdapter(overallwidthAdapter);
//
//
//        waistAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_text, waistList);
//        waistAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);
//        spinnerwaist.setAdapter(waistAdapter);
//
//        sleeveAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_text, sleeveList);
//        sleeveAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);
//        spinnersleeve.setAdapter(sleeveAdapter);
//
//        kaloshAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_text, kaloshList);
//        kaloshAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);
//        spinnerkalosh.setAdapter(kaloshAdapter);
//
//
//        spinnerhieght.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//
//                hieght = String.valueOf(hieghtList.get(position));
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                Log.e("nothingSelected", "nothing");
//            }
//        });
//
//        spinneroverallwidth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//
//                overallwidth = String.valueOf(overallwidthList.get(position));
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                Log.e("nothingSelected", "nothing");
//            }
//        });
//
//        spinnerwaist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//
//                waist = String.valueOf(waistList.get(position));
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                Log.e("nothingSelected", "nothing");
//            }
//        });
//
//        spinnersleeve.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//
//                sleeve = String.valueOf(sleeveList.get(position));
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                Log.e("nothingSelected", "nothing");
//            }
//        });
//
//        spinnerkalosh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//
//                kalosh = String.valueOf(kaloshList.get(position));
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                Log.e("nothingSelected", "nothing");
//            }
//        });


        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new Add_Cart_Fragment());
                fr.addToBackStack(null);
                fr.commit();

            }
        });

        btnaddtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tinyDB = new TinyDB(getContext());

                if (edithieght.getText().toString().equals("")) {

                    Toasty.error(Objects.requireNonNull(getContext()), R.string.please_complete_order_details, Toast.LENGTH_LONG).show();

                    edithieght.setError(getString(R.string.please_enter_length));

                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .repeat(1)
                            .playOn(view.findViewById(R.id.editHeight));


                } else if (editkalosh.getText().toString().equals("")) {

                    Toasty.error(Objects.requireNonNull(getContext()), R.string.please_complete_order_details, Toast.LENGTH_LONG).show();

                    editkalosh.setError(getString(R.string.please_enter_kalush));

                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .repeat(1)
                            .playOn(view.findViewById(R.id.editKlush));


                } else if (editoverallwidth.getText().toString().equals("")) {

                    Toasty.error(Objects.requireNonNull(getContext()), R.string.please_complete_order_details, Toast.LENGTH_LONG).show();

                    editoverallwidth.setError(getString(R.string.please_enter_width));

                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .repeat(1)
                            .playOn(view.findViewById(R.id.editOverallWidth));


                } else if (editsleeve.getText().toString().equals("")) {

                    Toasty.error(Objects.requireNonNull(getContext()), R.string.please_complete_order_details, Toast.LENGTH_LONG).show();

                    editsleeve.setError(getString(R.string.please_enter_sleeve));

                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .repeat(1)
                            .playOn(view.findViewById(R.id.editSleeve));


                } else if (editwaist.getText().toString().equals("")) {

                    Toasty.error(Objects.requireNonNull(getContext()), R.string.please_complete_order_details, Toast.LENGTH_LONG).show();

                    editwaist.setError(getString(R.string.please_enter_waist));

                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .repeat(1)
                            .playOn(view.findViewById(R.id.editTheWaist));


                } else {

                    String Id = tinyDB.getString("ProductId");
                    String title = tinyDB.getString("ProductTitle");
                    String price = tinyDB.getString("ProductPrice");
                    String quntity = tinyDB.getString("ProductQuntaty");
                    String Imagecart = tinyDB.getString("ProductImage");
                    String size = "مخصص";

                    Rcy_Cart_Model c = new Rcy_Cart_Model();


                    c.setProductid(Id);
                    c.setTxttitle(title);
                    c.setTxtprice(price);
                    c.setTxtnumberchoose(quntity);
                    c.setImghome(Imagecart);
                    c.setTxtsize(size);
                    c.setHieght(edithieght.getText().toString());
                    c.setOverallwidth(editoverallwidth.getText().toString());
                    c.setWaist(editwaist.getText().toString());
                    c.setSleeve(editsleeve.getText().toString());
                    c.setKalosh(editkalosh.getText().toString());
                    c.setExtraRequest(editdetails.getText().toString());


                    Realm_adapter adapter = new Realm_adapter(realm);
                    adapter.save(c);

                    Toasty.success(Objects.requireNonNull(getContext()), R.string.your_request_addeed_to_cart_successfully, Toast.LENGTH_LONG).show();


                    FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                    fr.replace(R.id.fragment_container, new Cart_Fragment());
                    fr.addToBackStack(null);
                    fr.commit();
                    MainActivity mainActivity1 = new MainActivity();
                    mainActivity1.setcartcount();

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

        FragmentTransaction fr = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
        fr.replace(R.id.fragment_container, new Add_Cart_Fragment());
        fr.addToBackStack(null);
        fr.commit();
        return true;
    }


}
