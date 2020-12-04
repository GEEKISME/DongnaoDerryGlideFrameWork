package com.biotag.dongnaoderryglideframework.dongnaoglide.glide;

import android.app.Activity;
import android.app.FragmentManager;

import com.biotag.dongnaoderryglideframework.dongnaoglide.lifecycle.RequestManagerFragment;
import com.biotag.dongnaoderryglideframework.dongnaoglide.request.BitmapRequest;

import androidx.fragment.app.FragmentActivity;

public class DNGlide {
    public static BitmapRequest with(Activity activity){
        FragmentManager fm = activity.getFragmentManager();
        RequestManagerFragment requestManagerFragment = (RequestManagerFragment) fm.findFragmentByTag("com.dongnao.glide");
        if(requestManagerFragment == null){
            RequestManagerFragment current = new RequestManagerFragment();
            fm.beginTransaction().add(current,"com.dongnao.glid").commitAllowingStateLoss();
        }

        return new BitmapRequest(activity);
    }

    public static BitmapRequest with(FragmentActivity fragmentActivity){
        return new BitmapRequest(fragmentActivity);
    }
}
