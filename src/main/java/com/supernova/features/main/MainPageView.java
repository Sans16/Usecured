package com.supernova.features.main;

import com.supernova.model.response.EmergencyResponseBody;

public interface MainPageView {

    void showProgress(boolean state);

    void showErrorDialog(String message);

    void showSuccessInfo(EmergencyResponseBody data);

}
