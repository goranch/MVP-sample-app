package com.goranch.shazammvp.api;

import android.app.Application;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.jakewharton.byteunits.DecimalByteUnit.MEGABYTES;

/**
 * Created by Goran Ch on 16/04/16.
 * <p>
 * Provides a single instance of {@link retrofit2.Retrofit}.
 */
@Module
public final class ApiModule {
    private static final long DISK_CACHE_SIZE = (int) MEGABYTES.toBytes(50);
    private static final HttpUrl PRODUCTION_API_URL = HttpUrl.parse("http://cdn.shazam.com");
//    private static ApiService instance;

    private OkHttpClient.Builder createApiClient(OkHttpClient client) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return client.newBuilder()
                .addInterceptor(interceptor);
    }

    private OkHttpClient.Builder createOkHttpClient(Application app) {
        // Install an HTTP cache in the application cache directory.
        File cacheDir = new File(app.getCacheDir(), "http");
        Cache cache = new Cache(cacheDir, DISK_CACHE_SIZE);

        return new OkHttpClient.Builder()
                .cache(cache);
    }

    @Provides
    OkHttpClient provideOkHttpClient(Application app) {
        return createOkHttpClient(app).build();
    }

    @Provides
    Gson provideGson() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

    @Provides
    HttpUrl provideBaseUrl() {
        return PRODUCTION_API_URL;
    }

    @Provides
    Retrofit provideRetrofit(HttpUrl baseUrl, OkHttpClient client, Gson gson) {
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Provides
    OkHttpClient provideApiClient(OkHttpClient client) {
        return createApiClient(client).build();
    }

    @Provides
    ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

//    public static ApiService getApiServiceInstance() {
//        if (instance != null) {
//            return instance;
//        }
//
////        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
////        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
//
//        final Gson gson = new GsonBuilder()
//                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
//                .create();
//
//
////        final OkHttpClient client = new OkHttpClient.Builder()
////                .addInterceptor(logging).build();
//
//        instance = new Retrofit.Builder()
//
//                .baseUrl("http://cdn.shazam.com")
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .client(client)
//                .build().create(ApiService.class);
//
//        return instance;
//    }
}
