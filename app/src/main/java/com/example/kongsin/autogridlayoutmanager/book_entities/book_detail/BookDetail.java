package com.example.kongsin.autogridlayoutmanager.book_entities.book_detail;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by DroidDev on 8/16/16.
 */
public class BookDetail implements Serializable{
    @SerializedName("apiVersion")
    public String apiVersion;

    @SerializedName("data")
    public BookDetailData data;
}
