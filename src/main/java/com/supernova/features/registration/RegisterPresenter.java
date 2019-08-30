package com.supernova.features.registration;

import com.supernova.model.request.RegisterRequestBody;
import com.supernova.model.response.register.RegisterResponseBody;

import io.reactivex.disposables.CompositeDisposable;

public class RegisterPresenter implements RegisterInteractor.onRequestFinishedListener {

    private RegisterView registerView;
    private RegisterInteractor registerInteractor;
    private CompositeDisposable disposable;

    public RegisterPresenter(RegisterView registerView, RegisterInteractor registerInteractor) {
        this.registerView = registerView;
        this.registerInteractor = registerInteractor;
        this.disposable = new CompositeDisposable();
    }

    void onDestroy(){
        registerView = null;
        if(disposable!=null && !disposable.isDisposed()){
            disposable.dispose();
        }
    }

    void processRegister(String firstName, String lastName, String email,String phoneNumber){
        registerView.showProgress(true);
        registerInteractor.registerUser(new RegisterRequestBody(firstName,lastName,email,phoneNumber),this);
    }
    @Override
    public void onRequestSuccess(RegisterResponseBody data) {
        if(registerView == null){
            return;
        }
        registerView.showProgress(false);
        registerView.navigateContactPage();
    }

    @Override
    public void onRequestFailed(String errorMessage) {
        if(registerView!=null){
            registerView.showProgress(false);
            registerView.showErrorDialog(errorMessage);
        }
    }
}
