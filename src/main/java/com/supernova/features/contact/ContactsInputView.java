package com.supernova.features.contact;

import com.supernova.model.response.EmergencyContactsResponseBody;

public interface ContactsInputView {

    void showProgress(boolean state);

    void showErrorDialog(String error);

    void navigateToHomePage(EmergencyContactsResponseBody data);
}
