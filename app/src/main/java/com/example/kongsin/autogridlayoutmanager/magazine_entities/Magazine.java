package com.example.kongsin.autogridlayoutmanager.magazine_entities;


import com.google.gson.annotations.SerializedName;

/**
 * Created by DroidDev on 8/16/16.
 */
public class Magazine {
    @SerializedName("apiVersion")
    public String apiVersion;

    @SerializedName("data")
    public MagazineData data;
}
