package com.supernova.features.contact;
import com.supernova.model.request.emergencycontact.EmergencyContactRequestBody;
import com.supernova.model.response.EmergencyContactsResponseBody;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class ContactsInputPresenter implements ContactsInputInteractor.onRequestListener {

    private ContactsInputView contactsInputView;
    private ContactsInputInteractor contactsInputInteractor;
    private CompositeDisposable disposable;

    public ContactsInputPresenter(ContactsInputView contactsInputView,
                                  ContactsInputInteractor contactsInputInteractor) {
        this.contactsInputView = contactsInputView;
        this.contactsInputInteractor = contactsInputInteractor;
        disposable = new CompositeDisposable();
    }


    void onDestroy(){
        contactsInputInteractor = null;
        if(disposable!=null || !disposable.isDisposed()){
            disposable.dispose();
        }
    }

    public void proceed(EmergencyContactRequestBody contactsAdded,String accessToken){
        contactsInputView.showProgress(true);
        contactsInputInteractor.addContacts(contactsAdded,accessToken,this);
    }

    @Override
    public void onRequestSuccess(EmergencyContactsResponseBody data) {
        if(contactsInputView!=null){
            contactsInputView.showProgress(false);
            // The code to add the shared preference value will be here.
            contactsInputView.navigateToHomePage(data);
        }
    }

    @Override
    public void onRequestFailed(String errorMessage) {
        if(contactsInputView!=null){
            contactsInputView.showProgress(false);
            contactsInputView.showErrorDialog(errorMessage);
        }
    }
}
