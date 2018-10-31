package com.dankook.tagme.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Store {

    @SerializedName("store_key") private String storeKey;

    @SerializedName("store_name") private String storeName;

    @SerializedName("main_image_url") private String mainImageUrl;

    @SerializedName("store_address") private String address;

    @SerializedName("rating_count") private int ratingCount;

    @SerializedName("review_count") private int reviewCount;

    private List<StoreMenu> menuList;

    public String getStoreKey() {
        return storeKey;
    }

    public void setStoreKey(String storeKey) {
        this.storeKey = storeKey;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getMainImageUrl() {
        return mainImageUrl;
    }

    public void setMainImageUrl(String mainImageUrl) {
        this.mainImageUrl = mainImageUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public List<StoreMenu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<StoreMenu> menuList) {
        this.menuList = menuList;
    }
}
