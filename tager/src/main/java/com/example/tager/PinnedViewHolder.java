package com.example.tager;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by kognsin on 9/5/2016.
 */

public abstract class PinnedViewHolder extends RecyclerView.ViewHolder {

    public PinnedViewHolder(View itemView) {
        super(itemView);
    }

    public int getTagerPosition(){
        PinnedViewHolder pinnedViewHolder = Tager.getInstance().getActualView(this);
        Tager.PinningObject pinningObject = Tager.getInstance().getPinnedItemByPosition(pinnedViewHolder.getAdapterPosition());
        return pinningObject != null ? pinningObject.getPosition() : getAdapterPosition();
    }
}
