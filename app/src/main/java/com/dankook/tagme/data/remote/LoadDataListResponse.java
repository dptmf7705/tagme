package com.dankook.tagme.data.remote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoadDataListResponse<T> {

    @SerializedName("result")
    @Expose
    private boolean result;

    @SerializedName("content")
    @Expose
    private List<T> content;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

}
