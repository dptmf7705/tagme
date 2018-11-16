package com.dankook.tagme.data.remote;

import com.google.gson.annotations.SerializedName;

public class StoreDetailRequest {
    @SerializedName("store_key") private String storeKey;

    public StoreDetailRequest(String storeKey) {
        this.storeKey = storeKey;
    }

    public String getStoreKey() {
        return storeKey;
    }

    public void setStoreKey(String storeKey) {
        this.storeKey = storeKey;
    }
}
