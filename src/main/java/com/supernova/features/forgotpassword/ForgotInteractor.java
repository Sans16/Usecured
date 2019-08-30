package com.supernova.features.forgotpassword;

import com.supernova.datastore.AppRemoteDataStore;
import com.supernova.injector.Injector;
import com.supernova.model.request.ForgotPasswordRequestBody;
import com.supernova.model.response.ForgotPasswordResponseBody;
import com.supernova.utils.app.ServerUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class ForgotInteractor{

    public interface onRequestListener{

        void onRequestSuccess(ForgotPasswordResponseBody body);

        void onRequestFailed(String errorMessage);
    }

    Disposable forgotPassword(ForgotPasswordRequestBody body, onRequestListener listener){
        AppRemoteDataStore appRemoteDataStore = Injector.provideRemoteAppRepository();
        return appRemoteDataStore.forgotPassword(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(ForgotPasswordResponseBodyBaseResponse ->{
                    if(ForgotPasswordResponseBodyBaseResponse.isStatus()){
                        listener.onRequestSuccess(ForgotPasswordResponseBodyBaseResponse.getData());
                    }else{
                        listener.onRequestFailed(ServerUtils.DEFAULT_ERROR_MESSAGE);
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
