package com.redoc.idu.utils.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

import com.android.volley.Cache;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Bitmap utilities.
 *
 * Created by limen on 2016/7/17.
 */
public class BitmapUtils {
    /**
     * Convert drawing resource to bitmap drawable.
     * @param context Context.
     * @param resId Resource id.
     * @return Bitmap drawable.
     * @throws IOException IO exception.
     */
    public static BitmapDrawable res2BitmapDrawable(Context context, int resId) throws IOException {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        InputStream is = context.getResources().openRawResource(resId);
        Bitmap bitmap = BitmapFactory.decodeStream(is,null, opt);
        is.close();
        return new BitmapDrawable(context.getResources(),bitmap);
    }

    /**
     * Convert bitmap to byte array.
     * @param bitmap bitmap
     * @return A byte array.
     */
    public static byte[] bitmap2Bytes(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    /**
     * Resample bitmap.
     * @param bitmap Bitmap.
     * @param ratio Resample ratio.
     * @return Get resampled bitmap.
     */
    public static Bitmap resampleBitmap(Bitmap bitmap, float ratio) {
        Matrix matrix = new Matrix();
        matrix.postScale(ratio, ratio); //长和宽放大缩小的比例
        Bitmap resizeBmp = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
        return resizeBmp;
    }

    /**
     * blur a bitmap with gause algorithm.
     * @param bitmap The bitmap.
     * @param context Context
     * @param blurDepth depth of blure, should between 0 and 25.
     * @return Blured bitmap.
     */
    public static Bitmap getBlurBitmap(Bitmap bitmap, Context context, float blurDepth) {

        Bitmap resampledBitmap = resampleBitmap(bitmap, 0.2f);
        //Let's create an empty bitmap with the same size of the bitmap we want to blur
        Bitmap outBitmap = Bitmap.createBitmap(resampledBitmap.getWidth(), resampledBitmap.getHeight(), Bitmap.Config.ARGB_8888);

        //Instantiate a new Renderscript
        RenderScript rs = RenderScript.create(context);

        //Create an Intrinsic Blur Script using the Renderscript
        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));

        //Create the Allocations (in/out) with the Renderscript and the in/out bitmaps
        Allocation allIn = Allocation.createFromBitmap(rs, resampledBitmap);
        Allocation allOut = Allocation.createFromBitmap(rs, outBitmap);

        //Set the radius of the blur: 0 < radius <= 25
        blurScript.setRadius(blurDepth);

        //Perform the Renderscript
        blurScript.setInput(allIn);
        blurScript.forEach(allOut);

        //Copy the final bitmap created by the out Allocation to the outBitmap
        allOut.copyTo(outBitmap);

        //After finishing everything, we destroy the Renderscript.
        rs.destroy();

        return outBitmap;
    }
}
