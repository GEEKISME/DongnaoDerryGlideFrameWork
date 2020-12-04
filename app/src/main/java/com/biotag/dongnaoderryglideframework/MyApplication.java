package com.biotag.dongnaoderryglideframework;

import android.app.Application;

import com.biotag.dongnaoderryglideframework.dongnaoglide.cache.DoubleLruCache;

public class MyApplication extends Application {
    private static MyApplication instance;

    public static MyApplication getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        DoubleLruCache.getInstance(this);
    }
}
