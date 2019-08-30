package com.supernova.model.response.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @Expose
    @SerializedName("firstName")
    String firstName;

    @Expose
    @SerializedName("lastName")
    String lastName;

    @Expose
    @SerializedName("email")
    String email;

    @Expose
    @SerializedName("phonenumber")
    String phoneNumber;

    public String getFirstName() {return firstName;}

    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}

    public void setLastName(String lastName) {this.lastName = lastName;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getPhoneNumber() {return phoneNumber;}

    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
}
