package com.dankook.tagme.model;

import com.google.gson.annotations.SerializedName;

public class LoginVO {
    private boolean result;
    @SerializedName("user") private UserVO uVO;
    private String fail;

    public void setResult(boolean result) {
        this.result = result;
    }
    public void setUVO(UserVO uVO) {
        this.uVO = uVO;
    }
    public void setFail(String fail) {
        this.fail = fail;
    }
    public boolean getResult() {
        return result;
    }
    public UserVO getUVO() {
        return uVO;
    }
    public String getFail() {
        return fail;
    }
}
