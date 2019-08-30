package com.supernova.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginRequestBody {

    @Expose
    @SerializedName("email")
    String email;

    @Expose
    @SerializedName("password")
    String password;

    public LoginRequestBody(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
