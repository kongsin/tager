package com.example.kongsin.autogridlayoutmanager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.kongsin.autogridlayoutmanager.magazine_entities.MagazineItem;
import com.example.kongsin.autogridlayoutmanager.magazine_entities.MagazineItemGroups;
import com.example.tager.Tager;
import com.example.tager.TagerCallback;

import java.util.ArrayList;

/**
 * Created by kongsin on 8/27/16.
 */

public class GridAdapter extends RecyclerView.Adapter<MagazineListViewHolder> implements TagerCallback<MagazineListViewHolder> {

    private static final String TAG = "GridAdapter";
    private Context mContext;
    private ArrayList<MagazineItem> mMagazineItem;
    private RecyclerView mRecyclerView;
    private MagazineListViewHolder mShowingView;
    private int mShowingViewPosition;
    private ArrayList<ItemSizePool> itemSizePools = new ArrayList<>();

    public GridAdapter(Context context, RecyclerView recyclerView) {
        this.mContext = context;
        this.mRecyclerView = recyclerView;
        Tager.getInstance().setCallBack(this);
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
    public void onBindViewHolder(final MagazineListViewHolder holder, int position) {
        holder.setupData(mMagazineItem.get(position));
        Tager.getInstance().subscribeView(holder);
    }

    @Override
    public int getItemCount() {
        return mMagazineItem.size();
    }

    @Override
    public void onViewDetachedFromWindow(MagazineListViewHolder holder) {
        ItemSizePool sizePool = new ItemSizePool();
        sizePool.position = holder.getAdapterPosition();
        sizePool.w = ViewGroup.LayoutParams.MATCH_PARENT;
        sizePool.h = holder.itemView.getMeasuredHeight();
        itemSizePools.add(sizePool);
        super.onViewDetachedFromWindow(holder);
    }

    @Override
    public void onReceived(int position, MagazineListViewHolder viewHolder) {
        if (mShowingView != null){ // if mShowing view is not null clear it first before set value to new position
            mMagazineItem.get(mShowingViewPosition).progress = false;
            if (mShowingView.getAdapterPosition() == mShowingViewPosition){
                //if opening view still not recycled so can use its to clearState
                mShowingView.clearState();
            } else {
                // if opening view already recycled clear opening view by get from layout manager
                View m = mRecyclerView.getLayoutManager().getChildAt(mShowingViewPosition);
                if (m != null) ((MagazineListViewHolder) m.getTag()).clearState();
            }
        }
        if (mShowingViewPosition != position){
            // if showing view position not the same with selected position
            // set value to new position and assign new position to be opening position
            setValue(viewHolder, position);
            mShowingView = viewHolder;
            mShowingViewPosition = position;
        } else {
            // if opening position and selected position are the same
            // clear opening position
            mShowingView = null;
            mShowingViewPosition = -1;
        }
    }

    private void setValue(MagazineListViewHolder viewHolder, int position) {
        mMagazineItem.get(position).progress = !mMagazineItem.get(position).progress; // inverse progress value
        viewHolder.showState(mMagazineItem.get(viewHolder.getAdapterPosition()));
    }

    class ItemSizePool {
        int position;
        int w;
        int h;
    }
}
