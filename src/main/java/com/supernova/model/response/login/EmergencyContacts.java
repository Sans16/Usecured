package com.supernova.model.response.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EmergencyContacts {

    @Expose
    @SerializedName("name")
    String name;

    @Expose
    @SerializedName("email")
    String email;

    @Expose
    @SerializedName("phonenumber")
    String phoneNumber;

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getPhoneNumber() {return phoneNumber;}

    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
}
