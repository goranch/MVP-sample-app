package com.goranch.publicapis.ui.home

import com.goranch.publicapis.MainActivity
import com.goranch.publicapis.api.ApiComponent
import com.goranch.publicapis.di.scopes.HomeScope

import dagger.Component

@HomeScope
@Component(dependencies = arrayOf(ApiComponent::class), modules = arrayOf(HomeModule::class))
internal interface HomeComponent {
    fun inject(mainActivity: MainActivity)
}
