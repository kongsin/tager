package com.example.kongsin.autogridlayoutmanager.magazine_entities.magazine_detail;

import com.google.gson.annotations.SerializedName;

/**
 * Created by DroidDev on 8/16/16.
 */
public class MagazinePrintListPrice {

    @SerializedName("value")
    public Float value;

    @SerializedName("currency")
    public String currency;

    @SerializedName("displayText")
    public String displayText;
}
