package com.redoc.idu.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by limen on 2016/7/17.
 */
public class BitmapUtils {
    public static BitmapDrawable res2BitmapDrawable(Context context, int resId) throws IOException {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        InputStream is = context.getResources().openRawResource(resId);
        Bitmap bitmap = BitmapFactory.decodeStream(is,null, opt);
        is.close();
        return new BitmapDrawable(context.getResources(),bitmap);
    }
}
