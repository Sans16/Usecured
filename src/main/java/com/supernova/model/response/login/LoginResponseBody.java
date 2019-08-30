package com.supernova.model.response.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginResponseBody {

    @Expose
    @SerializedName("access_token")
    private String accessToken;

    @Expose
    @SerializedName("status")
    private boolean status;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    //    @Expose
//    @SerializedName("user")
//    private Data userData;
//
//    @Expose
//    @SerializedName("emergencycontacts")
//    List<EmergencyContacts> emergencyContactsList;
//
    public String getAccessToken() {return accessToken;}
//
    public void setAccessToken(String accessToken) {this.accessToken = accessToken;}

 //   public Data getUserData() {return userData;}
//
//    public void setUserData(Data userData) {this.userData = userData;}
//
//    public List<EmergencyContacts> getEmergencyContactsList() {
//        return emergencyContactsList;
//    }
//
//    public void setEmergencyContactsList(List<EmergencyContacts> emergencyContactsList) {
//        this.emergencyContactsList = emergencyContactsList;
//    }
}
