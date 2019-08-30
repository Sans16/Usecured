package com.supernova.features.changepassword;

import com.supernova.datastore.AppRemoteDataStore;
import com.supernova.injector.Injector;
import com.supernova.model.request.ChangePasswordRequestBody;
import com.supernova.model.response.ChangePasswordResponseBody;
import com.supernova.utils.app.ServerUtils;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class ChangePasswordInteractor {

    public interface onRequestListener{
        void onRequestFailed(String errorMessage);

        void onRequestSuccess(String successMessage);
    }

    Disposable changePassword(ChangePasswordRequestBody body,String accessToken, onRequestListener listener){
        AppRemoteDataStore appRemoteDataStore = Injector.provideRemoteAppRepository();
        return  appRemoteDataStore.changePassword(body,accessToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(ChangePasswordResponseBody ->{
                    if(ChangePasswordResponseBody.isStatus()){
                        listener.onRequestSuccess(ChangePasswordResponseBody.getMessage());
                    }else{
                        listener.onRequestFailed(ServerUtils.NETWORK_ERROR_MESSAGE);
                    }
                }, throwable ->{
                            if(throwable instanceof HttpException){
                             listener.onRequestFailed(ServerUtils.NETWORK_ERROR_MESSAGE);
                            }else {
                                listener.onRequestFailed(ServerUtils.DEFAULT_ERROR_MESSAGE);
                            }
                        }
                );
    }
}
