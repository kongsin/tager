package com.example.tager;

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by kognsin on 9/2/2016.
 */

public class Tager {

    private static Tager mTager;
    private static final String TAG = "Tager";
    private ArrayList<TagerCallback> mTagerCallbacks;
    private RecyclerView mRecyclerView;
    private ArrayList<PinningObject> mPinnedView = new ArrayList<>();

    public static Tager getInstance(){
        if (mTager == null) mTager = new Tager();
        return mTager;
    }

    public <T> T subscribeView(T viewHolder){
        ((RecyclerView.ViewHolder)viewHolder).itemView.setTag(viewHolder);
        T t = viewHolder;
        return t;
    }

    public void attach(RecyclerView recyclerView){
        this.mRecyclerView = recyclerView;
    }

    public void pin(RecyclerView.ViewHolder viewHolder){
        mPinnedView.add(new PinningObject(viewHolder, viewHolder.getAdapterPosition()));
    }

    public void updatePinnedView(RecyclerView.ViewHolder viewHolder){
        for (PinningObject pinningObject : mPinnedView) {
            if (pinningObject.position == viewHolder.getAdapterPosition()){
                pinningObject.viewHolder = viewHolder;
            }
        }
    }

    public void removePin(int position){
        for (PinningObject pinningObject : mPinnedView) {
            if (pinningObject.position == position){
                mPinnedView.remove(pinningObject);
                break;
            }
        }
    }

    public void removePinAtIndex(int index){
        mPinnedView.remove(index);
    }


    public PinningObject getPinnedItem(int position){
        return mPinnedView.get(position);
    }

    public void removePinAll(){
        mPinnedView.clear();
    }

    public int pinniedSize(){
        return mPinnedView.size();
    }

    public void sendCallback(RecyclerView.ViewHolder rootView,@IdRes int position){
        if (mTager.mTagerCallbacks.size() > 0) {
            for (TagerCallback mTagerCallback : mTager.mTagerCallbacks) {
                mTagerCallback.onReceived(position, rootView);
            }
        }
    }

    public <T> T getActualView(RecyclerView.ViewHolder view){
        T t = (T) view.itemView.findViewWithTag(view).getTag();
       return t;
    }

    public <T> T getActualView(View itemView) {
        if (mRecyclerView != null){
            return (T) mRecyclerView.getChildViewHolder(itemView);
        } else {
            throw new Error("Null attached RecyclerView : Tager.getInstance().attach(RecyclerView recyclerView)");
        }
    }

    public <T> T getActualView(RecyclerView.ViewHolder viewHolder, View view){
        T t = (T) view.findViewWithTag(viewHolder).getTag();
        return t;
    }

    public void setCallBack(TagerCallback tagerCallback){
        mTager.mTagerCallbacks.add(tagerCallback);
    }

    public void removeCallBack(TagerCallback tagerCallback){
        mTager.mTagerCallbacks.remove(tagerCallback);
    }

    public void clearCallBack(){
        mTager.mTagerCallbacks.clear();
    }

    public class PinningObject{
        private int position;
        private RecyclerView.ViewHolder viewHolder;
        public PinningObject(RecyclerView.ViewHolder viewHolder, int position){
            this.position = position;
            this.viewHolder = viewHolder;
        }

        public int getPosition() {
            return position;
        }

        public RecyclerView.ViewHolder getViewHolder() {
            return viewHolder;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public void setViewHolder(RecyclerView.ViewHolder viewHolder) {
            this.viewHolder = viewHolder;
        }
    }
}
