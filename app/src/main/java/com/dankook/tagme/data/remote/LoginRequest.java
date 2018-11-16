package com.dankook.tagme.data.remote;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {
    @SerializedName("user_id") private String usrId;
    @SerializedName("user_password") private String usrPassword;

    public LoginRequest(String usrId, String usrPassword) {
        this.usrId = usrId;
        this.usrPassword = usrPassword;
    }

    public void setUsrId(String usrId) {
        this.usrId = usrId;
    }

    public void setUsrPassword(String usrPassword) {
        this.usrPassword = usrPassword;
    }
}
