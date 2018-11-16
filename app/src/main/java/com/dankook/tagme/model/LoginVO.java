package com.dankook.tagme.model;

import com.google.gson.annotations.SerializedName;

public class LoginVO {
    private boolean result;
    @SerializedName("usr_id") private UserVO usr;
    private String fail;

    public void setResult(boolean result) {
        this.result = result;
    }
    public void setUsrId(UserVO usrId) {
        this.usr = usrId;
    }
    public void setFail(String fail) {
        this.fail = fail;
    }
    public boolean getResult() {
        return result;
    }
    public UserVO getUsrId() {
        return usr;
    }
    public String getFail() {
        return fail;
    }
}
