package com.application.tedallal_app.Scenarios.ScenarioHome.Controller;

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
import com.application.tedallal_app.R;
import com.application.tedallal_app.Utils.TinyDB;

import java.util.Objects;

import es.dmoral.toasty.Toasty;

public class Terms_Popup {

    Context context;
    Dialog dialog;
    TextView txtdismiss;
    int x = 0;
    TinyDB tinyDB;
    LinearLayout loading;
    Button btnagree;
    CheckBox checkBox;


    public void dialog(final Context context, int resource, double widthh,CheckBox checkBox) {
        this.context = context;
        this.checkBox = checkBox;
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
        tinyDB = new TinyDB(context);
        loading = dialog.findViewById(R.id.loading);
        btnagree = dialog.findViewById(R.id.btnAgree);







        txtdismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();

            }
        });


        btnagree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkBox.setChecked(true);
                dialog.dismiss();


            }
        });

        dialog.show();
    }


}
