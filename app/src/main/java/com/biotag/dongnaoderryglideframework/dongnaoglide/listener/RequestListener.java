package com.biotag.dongnaoderryglideframework.dongnaoglide.listener;

import android.graphics.Bitmap;

public interface RequestListener {
    void onSuccess(Bitmap bitmap);
    void onFaile();
}
