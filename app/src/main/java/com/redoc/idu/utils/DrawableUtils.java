package com.redoc.idu.utils;

import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;

import com.redoc.idu.IDuApplication;

/**
 * Created by meli on 2016/11/15.
 */

public class DrawableUtils {
    public static ColorDrawable colorIdToDrawable(int colorResourceId) {
        return new ColorDrawable(ContextCompat.getColor(IDuApplication.Context, colorResourceId));
    }
}
