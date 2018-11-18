package com.dankook.tagme.data.remote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StoreListRequest {

    @SerializedName("category_key")
    @Expose
    private int categoryKey;

    @SerializedName("page_index")
    @Expose
    private int pageIndex;

    @SerializedName("page_unit")
    @Expose
    private int pageUnit;

    public StoreListRequest() {
    }

    public StoreListRequest(int categoryKey, int pageIndex, int pageUnit) {
        this.categoryKey = categoryKey;
        this.pageIndex = pageIndex;
        this.pageUnit = pageUnit;
    }

    public int getCategoryKey() {
        return categoryKey;
    }

    public void setCategoryKey(int categoryKey) {
        this.categoryKey = categoryKey;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageUnit() {
        return pageUnit;
    }

    public void setPageUnit(int pageUnit) {
        this.pageUnit = pageUnit;
    }
}
