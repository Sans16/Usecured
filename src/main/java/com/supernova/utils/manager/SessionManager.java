package com.supernova.utils.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import com.supernova.BuildConfig;

import static com.supernova.utils.keys.PreferenceKeys.PREFERENCE_KEY_ACCESS_TOKEN;
import static com.supernova.utils.keys.PreferenceKeys.PREFERENCE_KEY_APP_NAME;
import static com.supernova.utils.keys.PreferenceKeys.PREFERENCE_KEY_IS_SEEN_ONBOARDING;

public class SessionManager {

    private static Context mAppContext;

    //This is done to private initialization
    private SessionManager(){/* Empty method */}

    public static void init(Context appContext){
        mAppContext = appContext;
    }

    private static SharedPreferences getSharedPreferences() {
        return mAppContext.getSharedPreferences(PREFERENCE_KEY_APP_NAME, Context.MODE_PRIVATE);
    }

    // Seen Onboarding Screen Section---------------------------------------------------------------
    public static boolean isSeenOnboardingScreen() {
        return getSharedPreferences().getBoolean(PREFERENCE_KEY_IS_SEEN_ONBOARDING, false);
    }

    public static void setSeenOnboardingScreen() {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putBoolean(PREFERENCE_KEY_IS_SEEN_ONBOARDING, true);
        editor.apply();
    }

    // Seen Onboarding Screen Section---------------------------------------------------------------
    public static String getAccessToken() {
        return getSharedPreferences().getString(PREFERENCE_KEY_ACCESS_TOKEN,"");
    }

    public static void setAccessToken(String accessToken) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(PREFERENCE_KEY_ACCESS_TOKEN, accessToken);
        editor.apply();
    }

}
