package com.example.kongsin.autogridlayoutmanager.magazine_entities.magazine_detail;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by DroidDev on 8/16/16.
 */

public class MagazineDetail implements Serializable{
    @SerializedName("apiVersion")
    public String apiVersion;

    @SerializedName("data")
    public MagazineDetailData data;
}
