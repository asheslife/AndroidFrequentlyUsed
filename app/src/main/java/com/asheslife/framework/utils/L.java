package com.asheslife.framework.utils;

import android.util.Log;

import com.asheslife.framework.BuildConfig;

/**
 * @author asheslife
 */
public class L {
    private static final String TAG = "asheslife";
    public static boolean DEBUG = BuildConfig.DEBUG;

    public static void d(String msg) {
        if (DEBUG){
            Log.d(TAG, msg);
        }
    }

    public static void e(String msg) {
        if (DEBUG){
            Log.e(TAG, msg);
        }
    }
}
