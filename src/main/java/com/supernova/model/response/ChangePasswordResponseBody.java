package com.supernova.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChangePasswordResponseBody {

    @Expose()
    @SerializedName("status")
    boolean status;

    @Expose()
    @SerializedName("message")
    String message;

//    @Expose
//    @SerializedName("old_password")
//    String oldPassword;

//    @Expose
//    @SerializedName("new_password")
//    String newPassword;
//
//    public ChangePasswordResponseBody(String oldPassword, String newPassword) {
//        this.oldPassword = oldPassword;
//        this.newPassword = newPassword;
//    }
//
//    public String getOldPassword() {return oldPassword;}
//
//    public void setOldPassword(String oldPassword) {this.oldPassword = oldPassword;}
//
//    public String getNewPassword() {return newPassword;}
//
//    public void setNewPassword(String newPassword) {this.newPassword = newPassword;}


    public boolean isStatus() {return status;}

    public void setStatus(boolean status) {this.status = status;}

    public String getMessage() {return message;}

    public void setMessage(String message) {this.message = message;}
}
