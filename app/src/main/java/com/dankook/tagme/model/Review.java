package com.dankook.tagme.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Review extends UserVO {

    @SerializedName("review_key")
    @Expose
    private String reviewKey;

    @SerializedName("review_text")
    @Expose
    private String reviewText;

    @SerializedName("rating_point")
    @Expose
    private int ratingPoint;

    @SerializedName("first_upload_date")
    @Expose
    private Date writeDate;

    @SerializedName("store_key")
    @Expose
    private String storeKey;

    @SerializedName("menu_key")
    @Expose
    private String menuKey;

    @SerializedName("user_key")
    @Expose
    private String userKey;

    @SerializedName("imageList")
    @Expose
    private List<Image> imageList;

    public String getReviewKey() {
        return reviewKey;
    }

    public void setReviewKey(String reviewKey) {
        this.reviewKey = reviewKey;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public int getRatingPoint() {
        return ratingPoint;
    }

    public void setRatingPoint(int ratingPoint) {
        this.ratingPoint = ratingPoint;
    }

    public Date getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(Date writeDate) {
        this.writeDate = writeDate;
    }

    public String getStoreKey() {
        return storeKey;
    }

    public void setStoreKey(String storeKey) {
        this.storeKey = storeKey;
    }

    public String getMenuKey() {
        return menuKey;
    }

    public void setMenuKey(String menuKey) {
        this.menuKey = menuKey;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }
}
