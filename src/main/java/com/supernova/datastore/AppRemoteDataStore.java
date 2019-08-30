package com.supernova.datastore;

import com.supernova.injector.Injector;
import com.supernova.model.request.ChangePasswordRequestBody;
import com.supernova.model.request.LoginRequestBody;
import com.supernova.model.request.RegisterRequestBody;
import com.supernova.model.request.ForgotPasswordRequestBody;
import com.supernova.model.request.emergencycontact.EmergencyContactRequestBody;
import com.supernova.model.response.ChangePasswordResponseBody;
import com.supernova.model.response.EmergencyContactsResponseBody;
import com.supernova.model.response.EmergencyResponseBody;
import com.supernova.model.response.ForgotPasswordResponseBody;
import com.supernova.model.response.login.LoginResponseBody;
import com.supernova.model.response.register.RegisterResponseBody;
import com.supernova.network.ServiceGenerator;
import com.supernova.shared.base.base.BaseResponse;

import io.reactivex.Single;

public class AppRemoteDataStore implements AppDataStore{
    private ServiceGenerator serviceGenerator;

    public AppRemoteDataStore() {
       serviceGenerator = Injector.getServiceGenerator();
    }

    @Override
    public Single<BaseResponse<RegisterResponseBody>> registerUser(RegisterRequestBody body) {
        return Single.defer(()-> serviceGenerator.registerUser(body,"application/json","application/json"));
    }

    @Override
    public Single<LoginResponseBody> login(LoginRequestBody body) {
        return Single.defer(()-> serviceGenerator.login(body));
    }

    @Override
    public Single<BaseResponse<ForgotPasswordResponseBody>> forgotPassword(ForgotPasswordRequestBody body) {
        return Single.defer(()-> serviceGenerator.forgotPassword(body,"application/json","application/json"));
    }

    @Override
    public Single<ChangePasswordResponseBody> changePassword(ChangePasswordRequestBody body,String accessToken) {
        return Single.defer(()-> serviceGenerator.changePassword(body,"application/json","application/json",accessToken));
    }

    @Override
    public Single<EmergencyContactsResponseBody> emergencyContacts(EmergencyContactRequestBody body, String accessToken) {
        return Single.defer(()-> serviceGenerator.emergencyContacts(body,"application/json","application/json",accessToken));
    }


    @Override
    public Single<EmergencyResponseBody> emergencyRequested(String accessToken,double q) {
        return Single.defer(()-> serviceGenerator.emergencyRequested("application/json","application/json",accessToken,q));
    }
}
