package com.department.assessment.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.snackbar.Snackbar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HelperClass {

    Context context;

    public HelperClass(Activity context) {
        this.context = context;
    }



    public static void showSnackbarwithMsg(Activity context,String msg) {
        Snackbar.make(context.findViewById(android.R.id.content), msg, Snackbar.LENGTH_SHORT).show();
    }

    public static void showErrorSnackbar(Activity context) {

        Snackbar.make(context.findViewById(android.R.id.content), "Failed to connect, please try again", Snackbar.LENGTH_SHORT).show();


    }



}
