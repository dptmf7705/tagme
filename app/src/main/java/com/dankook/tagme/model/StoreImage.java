package com.dankook.tagme.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StoreImage {

    @SerializedName("store_image_key")
    @Expose
    private Integer storeImageKey;

    @SerializedName("store_image_path")
    @Expose
    private String storeImagePath;

    @SerializedName("store_key")
    @Expose
    private Integer storeKey;

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

    public Integer getStoreKey() {
        return storeKey;
    }

    public void setStoreKey(Integer storeKey) {
        this.storeKey = storeKey;
    }
}
