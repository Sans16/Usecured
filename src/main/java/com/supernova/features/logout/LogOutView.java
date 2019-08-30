package com.supernova.features.logout;

public interface LogOutView {

    void showProgressDialog(boolean state);

    void showErrorDialog(String message);

    void navigateToLoginPage();
}
