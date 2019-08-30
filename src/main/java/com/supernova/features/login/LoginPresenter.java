package com.supernova.features.login;

import com.supernova.model.request.LoginRequestBody;
import com.supernova.model.response.login.LoginResponseBody;
import com.supernova.utils.app.ServerUtils;

import io.reactivex.disposables.CompositeDisposable;

public class LoginPresenter implements LoginInteractor.onRequestListener {
    private LoginView loginView;
    private LoginInteractor loginInteractor;
    private CompositeDisposable disposable;

    public LoginPresenter(LoginView loginView, LoginInteractor loginInteractor) {
        this.loginView = loginView;
        this.loginInteractor = loginInteractor;
        disposable = new CompositeDisposable();
    }

    void onDestroy(){
        loginView = null;
        if(!disposable.isDisposed() || disposable!=null){
            disposable.dispose();
        }
    }

    public void proceed(String email, String password){
        loginView.showProgress(true);
        loginInteractor.login(new LoginRequestBody(email,password),this);
    }

    @Override
    public void onRequestSuccess(LoginResponseBody data) {
        if(loginView == null){
            return;
        }
        loginView.showProgress(false);

        loginView.navigateHomePage(data);
    }

    @Override
    public void onRequestFailed(String errorMessage) {
        if(loginView!=null){
            loginView.showProgress(false);
            loginView.showErrorDialog(errorMessage);
        }
    }
}
