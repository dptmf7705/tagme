package com.dankook.tagme.data.remote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StoreDetailRequest {

    @SerializedName("store_key")
    @Expose
    private int storeKey;

    public StoreDetailRequest() {
    }

    public StoreDetailRequest(int storeKey) {
        this.storeKey = storeKey;
    }

    public int getStoreKey() {
        return storeKey;
    }

    public void setStoreKey(int storeKey) {
        this.storeKey = storeKey;
    }
}
