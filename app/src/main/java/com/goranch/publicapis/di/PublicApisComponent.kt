package com.goranch.publicapis.di

import android.app.Application
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(PublicApisModule::class))
interface PublicApisComponent {
    // Expose to subgraphs
    fun application(): Application
}