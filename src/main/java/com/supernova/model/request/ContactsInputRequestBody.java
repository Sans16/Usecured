package com.supernova.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.supernova.model.request.emergencycontact.Contacts;

import java.util.List;

public class ContactsInputRequestBody {

    @Expose
    @SerializedName("contacts")
    List<Contacts> contactsList;

    public List<Contacts> getContactsList() {return contactsList;}

    public void setContactsList(List<Contacts> contactsList) {this.contactsList = contactsList;}
}
