package com.goranch.shazammvp.api;

import android.app.Application;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.goranch.shazammvp.di.scopes.ApiScope;

import java.io.File;

import javax.inject.Named;

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
    private static final HttpUrl SHAZAM_API_URL = HttpUrl.parse("http://cdn.shazam.com");
    private static final HttpUrl FOOD_API_URL = HttpUrl.parse("http://food2fork.com");

    private static final long DISK_CACHE_SIZE = (int) MEGABYTES.toBytes(25);
    private static final long FOOD_CACHE_SIZE = (int) MEGABYTES.toBytes(25);

    private OkHttpClient.Builder createApiClient(OkHttpClient client) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return client.newBuilder()
                .addInterceptor(interceptor);
    }

    private OkHttpClient.Builder createOkHttpClient(Application app) {
        // Install an HTTP cache in the application cache directory.
        File cacheDir = new File(app.getCacheDir(), "shazam");
        Cache cache = new Cache(cacheDir, DISK_CACHE_SIZE);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .cache(cache);
    }

    @Provides
    @ApiScope
    OkHttpClient provideOkHttpClient(Application app) {
        return createOkHttpClient(app).build();
    }

    @Provides
    @ApiScope
    Gson provideGson() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

    @Provides
    @ApiScope
    @Named("shazam")
    HttpUrl provideBaseUrl() {
        return SHAZAM_API_URL;
    }


    @Provides
    @ApiScope
    @Named("shazam")
    OkHttpClient provideApiClient(OkHttpClient client) {
        return createApiClient(client).build();
    }


    @Provides
    @ApiScope
    @Named("shazam")
    Retrofit provideRetrofit(@Named("shazam") HttpUrl baseUrl, @Named("shazam") OkHttpClient client, Gson gson) {
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Provides
    @ApiScope
    ApiService provideShazamService(@Named("shazam") Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    @Provides
    @ApiScope
    @Named("food")
    HttpUrl provideFoodBaseUrl() {
        return FOOD_API_URL;
    }

    @Provides
    @ApiScope
    @Named("food")
    OkHttpClient provideFoodClient(OkHttpClient client) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        return client.newBuilder()
                .addInterceptor(interceptor).build();
    }

    @Provides
    @ApiScope
    @Named("food")
    Retrofit provideFoodRetrofit(@Named("food") HttpUrl baseUrl, @Named("food") OkHttpClient client, Gson gson) {
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Provides
    @ApiScope
    FoodService provideFoodService(@Named("food") Retrofit retrofit) {
        return retrofit.create(FoodService.class);
    }
}
