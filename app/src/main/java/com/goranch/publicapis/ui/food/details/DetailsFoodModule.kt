package com.goranch.publicapis.ui.food.details

import com.goranch.publicapis.api.FoodService
import com.goranch.publicapis.api.NutritionService
import com.goranch.publicapis.di.scopes.FoodDetailScope
import com.goranch.publicapis.ui.food.repository.FoodDataRepositoryImpl

import dagger.Module
import dagger.Provides

@Module
class DetailsFoodModule(private val mDetailRecipeView: DetailRecipeView) {

    @Provides
    @FoodDetailScope
    internal fun provideDetailRecipeView(): DetailRecipeView {
        return mDetailRecipeView
    }

    @Provides
    @FoodDetailScope
    internal fun provideFoodDataRepo(foodService: FoodService, nutritionService: NutritionService): FoodDataRepositoryImpl {
        return FoodDataRepositoryImpl(foodService, nutritionService)
    }
}
