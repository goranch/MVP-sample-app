package com.goranch.shazammvp.api;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Goran Ch on 16/04/16.
 *
 * Provides a single instance of {@link retrofit2.Retrofit}.
 */
public class RestServiceProvider {
    private static ApiService instance;

    public static ApiService getApiServiceInstance() {
        if (instance != null) {
            return instance;
        }

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);

        final Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();


        final OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging).build();

        instance = new Retrofit.Builder()

                .baseUrl("http://cdn.shazam.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build().create(ApiService.class);

        return instance;
    }
}
