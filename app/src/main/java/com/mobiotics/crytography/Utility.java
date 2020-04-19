package com.mobiotics.crytography;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class Utility {

    public static void hideKeyPad(Context mContext, View mView){
        InputMethodManager imm = (InputMethodManager)mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mView.getWindowToken(), 0);
    }
}
