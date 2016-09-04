package com.example.tager;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by kognsin on 9/4/2016.
 */

public abstract class TagerAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        Tager.getInstance().subscribeView(holder);
        Tager.getInstance().updatePinnedView(holder);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

}
