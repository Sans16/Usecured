package com.supernova.features.registration;

public interface RegisterView {

    void showProgress(boolean show);

    void showErrorDialog(String message);

    void navigateContactPage();
}
