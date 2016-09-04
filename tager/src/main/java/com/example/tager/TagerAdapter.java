package com.example.tager;

import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by kognsin on 9/4/2016.
 */

public abstract class TagerAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    @Override
    public void onBindViewHolder(VH holder, int position) {
        Tager.getInstance().subscribeView(holder);
        Tager.getInstance().updatePinnedView(holder);
    }

    @Override
    public void onBindViewHolder(VH holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        Tager.getInstance().subscribeView(holder);
        Tager.getInstance().updatePinnedView(holder);
    }
}
