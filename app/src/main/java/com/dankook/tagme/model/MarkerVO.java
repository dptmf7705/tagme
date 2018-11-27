package com.dankook.tagme.model;

import android.graphics.Point;

import com.nhn.android.maps.maplib.NGeoPoint;

public class MarkerVO {
    private double lat;
    private double lng;
    private String storeAddress;
    private String storeName;

    public MarkerVO() {
        storeAddress = new String();
        storeName = new String();
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
