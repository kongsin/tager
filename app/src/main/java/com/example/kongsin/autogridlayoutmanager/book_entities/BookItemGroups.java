package com.example.kongsin.autogridlayoutmanager.book_entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by DroidDev on 8/16/16.
 */
public class BookItemGroups implements Serializable{
    @SerializedName("displayText")
    public String displayText;

    @SerializedName("iconImageUrl")
    public String iconImageUrl;

    @SerializedName("items")
    public BookItem[] items;
}
