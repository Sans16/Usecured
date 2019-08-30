package com.supernova.features.changepassword;

import com.supernova.model.request.ChangePasswordRequestBody;
import com.supernova.model.response.ChangePasswordResponseBody;

import io.reactivex.disposables.CompositeDisposable;

public class ChangePasswordPresenter implements ChangePasswordInteractor.onRequestListener {

    private ChangePasswordInteractor changePasswordInteractor;
    private ChangePasswordView changePasswordView;
    private CompositeDisposable disposable;

    public ChangePasswordPresenter(ChangePasswordInteractor changePasswordInteractor, ChangePasswordView changePasswordView){
        this.changePasswordInteractor = changePasswordInteractor;
        this.changePasswordView = changePasswordView;
        disposable = new CompositeDisposable();
    }

    void onDestroy(){
        changePasswordView = null;
        if(disposable!=null || !disposable.isDisposed()){
            disposable.dispose();
        }
    }

    public void proceed(String oldPassword,String newPassword,String accessToken){
        changePasswordView.showProgress(true);
        changePasswordInteractor.changePassword(new ChangePasswordRequestBody(oldPassword,newPassword),accessToken,this);
    }
    @Override
    public void onRequestFailed(String errorMessage) {
        if(changePasswordView==null){return;}
        changePasswordView.showProgress(false);
        changePasswordView.showErrorDialog(errorMessage);
    }

    @Override
    public void onRequestSuccess(String message) {
        if(changePasswordView!=null){
            changePasswordView.showProgress(false);
            changePasswordView.navigateBack(message);
        }
    }
}
