package com.supernova.datastore;

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
import com.supernova.shared.base.base.BaseResponse;

import io.reactivex.Single;

public interface AppDataStore {

    Single<BaseResponse<RegisterResponseBody>> registerUser(RegisterRequestBody body);

    Single<LoginResponseBody> login(LoginRequestBody body);

    Single<BaseResponse<ForgotPasswordResponseBody>> forgotPassword(ForgotPasswordRequestBody body);

    Single<ChangePasswordResponseBody> changePassword(ChangePasswordRequestBody body, String accessToken);

    Single<EmergencyContactsResponseBody> emergencyContacts(EmergencyContactRequestBody body, String accessToken);

    Single<EmergencyResponseBody> emergencyRequested(String accessToken,double q);
}
