package com.application.tedallal_app.Scenarios.ScenarioHome.Controller;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.MainActivity;
import com.application.tedallal_app.Scenarios.ScenarioMain.Controller.ScenariosAlshalChat.Controller.AlshalMainActivity;


public class login_dialog {
    public void dialog(final Context context, int resource, double widthh) {
        final Dialog dialog = new Dialog(context);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(resource);
        dialog.setCanceledOnTouchOutside(false);
        int width = (int) (context.getResources().getDisplayMetrics().widthPixels * widthh);
        int height = android.view.WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setLayout(width, height);
        dialog.show();


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

//                String id = saved_data.get_user_id(context);
//                Intent intent = new Intent(context, MainActivity.class);
//                intent.putExtra("id", id);
//                context.startActivity(intent);
//                ((Activity) context).finish();

                if (SignIn.login == 1) {

                    dialog.dismiss();
                    ((AppCompatActivity) context).finish();
                    ((AppCompatActivity) context).startActivity(new Intent(context, AlshalMainActivity.class));

                } else {

                    dialog.dismiss();
                    ((AppCompatActivity) context).finish();
                    ((AppCompatActivity) context).startActivity(new Intent(context, MainActivity.class));

                }


            }
        }, 3000);


    }
}
