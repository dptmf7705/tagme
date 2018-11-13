package com.dankook.tagme.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Store {

    @SerializedName("store_key")
    @Expose
    private Integer storeKey;

    @SerializedName("category_key")
    @Expose
    private Integer categoryKey;

    @SerializedName("store_name")
    @Expose
    private String storeName;

    @SerializedName("store_address")
    @Expose
    private String storeAddress;

    @SerializedName("store_phone")
    @Expose
    private String storePhone;

    @SerializedName("store_image_key")
    @Expose
    private Integer storeImageKey;

    @SerializedName("store_image_path")
    @Expose
    private String storeImagePath;

    @SerializedName("rating_count")
    @Expose
    private Integer ratingCount;

    @SerializedName("review_count")
    @Expose
    private Integer reviewCount;

    @SerializedName("storeImageList")
    @Expose
    private List<StoreImage> storeImageList;

    @SerializedName("storeMenuList")
    @Expose
    private List<StoreMenu> storeMenuList;

    public Integer getStoreKey() {
        return storeKey;
    }

    public void setStoreKey(Integer storeKey) {
        this.storeKey = storeKey;
    }

    public Integer getCategoryKey() {
        return categoryKey;
    }

    public void setCategoryKey(Integer categoryKey) {
        this.categoryKey = categoryKey;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getStorePhone() {
        return storePhone;
    }

    public void setStorePhone(String storePhone) {
        this.storePhone = storePhone;
    }

    public Integer getStoreImageKey() {
        return storeImageKey;
    }

    public void setStoreImageKey(Integer storeImageKey) {
        this.storeImageKey = storeImageKey;
    }

    public String getStoreImagePath() {
        return storeImagePath;
    }

    public void setStoreImagePath(String storeImagePath) {
        this.storeImagePath = storeImagePath;
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

    public List<StoreImage> getStoreImageList() {
        return storeImageList;
    }

    public void setStoreImageList(List<StoreImage> storeImageList) {
        this.storeImageList = storeImageList;
    }

    public List<StoreMenu> getStoreMenuList() {
        return storeMenuList;
    }

    public void setStoreMenuList(List<StoreMenu> storeMenuList) {
        this.storeMenuList = storeMenuList;
    }
}
