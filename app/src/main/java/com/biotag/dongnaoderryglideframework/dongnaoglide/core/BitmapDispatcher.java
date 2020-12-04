package com.biotag.dongnaoderryglideframework.dongnaoglide.core;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import com.biotag.dongnaoderryglideframework.MyApplication;
import com.biotag.dongnaoderryglideframework.dongnaoglide.cache.DoubleLruCache;
import com.biotag.dongnaoderryglideframework.dongnaoglide.request.BitmapRequest;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.BlockingQueue;

//打饭的阿姨
public class BitmapDispatcher extends Thread {
    //二级缓存(其中已经包含内存缓存与硬盘缓存)
    private DoubleLruCache doubleLruCache = DoubleLruCache.getInstance(MyApplication.getInstance());
    //队列，阻塞队列
    private BlockingQueue<BitmapRequest> requestQueue;
    //相关的UI操作需要借助这个主线程的handler
    private Handler mHandler = new Handler(Looper.myLooper());

    public BitmapDispatcher(BlockingQueue<BitmapRequest> requestQueue) {
        this.requestQueue = requestQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                //从队列中获取request
                BitmapRequest br = requestQueue.take();
                //显示占位符
                showLoadingImage(br);
                //
                Bitmap bitmap = findBitmap(br);
                //将网络下载的图片显示到imageview
                deliverUIThread(br, bitmap);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }

    private Bitmap findBitmap(BitmapRequest request) {
        Bitmap bitmap = null;
        //内存
        //硬盘
        bitmap = doubleLruCache.get(request);

        //网络
        if (bitmap != null) {
            return bitmap;
        }
        //去网络加载图片
        bitmap = downloadFromInternet(request);
        if(bitmap!=null){
            doubleLruCache.put(request,bitmap);
        }
        return bitmap;
    }

    private void deliverUIThread(BitmapRequest br, Bitmap bitmap) {
        ImageView imageView = br.getimageview();
        if (imageView != null && bitmap != null && imageView.getTag().equals(br.getUrlMd5())) {
            mHandler.post(() -> {
                imageView.setImageBitmap(bitmap);
            });
        }

        /**
         * 调用用户传进来的回调接口
         *
         */
        if(br.getRequestListener()!=null){
            if(bitmap!=null){
                mHandler.post(()->{
                    br.getRequestListener().onSuccess(bitmap);
                });
            }else {
                mHandler.post(()->{
                    br.getRequestListener().onFaile();
                });
            }
        }
    }

    private Bitmap downloadFromInternet(BitmapRequest br) {
        FileOutputStream fos = null;
        InputStream is = null;
        Bitmap bitmap = null;
        try {
            URL url = new URL(br.getUrl());
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            is = urlConnection.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }

    private void showLoadingImage(BitmapRequest br) {
        int resId = br.getLoadingResId();
        if (resId > 0) {
            ImageView iv = br.getimageview();
            if (iv != null) {
                mHandler.post(() -> {
                    iv.setImageResource(resId);
                });
            }
        }
    }
}
