package com.goranch.publicapis.di;

import android.app.Application;

import com.goranch.publicapis.PublicAPIsApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by goran on 10/09/2016.
 */

@Module
public class PublicApisModule {
    private final PublicAPIsApp app;

    public PublicApisModule(PublicAPIsApp app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return app;
    }
}
