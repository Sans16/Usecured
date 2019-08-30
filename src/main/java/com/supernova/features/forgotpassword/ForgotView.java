package com.supernova.features.forgotpassword;

public interface ForgotView {

    void showProgress(boolean show);

    void showErrorDialog(String error);

    void navigate();
}
