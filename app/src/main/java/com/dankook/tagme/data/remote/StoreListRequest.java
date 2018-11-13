package com.dankook.tagme.data.remote;

import com.google.gson.annotations.SerializedName;

public class StoreListRequest {

    @SerializedName("category_key") private int categoryKey;

    public StoreListRequest(int categoryKey) {
        this.categoryKey = categoryKey;
    }

    public int getCategoryKey() {
        return categoryKey;
    }

    public void setCategoryKey(int categoryKey) {
        this.categoryKey = categoryKey;
    }
}
