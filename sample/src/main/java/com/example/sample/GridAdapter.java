package com.example.sample;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tager.Tager;
import com.example.tager.TagerAdapter;

import java.util.ArrayList;

/**
 * Created by kognsin on 9/5/2016.
 */

public class GridAdapter extends TagerAdapter<GridViewHolder> {

    private static final String TAG = "GridAdapter";
    private ArrayList<String> mData;
    private Context mContext;

    public GridAdapter(Context context, ArrayList<String> data){
        this.mContext = context;
        this.mData = data;
    }

    @Override
    public GridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.mContext).inflate(R.layout.grid_layout, parent, false);
        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GridViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
