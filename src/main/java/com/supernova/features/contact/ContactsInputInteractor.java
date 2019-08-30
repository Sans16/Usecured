package com.supernova.features.contact;

import com.supernova.datastore.AppRemoteDataStore;
import com.supernova.injector.Injector;
import com.supernova.model.request.ContactsInputRequestBody;
import com.supernova.model.request.emergencycontact.EmergencyContactRequestBody;
import com.supernova.model.response.ContactsInputResponseBody;
import com.supernova.model.response.EmergencyContactsResponseBody;
import com.supernova.model.response.EmergencyResponseBody;
import com.supernova.utils.app.ServerUtils;

import java.util.concurrent.ThreadPoolExecutor;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class ContactsInputInteractor {

    public interface onRequestListener{

        void onRequestSuccess(EmergencyContactsResponseBody data);

        void onRequestFailed(String errorMessage);

    }

    Disposable addContacts(EmergencyContactRequestBody body, String accessToken, onRequestListener listener){
        AppRemoteDataStore appRemoteDataStore = Injector.provideRemoteAppRepository();

        return appRemoteDataStore.emergencyContacts(body,accessToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(EmergencyContactsResponseBody->{
                    if(EmergencyContactsResponseBody.isStatus()){
                        listener.onRequestSuccess(EmergencyContactsResponseBody);
                    }else{
                        listener.onRequestFailed(ServerUtils.NETWORK_ERROR_MESSAGE);
                    }
                }, throwable -> {
                    if(throwable instanceof HttpException){
                        String errorMessage = throwable.getMessage();
                        int code = ((HttpException) throwable).code();
                        listener.onRequestFailed("The code is "+code);
                       // listener.onRequestFailed(ServerUtils.NETWORK_ERROR_MESSAGE);

                    }else{
                        listener.onRequestFailed(ServerUtils.DEFAULT_ERROR_MESSAGE);
                    }
                        }
                );
    }
}
