package com.ifspace.advertising;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

/**
 * @author jiangxusheng
 */
public class AdsApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }
}
