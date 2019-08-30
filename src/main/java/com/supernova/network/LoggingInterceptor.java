package com.supernova.network;

import android.util.Log;

import androidx.annotation.NonNull;

import com.supernova.utils.manager.SessionManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class LoggingInterceptor implements Interceptor {
    private String TAG = LoggingInterceptor.class.getName();

    @Override public Response intercept(@NonNull Interceptor.Chain chain) throws IOException {
        Request originalRequest = chain.request();
      //  String token = "Bearer " + SessionManager.getAppToken();
        String token = "Bearer "+ SessionManager.getAccessToken();
        Request newRequest = originalRequest.newBuilder()
                .addHeader("Authorization", token)
                .build();

        long t1 = System.nanoTime();
        Log.d(TAG, String.format("Retrofit Sending request %s on %s%n%sn%s",
                newRequest.url(), chain.connection(), newRequest.headers(), newRequest.body()));

        Response response = chain.proceed(newRequest);

        long t2 = System.nanoTime();
        Log.d(TAG, String.format("%s", response.networkResponse()));
        Log.d(TAG, String.format("Retrofit Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers()));

        return response;
    }
}
