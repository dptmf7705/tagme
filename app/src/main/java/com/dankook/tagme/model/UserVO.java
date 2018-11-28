package com.dankook.tagme.model;

import com.google.gson.annotations.SerializedName;

public class UserVO {
    @SerializedName("user_key") private int usrKey;
    @SerializedName("user_id") private String usrId;
    @SerializedName("user_password") private String usrPassword;
    @SerializedName("user_name") private String usrName;
    @SerializedName("user_phone") private String usrPhone;
    @SerializedName("user_address") private String usrAddr;

    public UserVO() {
    }

    public UserVO(int usrKey, String usrId, String usrName, String usrAddr, String usrPhone) {
        this.usrKey = usrKey;
        this.usrId = usrId;
        this.usrName = usrName;
        this.usrAddr = usrAddr;
        this.usrPhone = usrPhone;
    }

    public void setUsrkey(int usrKey) {
        this.usrKey = usrKey;
    }

    public void setUsrId(String usrId) {
        this.usrId = usrId;
    }

    public void setUsrPassword(String usrPassword) {
        this.usrPassword = usrPassword;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }

    public void setUsrPhone(String usrPhone) {
        this.usrPhone = usrPhone;
    }

    public void setUsrAddr(String usrAddr) {
        this.usrAddr = usrAddr;
    }

    public int getUsrkey() {
        return usrKey;
    }

    public String getUsrId() {
        return usrId;
    }

    public String getUsrPassword() {
        return usrPassword;
    }

    public String getUsrName() {
        return usrName;
    }

    public String getUsrPhone() {
        return usrPhone;
    }

    public String getUsrAddr() {
        return usrAddr;
    }
}
