package com.redoc.idu.utils.layout;

import android.content.Context;
import android.graphics.Point;
import android.view.WindowManager;


/**
 * Created by Mengliang Li on 1/12/2017.
 */

public class LayoutUtils {

    /**
     * Get screen size in pixel.
     * @param context Context.
     * @return Screen size in pixel
     */
    public static Point getScreenSizeInPixel(Context context) {
        WindowManager windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        Point point = new Point();
        windowManager.getDefaultDisplay().getSize(point);
        return point;
    }

    /**
     * Get screen size in dip.
     * @param context Context.
     * @return Screen size in dip.
     */
    public static Point getScreenSizeInDp(Context context) {
        Point screenSize = getScreenSizeInPixel(context);
        return new Point(px2Dp(context, screenSize.x), px2Dp(context, screenSize.y));
    }

    /**
     * Get pixel value of n percent of screen width.
     * @param context Context.
     * @param percent Percent of width.
     * @return Dip value of width.
     */
    public static int getNPercentOfScreenWidthInPixel(Context context, float percent) {
        return (int)(getScreenSizeInPixel(context).x * percent + 0.5f);
    }

    /**
     * Get pixel value of n percent of screen height.
     * @param context Context.
     * @param percent Percent of height.
     * @return Dip value of percent.
     */
    public static int getNPercentOfScreenHeightInPixel(Context context, float percent) {
        return (int)(getScreenSizeInPixel(context).y * percent + 0.5f);
    }

    /**
     * Convert pix value to dip value.
     * @param context Context.
     * @param px Pix value.
     * @return Dip value.
     */
    public static int px2Dp(Context context, float px) {
        float m = context.getResources().getDisplayMetrics().density;
        return (int)(px / m + 0.5f) ;
    }

    /**
     * Convert dip value to pix value.
     * @param context Context.
     * @param dp Dip value.
     * @return Dip value.
     */
    public static int dp2Px(Context context, float dp) {
        float m = context.getResources().getDisplayMetrics().density;
        return (int)(dp * m + 0.5f);
    }
}
