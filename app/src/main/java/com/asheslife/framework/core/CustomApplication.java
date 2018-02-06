package com.asheslife.framework.core;

import android.app.Application;

/**
 * Created by asheslife on 2016/3/27.
 *
 */
public class CustomApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppStatusTracker.init(this);
    }
}
