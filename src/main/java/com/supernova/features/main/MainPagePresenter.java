package com.supernova.features.main;

import com.supernova.model.response.EmergencyResponseBody;

import io.reactivex.disposables.CompositeDisposable;

public class MainPagePresenter implements MainPageInteractor.onRequestListener {

    private MainPageView mainPageView;
    private MainPageInteractor mainPageInteractor;
    private CompositeDisposable disposable;

    public MainPagePresenter(MainPageView mainPageView,
                             MainPageInteractor mainPageInteractor) {
        this.mainPageView = mainPageView;
        this.mainPageInteractor = mainPageInteractor;
        disposable = new CompositeDisposable();
    }

    void onDestroy(){
        mainPageView = null;
        if(disposable!=null || !disposable.isDisposed()){
            disposable = null;
        }
    }

    public void emergencyRequested(String accessToken,double q){
        mainPageView.showProgress(true);
        mainPageInteractor.emergencyRequested(accessToken,q,this);
    }

    @Override
    public void onRequestSuccess(EmergencyResponseBody data) {
        if(mainPageView !=null){
            mainPageView.showProgress(false);
            mainPageView.showSuccessInfo(data);
        }
    }

    @Override
    public void onRequestFailed(String error) {
        if(mainPageView !=null){
            mainPageView.showProgress(false);
            mainPageView.showErrorDialog(error);
        }
    }
}
