package com.biotag.dongnaoderryglideframework.dongnaoglide.cache;

import android.content.Context;
import android.graphics.Bitmap;

import com.biotag.dongnaoderryglideframework.dongnaoglide.request.BitmapRequest;

public class DoubleLruCache implements BitmapCache {
    private static DoubleLruCache instance;
    //我们早Application的oncreate()方法中对DoubleLruCache进行了初始化
    //传的是Application的context
    public static DoubleLruCache getInstance(Context context){
        if(instance == null){
            synchronized (DoubleLruCache.class){
                if (instance == null){
                    instance = new DoubleLruCache(context);
                }
            }
        }
        return instance;
    }
    public static DoubleLruCache getInstance(){
        if(instance == null){
            return instance;
        }
        return instance;
    };
    private DiskBitmapCache diskBitmapCache;
    private MemoryLruCache lrucache;
    //这个构造方法的context是application的context，又分别传给了
    //diskBitmapCache与MemoryCache
    private DoubleLruCache(Context context){
        diskBitmapCache = DiskBitmapCache.getInstance(context);
        lrucache = MemoryLruCache.getInstance();
    }
    @Override
    public void put(BitmapRequest request, Bitmap bitmap) {
        lrucache.put(request,bitmap);
        diskBitmapCache.put(request,bitmap);
    }

    @Override
    public Bitmap get(BitmapRequest request) {
        Bitmap bitmap = lrucache.get(request);
        if(bitmap == null){
            bitmap = diskBitmapCache.get(request);
            lrucache.put(request,bitmap);
        }
        return bitmap;
    }

    @Override
    public void remove(BitmapRequest request) {
        lrucache.remove(request);
        diskBitmapCache.remove(request);
    }

    @Override
    public void remove(int activityCode) {
        lrucache.remove(activityCode);
        diskBitmapCache.remove(activityCode);
    }
}
