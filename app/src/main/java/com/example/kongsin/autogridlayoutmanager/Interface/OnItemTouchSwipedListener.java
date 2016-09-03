package com.example.kongsin.autogridlayoutmanager.Interface;

import android.support.v7.widget.RecyclerView;

/**
 * Created by DroidDev on 8/16/16.
 */
public interface OnItemTouchSwipedListener {
    void onSwiped(RecyclerView.ViewHolder viewHolder, int direction);
}
