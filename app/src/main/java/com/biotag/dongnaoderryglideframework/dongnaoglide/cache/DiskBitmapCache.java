package com.biotag.dongnaoderryglideframework.dongnaoglide.cache;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.biotag.dongnaoderryglideframework.dongnaoglide.cache.disk.DiskLruCache;
import com.biotag.dongnaoderryglideframework.dongnaoglide.cache.disk.Util;
import com.biotag.dongnaoderryglideframework.dongnaoglide.request.BitmapRequest;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DiskBitmapCache implements BitmapCache {
    private DiskLruCache diskLruCache;
    private static volatile DiskBitmapCache instance;
    private String imageCachePath = "Image";
    private static final byte[] lock = new byte[0];
    private int MB = 1024 * 1024;
    private int maxDiskSize = 50* MB;

    private DiskBitmapCache(Context context){
        File cachefile = getImageCacheFile(context,imageCachePath);
        if(!cachefile.exists()){
            cachefile.mkdirs();
        }
        try {
            diskLruCache = DiskLruCache.open(cachefile, getAppVersion(context), 1, maxDiskSize);
        }catch (IOException e){
            e.printStackTrace();
        }
    }




    public static DiskBitmapCache getInstance(Context context){
        if (instance == null){
            synchronized (lock){
                if (instance == null){
                    instance = new DiskBitmapCache(context);
                }
            }
        }
        return instance;
    }

    private int getAppVersion(Context context) {
        PackageManager manager = context.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
        }

        return 1;
    }

    private File getImageCacheFile(Context context, String imageCachePath) {
        String path;
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
            path = context.getExternalCacheDir().getPath();
        }else {
            path = context.getCacheDir().getPath();
        }
       return new File(path+File.separator+imageCachePath);
    }

    @Override
    public void put(BitmapRequest request, Bitmap bitmap) {
       DiskLruCache.Editor editor;
        OutputStream outputStream = null;
        try {
            editor = diskLruCache.edit(request.getUrlMd5());
            outputStream = editor.newOutputStream(0);
            if(pressBitmap2Disk(outputStream,bitmap)){
                editor.commit();
            }else {
                editor.abort();
            }
        }catch (IOException  e){
            e.printStackTrace();
        }finally {
            Util.closeQuietly(outputStream);
        }
    }

    private boolean pressBitmap2Disk(OutputStream outputStream, Bitmap bitmap) {
        BufferedOutputStream bufferedOutputStream = null;
        try {
            bufferedOutputStream = new BufferedOutputStream(outputStream);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,bufferedOutputStream);
            bufferedOutputStream.flush();
            return true;
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            Util.closeQuietly(bufferedOutputStream);
        }
        return false;
    }

    @Override
    public Bitmap get(BitmapRequest request) {
        InputStream stram = null;
        DiskLruCache.Snapshot snapshot = null;
        try {
            snapshot = diskLruCache.get(request.getUrlMd5());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(snapshot!=null){
            stram = snapshot.getInputStream(0);
            return BitmapFactory.decodeStream(stram);
        }
        return null;
    }

    @Override
    public void remove(BitmapRequest request) {
        try {
            diskLruCache.remove(request.getUrlMd5());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void remove(int activity) {

    }
}
