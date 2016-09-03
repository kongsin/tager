package com.example.kongsin.autogridlayoutmanager.magazine_entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by DroidDev on 8/16/16.
 */
public class MagazineItemGroups implements Serializable{
    @SerializedName("displayText")
    public String displayText;

    @SerializedName("iconImageUrl")
    public String iconImageUrl;

    @SerializedName("items")
    public MagazineItem[] items;
}
