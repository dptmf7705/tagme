package com.dankook.tagme.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("category_key")
    @Expose
    private Integer categoryKey;

    @SerializedName("category_name")
    @Expose
    private String categoryName;

    @SerializedName("category_name_kor")
    @Expose
    private String categoryNameKor;

    public Integer getCategoryKey() {
        return categoryKey;
    }

    public void setCategoryKey(Integer categoryKey) {
        this.categoryKey = categoryKey;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryNameKor() {
        return categoryNameKor;
    }

    public void setCategoryNameKor(String categoryNameKor) {
        this.categoryNameKor = categoryNameKor;
    }
}
