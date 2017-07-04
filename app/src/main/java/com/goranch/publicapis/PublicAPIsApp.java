package com.goranch.publicapis;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.goranch.publicapis.api.ApiComponent;
import com.goranch.publicapis.api.ApiModule;
import com.goranch.publicapis.api.DaggerApiComponent;
import com.goranch.publicapis.di.ComponentProvider;
import com.goranch.publicapis.di.DaggerPublicApisComponent;
import com.goranch.publicapis.di.PublicApisComponent;
import com.goranch.publicapis.di.PublicApisModule;

import io.realm.Realm;

public class PublicAPIsApp extends Application implements ComponentProvider<ApiComponent> {
    private ApiComponent apiComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        final PublicApisComponent mainComponent = DaggerPublicApisComponent.builder()
            .publicApisModule(new PublicApisModule(this))
            .build();

        apiComponent = DaggerApiComponent.builder()
            .publicApisComponent(mainComponent)
            .apiModule(new ApiModule())
            .build();

        // Fresco for image loading
        Fresco.initialize(this);

        // Initialize Realm
        Realm.init(this);
    }

    @Override
    public ApiComponent getComponent() {
        return apiComponent;
    }
}
