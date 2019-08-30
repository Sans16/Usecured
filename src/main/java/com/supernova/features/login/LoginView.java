package com.supernova.features.login;

import com.supernova.model.response.login.LoginResponseBody;

public interface LoginView {

    void showProgress(boolean show);

    void showErrorDialog(String error);

    void navigateHomePage(LoginResponseBody data);
}
