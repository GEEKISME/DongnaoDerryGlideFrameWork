package com.biotag.dongnaoderryglideframework.dongnaoglide.core;

import android.util.Log;

import com.biotag.dongnaoderryglideframework.dongnaoglide.request.BitmapRequest;

import java.util.concurrent.LinkedBlockingQueue;

public class RequestManager {
    private static RequestManager instance;
    private LinkedBlockingQueue<BitmapRequest> requestsQueue = new LinkedBlockingQueue<>();
    //转发器管理 打饭阿姨集合
    private BitmapDispatcher[] dispatchers;
    private RequestManager(){
        createDispatcherAndStart();
    }

    public static RequestManager getInstance() {
        if (instance == null){
            synchronized (RequestManager.class){
                if(instance ==null){
                    instance = new RequestManager();
                }
            }
        }
        return instance;
    }

    //食堂的入口
    public void addBitmapRequest(BitmapRequest request){
        if(!requestsQueue.contains(request)){
            requestsQueue.add(request);
        }else {
            Log.i("tmsk", "任务已经存在，不用再次添加");
        }
    }
    private void createDispatcherAndStart(){
        int threadcount = Runtime.getRuntime().availableProcessors();
        dispatchers = new BitmapDispatcher[threadcount];
        for (int i = 0; i < threadcount; i++) {
            BitmapDispatcher bs = new BitmapDispatcher(requestsQueue);
            bs.start();
            dispatchers[i] = bs;
        }
    }
}
