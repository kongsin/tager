package com.example.kongsin.autogridlayoutmanager;

import android.content.Context;
import android.view.ViewGroup;

import com.example.kongsin.autogridlayoutmanager.magazine_entities.MagazineItem;
import com.example.kongsin.autogridlayoutmanager.magazine_entities.MagazineItemGroups;
import com.example.tager.TagerAdapter;

import java.util.ArrayList;

/**
 * Created by kongsin on 8/27/16.
 */

public class GridAdapter extends TagerAdapter<MagazineListViewHolder> {

    private Context mContext;
    private ArrayList<MagazineItem> mMagazineItem;

    public GridAdapter(Context context) {
        this.mContext = context;
    }

    public MagazineItem getItem(int pos){
        return mMagazineItem.get(pos);
    }

    public void setDataSet(MagazineItemGroups[] magazineItemGroupses) {
        mMagazineItem = new ArrayList<>();
        for (MagazineItemGroups magazineItemGroupse : magazineItemGroupses) {
            for (MagazineItem item : magazineItemGroupse.items) {
                mMagazineItem.add(item);
            }
        }
    }

    @Override
    public MagazineListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MagazineListViewHolder(mContext, parent);
    }

    @Override
    public void onBindViewHolder(MagazineListViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.setupData(mMagazineItem.get(position));
    }

    @Override
    public int getItemCount() {
        return mMagazineItem.size();
    }

    @Override
    public void onViewRecycled(MagazineListViewHolder holder) {
        super.onViewRecycled(holder);
    }

}
