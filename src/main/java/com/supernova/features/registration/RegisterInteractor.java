package com.supernova.features.registration;

import com.supernova.datastore.AppRemoteDataStore;
import com.supernova.injector.Injector;
import com.supernova.model.request.RegisterRequestBody;
import com.supernova.model.response.register.RegisterResponseBody;
import com.supernova.utils.app.ServerUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class RegisterInteractor {

    public interface onRequestFinishedListener{
        void onRequestSuccess(RegisterResponseBody data);
        void onRequestFailed(String errorMessage);
    }

    Disposable registerUser(RegisterRequestBody body, onRequestFinishedListener listener){
        AppRemoteDataStore appRemoteDataStore = Injector.provideRemoteAppRepository();
        return appRemoteDataStore.registerUser(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(registerResponseBodyBaseResponse -> {
                    if(registerResponseBodyBaseResponse.isStatus()){
                        listener.onRequestSuccess(registerResponseBodyBaseResponse.getData());
                    }else{
                        listener.onRequestFailed(ServerUtils.DEFAULT_ERROR_MESSAGE);
                    }
                },throwable -> {
                    if(throwable instanceof HttpException){
                        //listener.onRequestFailed(ServerUtils.NETWORK_ERROR_MESSAGE);
                       int code= ((HttpException) throwable).code();
                       switch(code){
                           case 422:
                               String codesStr = "Either Phone number or email has already been taken";
                               listener.onRequestFailed(codesStr);
                               break;

                           case 500:
                               listener.onRequestFailed(ServerUtils.NETWORK_ERROR_MESSAGE);
                               break;

                               default:
                                   listener.onRequestFailed(ServerUtils.NETWORK_ERROR_MESSAGE);
                       }


                    }else{
                        listener.onRequestFailed(ServerUtils.DEFAULT_ERROR_MESSAGE);
                    }
//                    Log.e(AppKeys.TAG, "loginUser: "+ throwable.getMessage(),
//                            throwable);
                        }


                );
    }
}
