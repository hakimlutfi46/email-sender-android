package com.example.sendemailtest;

import android.content.Context;

import com.shashank.sony.fancytoastlib.FancyToast;

public class MyToast {

    public static void showToastDefault(Context context, String message) {
        FancyToast.makeText(context, message, FancyToast.LENGTH_SHORT, FancyToast.DEFAULT,false).show();
    }

    public static void showToastSuccess(Context context, String message) {
        FancyToast.makeText(context, message, FancyToast.LENGTH_SHORT, FancyToast.SUCCESS,false).show();
    }

    public static void showToastError(Context context, String message) {
        FancyToast.makeText(context, message, FancyToast.LENGTH_SHORT, FancyToast.ERROR,false).show();
    }

    public static void showToastWarning(Context context, String message) {
        FancyToast.makeText(context, message, FancyToast.LENGTH_SHORT, FancyToast.WARNING,false).show();
    }

    public static void showToastInfo(Context context, String message) {
        FancyToast.makeText(context, message, FancyToast.LENGTH_SHORT, FancyToast.INFO,false).show();
    }

    public static void showToastConfusing(Context context, String message) {
        FancyToast.makeText(context, message, FancyToast.LENGTH_SHORT, FancyToast.CONFUSING,false).show();
    }
}
