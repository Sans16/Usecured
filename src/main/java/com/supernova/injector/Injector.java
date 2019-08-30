package com.supernova.injector;

import com.supernova.datastore.AppRemoteDataStore;
import com.supernova.network.LoggingInterceptor;
import com.supernova.network.ServiceGenerator;
import com.supernova.utils.app.ServerUtils;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Injector {
    private static OkHttpClient okHttpClient;
    private static Retrofit retrofitInstance;
    private static ServiceGenerator serviceGenerator;

    private static Retrofit provideRetrofit() {
        if (retrofitInstance == null) {
            Retrofit.Builder retrofit = new Retrofit.Builder().client(Injector.provideOkHttpClient())
                    .baseUrl(ServerUtils.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            retrofitInstance = retrofit.build();

        }
        return retrofitInstance;
    }

    private static OkHttpClient provideOkHttpClient() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .addInterceptor(new LoggingInterceptor()).build();
        }

        return okHttpClient;
    }

    public static ServiceGenerator getServiceGenerator() {
        if (serviceGenerator == null) {
            serviceGenerator = provideRetrofit().create(ServiceGenerator.class);
        }

        return serviceGenerator;
    }

    public static AppRemoteDataStore provideRemoteAppRepository() {
        return new AppRemoteDataStore();
    }
}
