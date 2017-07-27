package com.goranch.publicapis.api

import android.app.Application

import com.goranch.publicapis.di.PublicApisComponent
import com.goranch.publicapis.di.scopes.ApiScope
import com.goranch.publicapis.ui.home.HomeFragment

import dagger.Component

@ApiScope
@Component(dependencies = arrayOf(PublicApisComponent::class), modules = arrayOf(ApiModule::class))
interface ApiComponent {

    fun inject(homeFragment: HomeFragment)

    // Expose to subgraphs
    fun application(): Application

    fun apiService(): ShazamService

    fun foodService(): FoodService

    fun nutritionService(): NutritionService
}