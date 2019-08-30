package com.supernova.utils.app;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ServerUtils {

    // Properties-----------------------------------------------------------------------------------
    public static final String BASE_URL = "https://limitless-oasis-29895.herokuapp.com/api/v1/";

    // Server Error Messages
    public static final String DEFAULT_ERROR_MESSAGE = "Something went wrong! Please try again.";
    public static final String REQUEST_PROCESS_ERROR_MESSAGE = "Something went wrong! Error processing request.";
    public static final String NETWORK_ERROR_MESSAGE = "Error communicating with the server.";
    public static final String NO_NETWORK_ERROR_MESSAGE = "No network connection detected.";

    // Methods--------------------------------------------------------------------------------------
    public static boolean isNetworkUnavailable(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo == null || !activeNetworkInfo.isConnected();
    }
}
