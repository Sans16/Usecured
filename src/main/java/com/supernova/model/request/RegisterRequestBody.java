package com.supernova.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterRequestBody {

    @Expose
    @SerializedName("first_name")
    String firstName;

    @Expose
    @SerializedName("last_name")
    String lastName;

    @Expose
    @SerializedName("email")
    String email;

    @Expose
    @SerializedName("phonenumber")
    String address;

    public RegisterRequestBody(String firstName, String lastName, String email, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
    }
}
