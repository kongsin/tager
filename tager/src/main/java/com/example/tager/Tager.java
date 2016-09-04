package com.example.tager;

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by kognsin on 9/2/2016.
 */

public class Tager {

    private static Tager mTager;
    private static final String TAG = "Tager";
    private ArrayList<TagerCallback> mTagerCallbacks = new ArrayList<>();
    private ArrayList<PinningObject> mPinnedView = new ArrayList<>();

    //Singleton create Tager object
    public static Tager getInstance(){
        if (mTager == null) mTager = new Tager();
        return mTager;
    }

    /*
    Subscribe ViewHolder for each item
    this one will be set tag (ViewHolder) to each view of its ViewHolder
    for follow it later
    */
    public <T> T subscribeView(T viewHolder){
        ((RecyclerView.ViewHolder)viewHolder).itemView.setTag(viewHolder);
        T t = viewHolder;
        return t;
    }

    /*
    Pin is things that you need to make it be like a current following
    Example : If you need to know what is a current clicked view holder
    so you can pin it to be a current clicked one.
    Example Call :
    Tager.getInstance().pin(ClickedViewHolder);
    So right now ClickedViewHolder is pinned.
    you can get pined ViewHolder by call method getPinnedItem(int index);
    */
    public void pin(RecyclerView.ViewHolder viewHolder){
        mPinnedView.add(new PinningObject(viewHolder, viewHolder.getAdapterPosition()));
    }

    /*
    updatePinnedView is a important method it help to resolve the problem like below one
    Example : when you had pinned ViewHolder one and then that ViewHolder was destroyed by recycler view.
    Right now you cannot follow that view holder because this was destroyed, So you need to update pinnedView when it
    come to onBindViewHolder in the adapter.
    */
    public void updatePinnedView(RecyclerView.ViewHolder viewHolder){
        for (PinningObject pinningObject : mPinnedView) {
            if (pinningObject.position == viewHolder.getAdapterPosition()){
                pinningObject.viewHolder = viewHolder;
            }
        }
    }

    /*
    Remove pinned one by it adapter position, Not index!
    */
    public void removePin(int position){
        Iterator it = mPinnedView.iterator();
        while (it.hasNext()) {
            if (it.next().equals(position)){
                it.remove();
                break;
            }
        }
    }

    /*
    Remove pinned one by index in pinned list
    */
    public void removePinAtIndex(int index){
        mPinnedView.remove(index);
    }


    /*
    Get pinned item
    */
    public PinningObject getPinnedItem(int index){
        return mPinnedView.get(index);
    }

    public <T> T getPinnedItemByPosition(int position){
        for (PinningObject pinningObject : mPinnedView) {
            if (pinningObject.equals(position)){
                return (T) pinningObject;
            }
        }
        return null;
    }

    public ArrayList<PinningObject> getPinnedItems() {return mPinnedView;}

    /*
        Remove all pinned list
        */
    public void removePinAll(){
        mPinnedView.clear();
    }

    /*
    Get pinned size
    */
    public int pinniedSize(){
        return mPinnedView.size();
    }

    /*
    Send callback to all callback in callback list
    */
    public void sendCallback(RecyclerView.ViewHolder rootView,@IdRes int position){
        if (mTager.mTagerCallbacks.size() > 0) {
            for (TagerCallback mTagerCallback : mTager.mTagerCallbacks) {
                RecyclerView.ViewHolder viewHolder = Tager.getInstance().getActualView(rootView);
                mTagerCallback.onReceived(position, viewHolder != null ? viewHolder : rootView);
            }
        }
    }

    /*
    when you get clicked view holder but you cannot specific which one is current clicked because its was
    shared to another item you need to use this method to get actual viewholder that it was set tag to each itemView
    of each ViewHolder
    */
    public <T> T getActualView(RecyclerView.ViewHolder view){
        T t = (T) view.itemView.findViewWithTag(view).getTag();
       return t;
    }

    public <T> T getActualView(RecyclerView.ViewHolder viewHolder, View view){
        T t = (T) view.findViewWithTag(viewHolder).getTag();
        return t;
    }

    /*
    Set callBack to call back list
    */
    public void setCallBack(TagerCallback tagerCallback){
        mTager.mTagerCallbacks.add(tagerCallback);
    }
    /*
    Remove callBack to call from list
    */
    public void removeCallBack(TagerCallback tagerCallback){
        mTager.mTagerCallbacks.remove(tagerCallback);
    }

    /*
    Clear all callBack from callBack list
    */
    public void clearCallBack(){
        mTager.mTagerCallbacks.clear();
    }

    public boolean isMatchWithPinnedView(RecyclerView.ViewHolder viewHolder){
        if (mPinnedView.size() > 0){
            for (PinningObject pinningObject : mPinnedView) {
                if (pinningObject.equals(viewHolder)){
                    return true;
                }
            }
        }
        return false;
    }

    /*
    Pinned object is a object that you save to pinned list
    need to save separate viewHolder and position
    for check that view holder is still correct one
    by if(positon == viewHolder.getAdapterPosition()){
            //this one is correct one
        } else {
           // something went wrong maybe ViewHolder was destroyed and cannot update
        }
    */
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

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof RecyclerView.ViewHolder){
                RecyclerView.ViewHolder _vh = (RecyclerView.ViewHolder) obj;
                return viewHolder.getAdapterPosition() == _vh.getAdapterPosition() && viewHolder.getAdapterPosition() == position;
            } else if (obj instanceof Integer){
                return obj.equals(position);
            }
            return false;
        }
    }
}
