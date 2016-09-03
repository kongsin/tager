package com.example.kongsin.autogridlayoutmanager.Interface;

/**
 * Created by DroidDev on 8/16/16.
 */
public interface OnResponseListener<T> {
    void onSuccess(T res);
    void onFailed(String msg);
}
