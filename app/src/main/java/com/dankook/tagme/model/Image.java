package com.dankook.tagme.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image {

    @SerializedName("image_key")
    @Expose
    public Integer imageKey;

    @SerializedName("image_stored_name")
    @Expose
    public String imageStoredName;

    @SerializedName("image_extension")
    @Expose
    public String imageExtension;

    @SerializedName("image_stored_path")
    @Expose
    public String imageStoredPath;

    public Integer getImageKey() {
        return imageKey;
    }

    public void setImageKey(Integer imageKey) {
        this.imageKey = imageKey;
    }

    public String getImageStoredName() {
        return imageStoredName;
    }

    public void setImageStoredName(String imageStoredName) {
        this.imageStoredName = imageStoredName;
    }

    public String getImageExtension() {
        return imageExtension;
    }

    public void setImageExtension(String imageExtension) {
        this.imageExtension = imageExtension;
    }

    public String getImageStoredPath() {
        return imageStoredPath;
    }

    public void setImageStoredPath(String imageStoredPath) {
        this.imageStoredPath = imageStoredPath;
    }
}
