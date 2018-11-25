package com.municipal.cmb.helper;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by RazR on 7/3/2018.
 */

public class FontChangeCrawler {
    private Typeface typeface;
    public FontChangeCrawler(Typeface typeface) {
        this.typeface = typeface;
    }

    public FontChangeCrawler(AssetManager assets, String fontPath){
        if(String.valueOf(fontPath).isEmpty()) return;
        fontPath = fontPath.contains(".ttf") ? fontPath : fontPath + ".ttf";
        this.typeface = Typeface.createFromAsset(assets, fontPath);
    }

    public void replaceFont(ViewGroup viewGroup) {
        View child;
        for(int i = 0; i < viewGroup.getChildCount(); i++) {
            child = viewGroup.getChildAt(i);
            if(child instanceof ViewGroup) {
                replaceFont((ViewGroup)child);
            } else if (child instanceof EditText) {
                ((EditText)child).setTypeface(typeface);
            } else if (child instanceof TextView) {
                ((TextView)child).setTypeface(typeface);
            }
        }
    }
}
