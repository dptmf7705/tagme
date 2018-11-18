package com.dankook.tagme.data.remote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoadDataResponse<T> {

    @SerializedName("result")
    @Expose
    private boolean result;

    @SerializedName("content")
    @Expose
    private T content;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
