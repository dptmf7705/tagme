package com.dankook.tagme.data.remote;

import com.google.gson.annotations.SerializedName;

public class StoreAddrRequest {
    @SerializedName("store_address")
    private String storeAddr;

    public String getStoreAddr() {
        return storeAddr;
    }

    public void setStoreAddr(String storeAddr) {
        this.storeAddr = storeAddr;
    }

}
