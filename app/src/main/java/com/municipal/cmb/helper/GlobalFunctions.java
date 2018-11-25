package com.municipal.cmb.helper;

import android.content.Context;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A global class that contains all the global functions to be used from anywhere in the project
 */

public class GlobalFunctions {
    private Context context;

    public GlobalFunctions(Context context) {
        this.context = context;
    }

    public boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null;
    }

    public void setCustomFont(String fontName, EditText view) {
        //setting custom font to the button
        if(view != null) {
            if(String.valueOf(fontName).isEmpty()) return;
            fontName = fontName.toLowerCase(); //ensure that it is in lowercase
            String format = fontName.contains(".ttf") || fontName.endsWith(".ttf") ? "" : ".ttf"; //the prefix of the path
            String path = "fonts/" + fontName + format;
            Typeface tf = Typeface.createFromAsset(context.getAssets(), path);
            view.setTypeface(tf);
        } else {
            Log.d("CustomFont", "The supplied view is null");
        }
    }

    public void setCustomFont(String fontName, Button view) {
        //setting custom font to the button
        if(view != null) {
            if(String.valueOf(fontName).isEmpty()) return;
            fontName = fontName.toLowerCase(); //ensure that it is in lowercase
            String format = fontName.contains(".ttf") || fontName.endsWith(".ttf") ? "" : ".ttf"; //the prefix of the path
            String path = "fonts/" + fontName + format;
            Typeface tf = Typeface.createFromAsset(context.getAssets(), path);
            view.setTypeface(tf);
        } else {
            Log.d("CustomFont", "The supplied view is null");
        }
    }

    public void setCustomFont(String fontName, TextView view) {
        //setting custom font to the button
        if(view != null) {
            if(String.valueOf(fontName).isEmpty()) return;
            fontName = fontName.toLowerCase(); //ensure that it is in lowercase
            String format = fontName.contains(".ttf") || fontName.endsWith(".ttf") ? "" : ".ttf"; //the prefix of the path
            String path = "fonts/" + fontName + format;
            Typeface tf = Typeface.createFromAsset(context.getAssets(), path);
            view.setTypeface(tf);
        } else {
            Log.d("CustomFont", "The supplied view is null");
        }
    }


    public Typeface getCustomTypeFace(String fontName) {
        if(String.valueOf(fontName).isEmpty()) return null;
        fontName = fontName.toLowerCase(); //ensure that it is in lowercase
        String format = fontName.contains(".ttf") || fontName.endsWith(".ttf") ? "" : ".ttf"; //the prefix of the path
        String path = "fonts/" + fontName + format;
        return Typeface.createFromAsset(context.getAssets(), path);
    }
}
