package com.biotag.dongnaoderryglideframework.dongnaoglide.lifecycle;

import com.biotag.dongnaoderryglideframework.dongnaoglide.cache.DoubleLruCache;

public class LifecycleObservable {
    private static volatile  LifecycleObservable instance;
    public static LifecycleObservable getInstance(){
        if(instance ==  null){
            synchronized (LifecycleObservable.class){
                if(instance == null){
                    instance = new LifecycleObservable();
                }
            }
        }
        return instance;
    }

    /**
     * 是activity的hashcode ,如果直接传activity，那么我们当前的fragment就
     * 会对activity产生引用，可能导致内存泄漏
     * @param activityCode
     */
    public void onStart(int activityCode){

    }
    public void onStop(int activtyCode){

    }
    public void onDestroy(int activityCode){
        DoubleLruCache.getInstance().remove(activityCode);
    }
}
