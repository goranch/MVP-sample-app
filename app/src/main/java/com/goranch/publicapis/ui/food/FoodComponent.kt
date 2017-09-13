package com.goranch.publicapis.ui.food

import com.goranch.publicapis.api.ApiComponent
import com.goranch.publicapis.di.scopes.FoodScope
import com.goranch.publicapis.ui.food.fragment.FoodFragment

import dagger.Component


@FoodScope
@Component(dependencies = arrayOf(ApiComponent::class), modules = arrayOf(FoodModule::class))
interface FoodComponent {
    fun inject(foodFragment: FoodFragment)
}
