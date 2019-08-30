package com.supernova.features.login;

import com.supernova.datastore.AppRemoteDataStore;
import com.supernova.injector.Injector;
import com.supernova.model.request.LoginRequestBody;
import com.supernova.model.response.login.LoginResponseBody;
import com.supernova.utils.app.ServerUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class LoginInteractor {

    public interface onRequestListener{
        void onRequestSuccess(LoginResponseBody token);
        void onRequestFailed(String errorMessage);
    }

    Disposable login(LoginRequestBody body, onRequestListener listener) {
        AppRemoteDataStore appRemoteDataStore = Injector.provideRemoteAppRepository();
        return appRemoteDataStore.login(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(LoginResponseBody -> {
                            if (LoginResponseBody.isStatus()) {
                                listener.onRequestSuccess(LoginResponseBody);
                             //  String accessToken = loginResponseBodyBaseResponse.getData().getAccessToken();
                                //SessionManager.setAccessToken("ffggb");
                            } else {
                                listener.onRequestFailed(ServerUtils.DEFAULT_ERROR_MESSAGE);
                            }
                        }, throwable -> {
                            if (throwable instanceof HttpException) {
                                listener.onRequestFailed(ServerUtils.NETWORK_ERROR_MESSAGE);
                            } else {
                                listener.onRequestFailed(ServerUtils.DEFAULT_ERROR_MESSAGE);
                            }
//                    Log.e(AppKeys.TAG, "loginUser: "+ throwable.getMessage(),
//                            throwable);
                        }


                );
    }

}
