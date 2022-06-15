package com.application.tedallal_app.Utils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;


@SuppressLint("AppCompatCustomView")
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class TextViewWithFontLight extends TextView {
    public static Typeface tf;

    public TextViewWithFontLight(Context context) {
        super(context);
        init();
    }

    public TextViewWithFontLight(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TextViewWithFontLight(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    public TextViewWithFontLight(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            tf = Typeface.createFromAsset(getResources().getAssets(), "fonts/tajawal_light.ttf");
            setTypeface(tf);
        }
    }



}
