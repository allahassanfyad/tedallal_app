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
public class TextViewWithFontBlack extends TextView {
    public static Typeface tf;

    public TextViewWithFontBlack(Context context) {
        super(context);
        init();
    }

    public TextViewWithFontBlack(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TextViewWithFontBlack(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    public TextViewWithFontBlack(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            tf = Typeface.createFromAsset(getResources().getAssets(), "fonts/tajawal_black.ttf");
            setTypeface(tf);
        }
    }



}
