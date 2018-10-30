package com.dankook.tagme.vo;

public class LoginVO {
    public boolean result;
    public UserVO user;
    public String fail;

    public boolean getResult() {
        return result;
    }

    public UserVO getUserVO() {
        return user;
    }

    public String getFail() {
        return fail;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public void setUserVO(UserVO userVO) {
        this.user = userVO;
    }

    public void setFail(String fail) {
        this.fail = fail;
    }

}
