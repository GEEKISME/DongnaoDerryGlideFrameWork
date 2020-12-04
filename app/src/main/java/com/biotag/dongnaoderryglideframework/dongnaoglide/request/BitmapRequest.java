package com.biotag.dongnaoderryglideframework.dongnaoglide.request;

import android.content.Context;
import android.widget.ImageView;

import com.biotag.dongnaoderryglideframework.dongnaoglide.utils.MD5Utils;
import com.biotag.dongnaoderryglideframework.dongnaoglide.core.RequestManager;
import com.biotag.dongnaoderryglideframework.dongnaoglide.listener.RequestListener;

import java.lang.ref.SoftReference;

public class BitmapRequest {
    private String url;
    private SoftReference<ImageView> softimageview;
    private String urlMd5;
    private int loadingResId;
    private Context context;
    private RequestListener requestListener;


    //设置流式布局
    public BitmapRequest (Context context){
        this.context = context;
    }

    public BitmapRequest loading(int loadingResId){
        this.loadingResId = loadingResId;
        return this;
    }

    public BitmapRequest listener(RequestListener requestListener){
        this.requestListener = requestListener;
        return this;
    }

    public BitmapRequest load(String url){
        this.url = url;
        this.urlMd5 = MD5Utils.toMD5(url);
        return this;
    }

    public void into(ImageView imageView){
        this.softimageview = new SoftReference<>(imageView);
        imageView.setTag(urlMd5);
        RequestManager.getInstance().addBitmapRequest(this);
    }



    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.urlMd5 = MD5Utils.toMD5(url);
        this.url = url;
    }

    public ImageView getimageview() {
        return softimageview.get();
    }

    public void setSoftimageview(SoftReference<ImageView> softimageview) {
        this.softimageview = softimageview;
    }

    public String getUrlMd5() {
        return urlMd5;
    }

    public void setUrlMd5(String urlMd5) {
        this.urlMd5 = urlMd5;
    }

    public int getLoadingResId() {
        return loadingResId;
    }

    public void setLoadingResId(int loadingResId) {
        this.loadingResId = loadingResId;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public RequestListener getRequestListener() {
        return requestListener;
    }

    public void setRequestListener(RequestListener requestListener) {
        this.requestListener = requestListener;
    }
}
