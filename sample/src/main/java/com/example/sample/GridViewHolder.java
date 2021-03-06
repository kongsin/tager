package com.example.sample;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.example.tager.TagerViewHolder;
import com.example.tager.Tager;

/**
 * Created by kognsin on 9/5/2016.
 */

public class GridViewHolder extends TagerViewHolder {
    public GridViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tager.getInstance().sendCallback(GridViewHolder.this);
            }
        });
    }

    public void setTextColor(int color){
        ((TextView) itemView.findViewById(R.id.text)).setTextColor(color);
    }

    public void resetTextColor(){
        ((TextView) itemView.findViewById(R.id.text)).setTextColor(Color.BLACK);
    }

    public void setText(String text){
        if (Tager.getInstance().isEqualWithPinnedView(this)) {
            ((TextView) itemView.findViewById(R.id.text)).setTextColor(Color.BLUE);
        } else {
            ((TextView) itemView.findViewById(R.id.text)).setTextColor(Color.BLACK);
        }
        ((TextView) itemView.findViewById(R.id.text)).setText(text);
    }
}
