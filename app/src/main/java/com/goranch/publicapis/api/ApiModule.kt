package com.goranch.publicapis.api

import android.app.Application
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.goranch.publicapis.di.scopes.ApiScope
import com.jakewharton.byteunits.DecimalByteUnit
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import javax.inject.Named

/**
 * Provides a single instance of [retrofit2.Retrofit].
 */
@Module
class ApiModule {
    //    private static final long FOOD_CACHE_SIZE = (int) MEGABYTES.toBytes(25);

    fun createApiClient(client: OkHttpClient): OkHttpClient.Builder {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return client.newBuilder()
                .addInterceptor(interceptor)
    }

    fun createOkHttpClient(app: Application): OkHttpClient.Builder {
        // Install an HTTP cache in the application cache directory.
        val cacheDir = File(app.cacheDir, "http")
        val cache = Cache(cacheDir, DISK_CACHE_SIZE)

        return OkHttpClient.Builder()
                .cache(cache)
    }

    @Provides
    @ApiScope
    fun provideOkHttpClient(app: Application): OkHttpClient {
        return createOkHttpClient(app).build()
    }

    @Provides
    @ApiScope
    fun provideGson(): Gson {
        return GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
    }

    @Provides
    @ApiScope
    @Named(FOOD)
    fun provideFoodBaseUrl(): HttpUrl {
        return RECIPE_API_URL!!
    }

    @Provides
    @ApiScope
    @Named(FOOD)
    fun provideFoodClient(client: OkHttpClient): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        return client.newBuilder()
                .addInterceptor(interceptor).build()
    }

    @Provides
    @ApiScope
    @Named(FOOD)
    fun provideFoodRetrofit(@Named(FOOD) baseUrl: HttpUrl, @Named(FOOD) client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides
    @ApiScope
    fun provideFoodService(@Named(FOOD) retrofit: Retrofit): FoodService {
        return retrofit.create(FoodService::class.java)
    }

    // Nutritionix API specific
    @Provides
    @ApiScope
    @Named(NUTRITION)
    fun provideNutritionUrl(): HttpUrl {
        return NUTRITION_API_URL!!
    }

    @Provides
    @ApiScope
    @Named(NUTRITION)
    fun provideNutritionClient(client: OkHttpClient): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return client.newBuilder()
                .addInterceptor(interceptor).build()
    }

    @Provides
    @ApiScope
    @Named(NUTRITION)
    fun provideNutritionFoodRetrofit(@Named(NUTRITION) baseUrl: HttpUrl, @Named(NUTRITION) client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides
    @ApiScope
    fun provideNutritionService(@Named(NUTRITION) retrofit: Retrofit): NutritionService {
        return retrofit.create(NutritionService::class.java)
    }

    companion object {
        val RECIPE_API_KEY = "b549c4c96152e677eb90de4604ca61a2"
        const val NUTRITION_API_KEY = "741d0c38b2b6657c90127024404db573"
        const val NUTRITION_APP_ID = "e7319e27"
        private val RECIPE_API_URL = HttpUrl.parse("http://food2fork.com")
        private val NUTRITION_API_URL = HttpUrl.parse("https://trackapi.nutritionix.com")
        private val SHAZAM_API_URL = HttpUrl.parse("http://cdn.shazam.com")
        private val DISK_CACHE_SIZE = DecimalByteUnit.MEGABYTES.toBytes(50).toInt().toLong()
        const private val NUTRITION = "nutrition"
        const private val FOOD = "food"
    }

}


