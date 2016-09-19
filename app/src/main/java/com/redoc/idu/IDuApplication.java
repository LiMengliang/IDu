package com.redoc.idu;

import android.app.Application;
import android.content.Context;

import com.redoc.idu.model.CategoryAndChannelManager;

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
     * Category and channel manager.
     */
    public static CategoryAndChannelManager CategoryAndChannelManager;

    /**
     * On create.
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Context = getApplicationContext();
        CategoryAndChannelManager = new CategoryAndChannelManager(Context);
        CategoryAndChannelManager.initialize();
    }
}
