package com.supernova.features.changepassword;

public interface ChangePasswordView {

    void showProgress(boolean state);

    void showErrorDialog(String error);

    void navigateBack(String message);
}
