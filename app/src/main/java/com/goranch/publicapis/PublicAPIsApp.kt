package com.goranch.publicapis

import android.app.Application

import com.facebook.drawee.backends.pipeline.Fresco
import com.goranch.publicapis.api.ApiComponent
import com.goranch.publicapis.api.ApiModule
import com.goranch.publicapis.api.DaggerApiComponent
import com.goranch.publicapis.di.ComponentProvider
import com.goranch.publicapis.di.DaggerPublicApisComponent
import com.goranch.publicapis.di.PublicApisComponent
import com.goranch.publicapis.di.PublicApisModule

class PublicAPIsApp : Application(), ComponentProvider<ApiComponent> {

    companion object {

        @JvmStatic lateinit var mainComponent: PublicApisComponent

        @JvmStatic lateinit var apiComponent: ApiComponent

    }


    override fun onCreate() {
        super.onCreate()

        mainComponent = DaggerPublicApisComponent.builder()
                .publicApisModule(PublicApisModule(this))
                .build()

        apiComponent = DaggerApiComponent.builder().publicApisComponent(mainComponent)
                .apiModule(ApiModule())
                .build()

        // Fresco for image loading
        Fresco.initialize(this)
    }

    override val component: ApiComponent
        get() = apiComponent
}
