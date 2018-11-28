package com.dankook.tagme.data.remote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InsertOrderRequest {

    @SerializedName("user_key")
    @Expose
    private int userKey;

    @SerializedName("store_key")
    @Expose
    private int storeKey;

    @SerializedName("table_number")
    @Expose
    private int tableNumber;

    @SerializedName("menu_key")
    @Expose
    private int menuKey;

    @SerializedName("quantity_of_menu")
    @Expose
    private int amount;

    public InsertOrderRequest() {
    }

    public InsertOrderRequest(int userKey, int storeKey, int tableNumber, int menuKey, int amount) {
        this.userKey = userKey;
        this.storeKey = storeKey;
        this.tableNumber = tableNumber;
        this.menuKey = menuKey;
        this.amount = amount;
    }

    public int getStoreKey() {
        return storeKey;
    }

    public void setStoreKey(int storeKey) {
        this.storeKey = storeKey;
    }

    public int getUserKey() {
        return userKey;
    }

    public void setUserKey(int userKey) {
        this.userKey = userKey;
    }

    public int getMenuKey() {
        return menuKey;
    }

    public void setMenuKey(int menuKey) {
        this.menuKey = menuKey;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }
}
