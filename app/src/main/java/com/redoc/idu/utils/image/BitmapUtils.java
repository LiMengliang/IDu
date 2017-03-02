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
 * http://www.codeceo.com/article/android-bitmap-tips.html for more details
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

    public static Bitmap getSubBitmap(Bitmap bitmap, Rect rect) {
        Bitmap clipBitmap = Bitmap.createBitmap(bitmap,
                rect.left, rect.top,
                rect.width(), rect.height());
        return clipBitmap;
    }


    /**
     * Resample bitmap.
     * 这个方法有两个缺陷：
     * 1、我们先要从磁盘上先将图片加载到内存，然后才能对图片进行缩放，在移动设备上对内存的要求比较高，这在一定程度上降级了性能。
     * 2、我们使用Bitmap.createBitmap这个方法进行缩放，使用的是Java层面的方法来缩放，我们知道Java层面对图片，视频等进行处理是有性能损失的。
     * @param bitmap Bitmap.
     * @param scale Resample ratio.
     * @return Get resampled bitmap.
     */
    public static Bitmap scaleBitmap(Bitmap bitmap, float scale) {
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale); //长和宽放大缩小的比例
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

        Bitmap resampledBitmap = bitmap;//resampleBitmap(bitmap, 0.1f);
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

    public static Bitmap getThumbnail(String path, int maxWidth, int maxHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        // 获取这个图片的宽和高信息到options中, 此时返回bm为空
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
        options.inJustDecodeBounds = false;
        // 计算缩放比
        int sampleSize = sampleSize(options, maxWidth, maxHeight);
        options.inSampleSize = sampleSize;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inPurgeable = true;
        options.inInputShareable = true;
        bitmap = BitmapFactory.decodeFile(path, options);
        return bitmap;
    }

    public static int sampleSize(BitmapFactory.Options options, int maxWidth, int maxHeight) {
        // raw height and width of image
        int rawWidth = options.outWidth;
        int rawHeight = options.outHeight;

        // calculate best sample size
        int inSampleSize = 0;
        if (rawHeight > maxHeight || rawWidth > maxWidth) {
            float ratioWidth = (float) rawWidth / maxWidth;
            float ratioHeight = (float) rawHeight / maxHeight;
            inSampleSize = (int) Math.min(ratioHeight, ratioWidth);
        }
        inSampleSize = Math.max(1, inSampleSize);

        return inSampleSize;
    }

    /**
     * Decode thumb nail for a bitmap.
     * @param path Path of the bitmap
     * @param viewWidth Expected width
     * @param viewHeight Expected height.
     * @return
     */
    public static Bitmap decodeThumbBitmapForFile(String path, int viewWidth, int viewHeight){
        BitmapFactory.Options options = new BitmapFactory.Options();
        //设置为true,表示解析Bitmap对象，该对象不占内存
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        //设置缩放比例
        options.inSampleSize = computeScale(options, viewWidth, viewHeight);
        //设置为false,解析Bitmap对象加入到内存中
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path, options);
    }

    /**
     * Compute scale.
      * @param options The option of original image.
     * @param viewWidth Expected view width.
     * @param viewHeight Expected view height.
     * @return Scale.
     */
    private static int computeScale(BitmapFactory.Options options, int viewWidth, int viewHeight){
        int inSampleSize = 1;
        if(viewWidth == 0 || viewWidth == 0){
            return inSampleSize;
        }
        int bitmapWidth = options.outWidth;
        int bitmapHeight = options.outHeight;

        //假如Bitmap的宽度或高度大于我们设定图片的View的宽高，则计算缩放比例
        if(bitmapWidth > viewWidth || bitmapHeight > viewHeight){
            int widthScale = Math.round((float) bitmapWidth / (float) viewWidth);
            int heightScale = Math.round((float) bitmapHeight / (float) viewWidth);
            //为了保证图片不缩放变形，我们取宽高比例最小的那个
            inSampleSize = widthScale < heightScale ? widthScale : heightScale;
        }
        return inSampleSize;
    }
}
