package com.goranch.publicapis.ui.food.details

import com.goranch.publicapis.api.ApiComponent
import com.goranch.publicapis.di.scopes.FoodDetailScope
import com.goranch.publicapis.ui.food.fragment.DetailsFragment

import dagger.Component

@FoodDetailScope
@Component(dependencies = arrayOf(ApiComponent::class), modules = arrayOf(DetailsFoodModule::class))
interface DetailsFoodComponent {
    fun inject(detailsFragment: DetailsFragment)
}
