package com.supernova.model.request.emergencycontact;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EmergencyContactRequestBody {

    @Expose
    @SerializedName("contacts")
    List<Contacts> contacts;

    public EmergencyContactRequestBody(List<Contacts> contacts) {
        this.contacts = contacts;
    }

    public List<Contacts> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contacts> contacts) {
        this.contacts = contacts;
    }
}
