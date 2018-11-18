package com.dankook.tagme.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Store extends Image {

    @SerializedName("store_key")
    @Expose
    public Integer storeKey;

    @SerializedName("store_name")
    @Expose
    public String storeName;

    @SerializedName("store_branch")
    @Expose
    public String storeBranch;

    @SerializedName("store_address")
    @Expose
    public String storeAddress;

    @SerializedName("store_phone")
    @Expose
    public String storePhone;

    @SerializedName("open_time")
    @Expose
    public String openTime;

    @SerializedName("close_time")
    @Expose
    public String closeTime;

    @SerializedName("rating_count")
    @Expose
    public Integer ratingCount;

    @SerializedName("review_count")
    @Expose
    public Integer reviewCount;

    @SerializedName("category_key")
    @Expose
    public Integer categoryKey;

    @SerializedName("imageList")
    @Expose
    public List<Image> imageList = null;

    @SerializedName("menuList")
    @Expose
    public List<Menu> menuList = null;

    public Integer getStoreKey() {
        return storeKey;
    }

    public void setStoreKey(Integer storeKey) {
        this.storeKey = storeKey;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreBranch() {
        return storeBranch;
    }

    public void setStoreBranch(String storeBranch) {
        this.storeBranch = storeBranch;
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

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
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

    public Integer getCategoryKey() {
        return categoryKey;
    }

    public void setCategoryKey(Integer categoryKey) {
        this.categoryKey = categoryKey;
    }

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

}
