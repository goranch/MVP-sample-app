package com.goranch.publicapis.di

import android.app.Application
import com.goranch.publicapis.PublicAPIsApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PublicApisModule(private val app: PublicAPIsApp) {

    @Provides
    @Singleton
    fun provideApplication(): Application {
        return app
    }
}
