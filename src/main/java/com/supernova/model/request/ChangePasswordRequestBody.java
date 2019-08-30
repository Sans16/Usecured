package com.supernova.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChangePasswordRequestBody {

    @Expose
    @SerializedName("old_password")
    private String oldPassword;

    @Expose
    @SerializedName("new_password")
    private String newPassword;

    public ChangePasswordRequestBody(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getOldPassword() {return oldPassword;}

    public void setOldPassword(String oldPassword) {this.oldPassword = oldPassword;}

    public String getNewPassword() {return newPassword;}

    public void setNewPassword(String newPassword) {this.newPassword = newPassword;}
}
