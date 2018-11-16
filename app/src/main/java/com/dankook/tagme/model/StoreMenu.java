package com.dankook.tagme.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StoreMenu {

    @SerializedName("menu_key")
    @Expose
    private Integer menuKey;

    @SerializedName("menu_name")
    @Expose
    private String menuName;

    @SerializedName("menu_image_path")
    @Expose
    private String menuImagePath;

    @SerializedName("menu_price")
    @Expose
    private Integer menuPrice;

    @SerializedName("rating_count")
    @Expose
    private Integer ratingCount;

    @SerializedName("review_count")
    @Expose
    private Integer reviewCount;

    @SerializedName("store_key")
    @Expose
    private Integer storeKey;

    public Integer getMenuKey() {
        return menuKey;
    }

    public void setMenuKey(Integer menuKey) {
        this.menuKey = menuKey;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuImagePath() {
        return menuImagePath;
    }

    public void setMenuImagePath(String menuImagePath) {
        this.menuImagePath = menuImagePath;
    }

    public Integer getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(Integer menuPrice) {
        this.menuPrice = menuPrice;
    }

    public Integer getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(Integer ratingCount) {
        this.ratingCount = ratingCount;
    }

    public Integer getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }

    public Integer getStoreKey() {
        return storeKey;
    }

    public void setStoreKey(Integer storeKey) {
        this.storeKey = storeKey;
    }
}
