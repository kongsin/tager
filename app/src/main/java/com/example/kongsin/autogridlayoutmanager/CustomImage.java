package com.example.kongsin.autogridlayoutmanager;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.example.kongsin.autogridlayoutmanager.magazine_entities.MagazineItem;

/**
 * Created by kognsin on 9/2/2016.
 */

public class CustomImage extends ImageView {

    public int position;
    public String url;
    public MagazineItem magazineItem;
    public boolean isResized = false;

    public CustomImage(Context context) {
        super(context);
    }

    public CustomImage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
