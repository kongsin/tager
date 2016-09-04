package com.example.tager;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by kognsin on 9/5/2016.
 */

public abstract class TagerViewHolder extends RecyclerView.ViewHolder {

    public TagerViewHolder(View itemView) {
        super(itemView);
    }

    public int getTagerPosition(){
        TagerViewHolder tagerViewHolder = Tager.getInstance().getActualView(this);
        Tager.PinningObject pinningObject = Tager.getInstance().getPinnedItemByPosition(tagerViewHolder.getAdapterPosition());
        return pinningObject != null ? pinningObject.getPosition() : getAdapterPosition();
    }
}
