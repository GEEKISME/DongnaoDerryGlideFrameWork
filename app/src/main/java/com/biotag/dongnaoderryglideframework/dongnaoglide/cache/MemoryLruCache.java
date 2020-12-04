package com.biotag.dongnaoderryglideframework.dongnaoglide.cache;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.biotag.dongnaoderryglideframework.dongnaoglide.request.BitmapRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MemoryLruCache implements BitmapCache {
    //这个activityMap以图片的Url为key，以这张图片所属的activity的actiivtyCode为
    //value，那么当某个activity被销毁时，有这个map中的value找到对应的key也就是bitmap对象
    //然后将这些对象销毁
    private HashMap<String, Integer> activityCache;
    private LruCache<String, Bitmap> lruCache;
    private static volatile MemoryLruCache instance;
    private static final byte[] lock = new byte[0];
    public static MemoryLruCache getInstance(){
        if(instance == null){
            synchronized (lock){
                if(instance == null){
                    instance = new MemoryLruCache();
                }
            }
        }
        return instance;
    }
    private MemoryLruCache(){
        int maxMemorySize = 1024*1024*1025;
        lruCache = new LruCache<String, Bitmap>(maxMemorySize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //一张图片的大小
                return value.getRowBytes()*value.getHeight();
            }
        };
        activityCache = new HashMap<>();
    }

    @Override
    public void put(BitmapRequest request, Bitmap bitmap) {
        if(bitmap!=null){
            lruCache.put(request.getUrlMd5(), bitmap);
            activityCache.put(request.getUrlMd5(),request.getContext().hashCode());
        }
    }

    @Override
    public Bitmap get(BitmapRequest request) {
        return lruCache.get(request.getUrlMd5());
    }

    @Override
    public void remove(BitmapRequest request) {
        lruCache.remove(request.getUrlMd5());
    }

    //只有activity被销毁了
    @Override
    public void remove(int activityCode) {
        //查找有多少个MD5的value是activityCode
        List<String> tempUrlMd5List = new ArrayList<>();
        for (String urlMd5:activityCache.keySet()) {
            if(activityCache.get(urlMd5).intValue() == activityCode){
                tempUrlMd5List.add(urlMd5);
            }
        }
        //回收内存
        for (String urlMd5:tempUrlMd5List) {
            activityCache.remove(urlMd5);
            Bitmap bitmap = lruCache.get(urlMd5);
            if(bitmap!=null && !bitmap.isRecycled()){
                bitmap.recycle();
            }
            bitmap = null;
        }
        if (!tempUrlMd5List.isEmpty()){
            System.gc();
        }
    }

}
