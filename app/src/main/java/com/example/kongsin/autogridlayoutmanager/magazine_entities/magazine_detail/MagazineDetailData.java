package com.example.kongsin.autogridlayoutmanager.magazine_entities.magazine_detail;

import com.google.gson.annotations.SerializedName;

/**
 * Created by DroidDev on 8/16/16.
 */
public class MagazineDetailData {

    @SerializedName("paperPrintLength")
    public Integer  paperPrintLength;

    @SerializedName("releaseDate")
    public String   releaseDate;

    @SerializedName("availableDate")
    public String   availableDate;

    @SerializedName("digitalPageCount")
    public Integer  digitalPageCount;

    @SerializedName("digitalSizeByteCount")
    public Long     digitalSizeByteCount;

    @SerializedName("digitalSizeDisplayText")
    public String   digitalSizeDisplayText;

    @SerializedName("digitalInteractiveSizeByteCount")
    public Integer  digitalInteractiveSizeByteCount;

    @SerializedName("digitalInteractiveSizeDisplayText")
    public String   digitalInteractiveSizeDisplayText;

    @SerializedName("digitalFormat")
    public String   digitalFormat;

    @SerializedName("printListPrice")
    public MagazinePrintListPrice printListPrice;

    @SerializedName("permissionLevel")
    public Integer  permissionLevel;

    @SerializedName("title")
    public String   title;

    @SerializedName("issueName")
    public String   issueName;

    @SerializedName("magazineName")
    public String   magazineName;

    @SerializedName("magazineCode")
    public String   magazineCode;

    @SerializedName("coverImageUrl")
    public String   coverImageUrl;

    @SerializedName("supportsPdf")
    public Boolean  supportsPdf;

    @SerializedName("supportsEpub")
    public Boolean  supportsEpub;

    @SerializedName("supportsGreelane")
    public Boolean  supportsGreelane;

    @SerializedName("code")
    public String   code;
}
