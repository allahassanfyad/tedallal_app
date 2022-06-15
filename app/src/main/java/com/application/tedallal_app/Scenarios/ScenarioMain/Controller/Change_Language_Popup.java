package com.application.tedallal_app.Scenarios.ScenarioMain.Controller;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.application.tedallal_app.NetworkLayer.NetworkInterface;
import com.application.tedallal_app.NetworkLayer.ResponseModel;
import com.application.tedallal_app.R;
import com.application.tedallal_app.Utils.TinyDB;
import com.application.tedallal_app.local_data.saved_data;
import com.google.gson.Gson;

import java.util.Objects;

import es.dmoral.toasty.Toasty;

public class Change_Language_Popup implements NetworkInterface {

    Context context;
    Dialog dialog;
    TextView txtdismiss;
    CheckBox checkBoxarabic, checkBoxenglish;
    int x = 0;
    TinyDB tinyDB;
    LinearLayout loading;
    Button btnconfirm;
    String language ="";


    public void dialog(final Context context, int resource, double widthh) {
        this.context = context;
        dialog = new Dialog(context);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(resource);
        int width = (int) (context.getResources().getDisplayMetrics().widthPixels * widthh);
        int height = android.view.WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setLayout(width, height);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation1;
        txtdismiss = dialog.findViewById(R.id.txtDismiss);
        checkBoxenglish = dialog.findViewById(R.id.checkPersonalEnglish);
        checkBoxarabic = dialog.findViewById(R.id.checkPersonalArabic);
        tinyDB = new TinyDB(context);
        loading = dialog.findViewById(R.id.loading);
        btnconfirm = dialog.findViewById(R.id.btnConfirm);

        OnclickMethod();

        if (saved_data.get_lang_num(context).equals("ar")) {


            checkBoxarabic.setChecked(true);
            checkBoxenglish.setChecked(false);

        } else if (saved_data.get_lang_num(context).equals("en")) {


            checkBoxarabic.setChecked(false);
            checkBoxenglish.setChecked(true);

        }


        txtdismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();

            }
        });


        btnconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (language.equals("")){


                    Toasty.error(Objects.requireNonNull(context), R.string.please_choose_your_language, Toast.LENGTH_LONG).show();

                }else {

                    MainActivity.changeLang(context,language);
                    dialog.dismiss();

                }


//                loading.setVisibility(View.VISIBLE);
//                new Apicalls(context, Change_Gender_Popup.this).change_Gender(String.valueOf(x));

            }
        });

        dialog.show();
    }


    private void OnclickMethod() {

        checkBoxarabic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                language = "ar";
                checkBoxarabic.setChecked(true);
                checkBoxenglish.setChecked(false);
            }
        });
        checkBoxenglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                language = "en";
                checkBoxarabic.setChecked(false);
                checkBoxenglish.setChecked(true);

            }
        });

    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {
        loading.setVisibility(View.GONE);

//        Gson gson = new Gson();
//        ModelGenderChange genderChange = gson.fromJson(String.valueOf(model.getJsonObject()),ModelGenderChange.class);
//        ModelGenderDatum genderDatum = genderChange.getData();
//
//        Toasty.success(context, "تم تغيير النوع بنجاح ", Toast.LENGTH_LONG).show();
//
//        dialog.dismiss();
    }

    @Override
    public void OnError(VolleyError error) {
        loading.setVisibility(View.GONE);
        Log.e("error", error.toString());

    }
}
