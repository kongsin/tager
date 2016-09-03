package com.example.kongsin.autogridlayoutmanager.book_entities.book_detail;

import com.google.gson.annotations.SerializedName;

/**
 * Created by DroidDev on 8/16/16.
 */
public class BookDetailData {

    @SerializedName("overview")
    public String overview;

    @SerializedName("primaryCategoryName")
    public String primaryCategoryName;

    @SerializedName("secondaryCategoryName")
    public String secondaryCategoryName;

    @SerializedName("paperPrintLength")
    public Integer paperPrintLength;

    @SerializedName("availableDate")
    public String availableDate;

    @SerializedName("languageCodes")
    public String languageCodes;

    @SerializedName("languages")
    public String languages;

    @SerializedName("printListPrice")
    public Integer printListPrice;

    @SerializedName("printListPriceCurrency")
    public String printListPriceCurrency;

    @SerializedName("printListPriceCurrencyDisplayText")
    public String printListPriceCurrencyDisplayText;

    @SerializedName("isRtl")
    public Boolean isRtl;

    @SerializedName("code")
    public String code;

    @SerializedName("title")
    public String title;

    @SerializedName("author")
    public String author;

    @SerializedName("isFree")
    public Boolean isFree;

    @SerializedName("isMatureContent")
    public Boolean isMatureContent;

    @SerializedName("publisherName")
    public String publisherName;

    @SerializedName("coverImageUrl")
    public String coverImageUrl;

    @SerializedName("permissionLevel")
    public Integer permissionLevel;

    @SerializedName("countryCode")
    public String countryCode;

    @SerializedName("country")
    public String country;

    @SerializedName("rank")
    public Integer rank;

    @SerializedName("rating")
    public Float rating;

    @SerializedName("releaseDate")
    public String releaseDate;

    @SerializedName("supportsPdf")
    public Boolean supportsPdf;

    @SerializedName("supportsEpub")
    public Boolean supportsEpub;

    @SerializedName("supportsGreelane")
    public Boolean supportsGreelane;

    @SerializedName("pdfSample")
    public BookPdfSample pdfSample;

    @SerializedName("isDisney")
    public String isDisney;

    }

