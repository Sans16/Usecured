package com.supernova.app;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.multidex.MultiDex;
import com.supernova.BuildConfig;
import com.supernova.R;
import com.supernova.utils.manager.SessionManager;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;

public class UsecuredApp extends Application {

    @Override
    public void onCreate() {
        initStrictModeWatch();
        super.onCreate();

        initManagers();
        initCalligraphy();
        enableVectorResources();
    }

    private void initStrictModeWatch() {
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectNetwork()   // or .detectAll() for all detectable problems
                    .penaltyLog()
                    .build());

            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects()
                    .detectLeakedClosableObjects()
                    .penaltyLog()
                    .penaltyDeath()
                    .build());
        }
    }

    private void initManagers() {
        SessionManager.init(getApplicationContext());
    }

    private void initCalligraphy() {
        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setDefaultFontPath("fonts/OpenSans-Regular.ttf")
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build());
    }

    private void enableVectorResources() {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
