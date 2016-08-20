package com.redoc.idu;

import android.app.Application;
import android.content.Context;

/**
 * IDu application.
 *
 * Created by limen on 2016/7/17.
 */
public class IDuApplication extends Application {

    /**
     * Application context.
     */
    public static Context Context;

    /**
     * On create.
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Context = getApplicationContext();
    }
}
