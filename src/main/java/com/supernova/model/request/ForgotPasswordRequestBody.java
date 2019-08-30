package com.supernova.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForgotPasswordRequestBody {

    @Expose
    @SerializedName("email")
    String email;

    public ForgotPasswordRequestBody(String email) {
        this.email = email;
    }

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}
}
