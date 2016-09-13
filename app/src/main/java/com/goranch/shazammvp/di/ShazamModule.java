package com.goranch.shazammvp.di;

import android.app.Application;

import com.goranch.shazammvp.ShazamMVPApp;

import dagger.Module;
import dagger.Provides;

/**
 * Created by goran on 10/09/2016.
 */

@Module
public class ShazamModule {
    private final ShazamMVPApp app;

    public ShazamModule(ShazamMVPApp app) {
        this.app = app;
    }

    @Provides
    Application provideApplication() {
        return app;
    }
}
