package com.example.tager;

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
/**
 * Created by kognsin on 9/2/2016.
 */

public class Tager {

    private static Tager tager;
    private static final String TAG = "Tager";
    private TagerCallback tagerCallback;

    public static Tager getInstance(){
        if (tager == null) tager = new Tager();
        return tager;
    }

    public <T> T subscribeView(T viewHolder){
        ((RecyclerView.ViewHolder)viewHolder).itemView.setTag(viewHolder);
        T t = viewHolder;
        return t;
    }

    public void sendCallback(RecyclerView.ViewHolder rootView,@IdRes int position){
        tager.tagerCallback.onReceived(position, rootView);
    }

    public <T> T getActualView(RecyclerView.ViewHolder view){
        T t = (T) view.itemView.findViewWithTag(view).getTag();
       return t;
    }

    public void setCallBack(TagerCallback tagerCallback){
        tager.tagerCallback = tagerCallback;
    }
}
