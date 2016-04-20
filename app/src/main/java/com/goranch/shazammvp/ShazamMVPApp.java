package com.goranch.shazammvp;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Goran Ch on 16/04/16.
 */
public class ShazamMVPApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        // Fresco for image loading
        Fresco.initialize(this);
    }
}
