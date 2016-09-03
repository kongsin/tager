package com.example.tager;

/**
 * Created by kognsin on 9/2/2016.
 */

public interface TagerCallback<T> {
    void onReceived(int position, T viewHolder);
}
