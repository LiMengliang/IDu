package com.redoc.idu;

import android.app.Application;
import android.content.Context;

import com.redoc.idu.model.CategoryAndChannelManager;
import com.redoc.idu.utils.html.JsoupParser;
import com.redoc.idu.utils.network.HttpClient;

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

    public static HttpClient HttpClient;
    public static JsoupParser JsoupParser;

    /**
     * On create.
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Context = getApplicationContext();
        CategoryAndChannelManager = new CategoryAndChannelManager(Context);
        CategoryAndChannelManager.initialize();
        HttpClient = new HttpClient(Context);
    }
}
