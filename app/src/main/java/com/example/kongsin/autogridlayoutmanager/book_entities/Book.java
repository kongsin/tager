package com.example.kongsin.autogridlayoutmanager.book_entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by DroidDev on 8/16/16.
 */
public class Book {

    @SerializedName("apiVersion")
    public String apiVersion;

    @SerializedName("data")
    public BookData data;

}
