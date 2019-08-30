package com.supernova.features.main;

import com.supernova.datastore.AppRemoteDataStore;
import com.supernova.injector.Injector;
import com.supernova.model.response.EmergencyResponseBody;
import com.supernova.utils.app.ServerUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class MainPageInteractor {

    public interface onRequestListener{

        void onRequestSuccess(EmergencyResponseBody data);

        void onRequestFailed(String error);
    }

    Disposable emergencyRequested(String accessToken,double q, onRequestListener listener){
        AppRemoteDataStore appRemoteDataStore = Injector.provideRemoteAppRepository();
        return appRemoteDataStore.emergencyRequested(accessToken,q)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(EmergencyResponseBody ->{
                    if(EmergencyResponseBody.isStatus()){
                        listener.onRequestSuccess(EmergencyResponseBody);
                    }else{
                        listener.onRequestFailed(ServerUtils.NETWORK_ERROR_MESSAGE);
                    }
                },throwable -> {
                            if(throwable instanceof HttpException){
                                listener.onRequestFailed(ServerUtils.NETWORK_ERROR_MESSAGE);
                            }else{
                                listener.onRequestFailed(ServerUtils.DEFAULT_ERROR_MESSAGE);
                            }
                        }

                );
    }
}
