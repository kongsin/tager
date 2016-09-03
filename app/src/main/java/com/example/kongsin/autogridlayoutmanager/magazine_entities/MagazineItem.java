package com.example.kongsin.autogridlayoutmanager.magazine_entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by DroidDev on 8/16/16.
 */
public class MagazineItem implements Serializable{

    public int width = 0;

    public int height = 0;

    public boolean progress = false;

    @SerializedName("title")
    public String title;

    @SerializedName("subtitle")
    public String subtitle;

    @SerializedName("imageUrl")
    public String imageUrl;

    @SerializedName("linkUrl")
    public String linkUrl;

    @SerializedName("rating")
    public Float rating;

    @SerializedName("permissionLevel")
    public Integer permissionLevel;

    @SerializedName("isMatureContent")
    public Boolean isMatureContent;

    @SerializedName("supportsPdf")
    public Boolean supportsPdf;

    @SerializedName("supportsEpub")
    public Boolean supportsEpub;

    @SerializedName("supportsGreelane")
    public Boolean supportsGreelane;

    @SerializedName("hasPdfSample")
    public Boolean hasPdfSample;

    @SerializedName("code")
    public String code;

    public String getMagazineDetailReferenceId(){
        return this.linkUrl.replace("me://mi","");
    }
}
