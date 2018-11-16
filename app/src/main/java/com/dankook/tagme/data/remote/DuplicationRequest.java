package com.dankook.tagme.data.remote;

import com.google.gson.annotations.SerializedName;

public class DuplicationRequest {
    @SerializedName("usr_id") private String usrId;

    public DuplicationRequest(String usrId) {
        this.usrId = usrId;
    }

    public String getUsrId() {
        return usrId;
    }

    public void setUsrId(String usrId) {
        this.usrId = usrId;
    }
}
