package com.biotag.dongnaoderryglideframework.dongnaoglide.cache;

import android.graphics.Bitmap;

import com.biotag.dongnaoderryglideframework.dongnaoglide.request.BitmapRequest;

public interface BitmapCache {
    /**
     * 存入内存
     * @param request
     * @param bitmap
     */
    void put(BitmapRequest request, Bitmap bitmap);

    /**
     * 读取缓存的图片
     * @param request
     * @return
     */
    Bitmap get(BitmapRequest request);

    /**
     * 清楚缓存的图片
     * @param request
     */
    void remove(BitmapRequest request);

    /**
     * 清楚所属activity的bitmap
     * @param activity
     */
    void remove(int activity);
}
