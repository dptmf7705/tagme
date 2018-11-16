package com.dankook.tagme.model;

import com.google.gson.annotations.SerializedName;

public class UserVO {
    @SerializedName("usr_key") private int usrKey;
    @SerializedName("usr_id") private String usrId;
    @SerializedName("usr_password") private String usrPassword;
    @SerializedName("usr_name") private String usrName;
    @SerializedName("usr_phone") private String usrPhone;
    @SerializedName("usr_addr") private String usrAddr;

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
