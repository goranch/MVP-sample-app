package com.goranch.shazammvp;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.goranch.shazammvp.api.ApiComponent;
import com.goranch.shazammvp.api.ApiModule;
import com.goranch.shazammvp.api.DaggerApiComponent;
import com.goranch.shazammvp.di.ComponentProvider;
import com.goranch.shazammvp.di.DaggerShazamComponent;
import com.goranch.shazammvp.di.ShazamComponent;
import com.goranch.shazammvp.di.ShazamModule;

/**
 * Created by Goran Ch on 16/04/16.
 */
public class ShazamMVPApp extends Application implements ComponentProvider<ApiComponent> {
    private ApiComponent apiComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        final ShazamComponent shazamComponent = DaggerShazamComponent.builder()
                .shazamModule(new ShazamModule(this))
                .build();

        apiComponent = DaggerApiComponent.builder()
                .shazamComponent(shazamComponent)
                .apiModule(new ApiModule())
                .build();

        // Fresco for image loading
        Fresco.initialize(this);
    }

    @Override
    public ApiComponent getComponent() {
        return apiComponent;
    }
}
