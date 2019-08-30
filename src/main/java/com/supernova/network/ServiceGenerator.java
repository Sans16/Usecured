package com.supernova.network;

import com.supernova.model.request.ForgotPasswordRequestBody;
import com.supernova.model.request.LoginRequestBody;
import com.supernova.model.request.RegisterRequestBody;
import com.supernova.model.request.ChangePasswordRequestBody;
import com.supernova.model.request.emergencycontact.EmergencyContactRequestBody;
import com.supernova.model.response.EmergencyContactsResponseBody;
import com.supernova.model.response.EmergencyResponseBody;
import com.supernova.model.response.ForgotPasswordResponseBody;
import com.supernova.model.response.login.LoginResponseBody;
import com.supernova.model.response.ChangePasswordResponseBody;
import com.supernova.model.response.register.RegisterResponseBody;
import com.supernova.shared.base.base.BaseResponse;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServiceGenerator {

    @POST("auth/register")
    Single<BaseResponse<RegisterResponseBody>> registerUser(@Body RegisterRequestBody body, @Header("Content-Type") String content,
                                                            @Header("Accept") String accept);

    @POST("auth/login")
    Single<LoginResponseBody> login(@Body LoginRequestBody body);

    @PATCH("password/update")
    Single<ChangePasswordResponseBody> changePassword(@Body ChangePasswordRequestBody body, @Header("Content-Type") String content,
                                                                    @Header("Accept") String accept, @Header("Authorization") String accessToken);;

    @POST("emergencycontacts")
    Single<EmergencyContactsResponseBody> emergencyContacts(@Body EmergencyContactRequestBody body, @Header("Content-Type") String content,
                                                                          @Header("Accept") String accept, @Header("Authorization") String accessToken);

    @POST("password/reset")
    Single<BaseResponse<ForgotPasswordResponseBody>> forgotPassword(@Body ForgotPasswordRequestBody body,@Header("Content-Type") String content,
                                                                    @Header("Accept") String accept);

    @POST("emergency")
    Single<EmergencyResponseBody> emergencyRequested(@Header("Content-Type") String content, @Header("Accept") String accept,
                                                                   @Header("Authorization") String accessToken, @Query("q") double q);

}
