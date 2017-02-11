package com.redoc.idu.utils.activityTask;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

/**
 * Utilities for annalysing activity task.
 * Created by limen on 2017/2/11.
 */

public class TaskUtilities {
    /**
     * Get tasks of the application
     * @param context Application context
     * @return A list of tasks.
     */
    public static List<ActivityManager.AppTask> getApplicationTasks(Context context) {
        ActivityManager activityManager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        return activityManager.getAppTasks();
    }
}
