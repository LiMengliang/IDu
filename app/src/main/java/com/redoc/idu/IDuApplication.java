package com.redoc.idu;

import android.app.Application;
import android.content.Context;

/**
 * Created by limen on 2016/7/17.
 */
public class IDuApplication extends Application {

    public static Context Context;

    @Override
    public void onCreate() {
        super.onCreate();
        Context = getApplicationContext();
    }
}
