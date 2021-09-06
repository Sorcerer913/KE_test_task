package com.maxemelyanov.prod.api;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIServerConstructor {

    public static <T> T CreateService(Class<T> serviceClass/*, Cache cache*/){

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
//                .cache(cache)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIConfig.HOST_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(serviceClass);
    }
}
