package com.dankook.tagme.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Menu extends Image {

    @SerializedName("menu_key")
    @Expose
    public Integer menuKey;

    @SerializedName("menu_name")
    @Expose
    public String menuName;

    @SerializedName("menu_price")
    @Expose
    public Integer menuPrice;

    @SerializedName("menu_intro_text")
    @Expose
    public String menuIntroText;

    @SerializedName("rating_count")
    @Expose
    public Integer ratingCount;

    @SerializedName("review_count")
    @Expose
    public Integer reviewCount;

    @SerializedName("menu_index")
    @Expose
    public Integer menuIndex;

    @SerializedName("store_key")
    @Expose
    public Integer storeKey;

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

    public Integer getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(Integer menuPrice) {
        this.menuPrice = menuPrice;
    }

    public String getMenuIntroText() {
        return menuIntroText;
    }

    public void setMenuIntroText(String menuIntroText) {
        this.menuIntroText = menuIntroText;
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

    public Integer getMenuIndex() {
        return menuIndex;
    }

    public void setMenuIndex(Integer menuIndex) {
        this.menuIndex = menuIndex;
    }

    public Integer getStoreKey() {
        return storeKey;
    }

    public void setStoreKey(Integer storeKey) {
        this.storeKey = storeKey;
    }
}
