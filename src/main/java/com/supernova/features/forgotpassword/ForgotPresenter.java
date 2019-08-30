package com.supernova.features.forgotpassword;

import com.supernova.model.request.ForgotPasswordRequestBody;
import com.supernova.model.response.ForgotPasswordResponseBody;

import io.reactivex.disposables.CompositeDisposable;

public class ForgotPresenter implements ForgotInteractor.onRequestListener {

   private ForgotView forgotView;
   private ForgotInteractor forgotInteractor;
   private CompositeDisposable disposable;

    public ForgotPresenter(ForgotView forgotView, ForgotInteractor forgotInteractor) {
        this.forgotView = forgotView;
        this.forgotInteractor = forgotInteractor;
        disposable = new CompositeDisposable();
    }

    void proceed(String email){
        forgotView.showProgress(true);
        forgotInteractor.forgotPassword(new ForgotPasswordRequestBody(email),this);
    }

    void onDestroy(){
        forgotView = null;
        if(!disposable.isDisposed() && disposable!=null){
            disposable.dispose();
        }
    }

    @Override
    public void onRequestSuccess(ForgotPasswordResponseBody body) {
        if(forgotView == null){
            return;
        }

        forgotView.showProgress(false);
        forgotView.navigate();
    }

    @Override
    public void onRequestFailed(String errorMessage) {
        if(forgotView!=null){
            forgotView.showProgress(false);
            forgotView.showErrorDialog(errorMessage);
        }
    }
}
