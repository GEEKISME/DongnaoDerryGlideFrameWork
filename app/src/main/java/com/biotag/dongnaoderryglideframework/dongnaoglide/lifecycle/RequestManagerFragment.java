package com.biotag.dongnaoderryglideframework.dongnaoglide.lifecycle;

import android.app.Activity;
import android.app.Fragment;

public class RequestManagerFragment extends Fragment {
    private int activitycode;
    LifecycleObservable lifecycleObservable = LifecycleObservable.getInstance();
    //声明周期导出去
    @Override
    public void onStart() {
        super.onStart();
        lifecycleObservable.onStart(activitycode);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        /**
         * 我们把所绑定的activty 的hashcode 设置给变量activityCode,
         * 加入有一个activity被销毁了，就拿着这个activity的hashcode去查
         * 缓存表，如果是一致的那就将这个bitmap销毁掉
         */
        activitycode = activity.hashCode();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        lifecycleObservable.onDestroy(activitycode);
    }

}
