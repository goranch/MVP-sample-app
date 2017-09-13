package com.goranch.publicapis.ui.food

import com.goranch.publicapis.api.FoodService
import com.goranch.publicapis.api.NutritionService
import com.goranch.publicapis.di.scopes.FoodScope
import com.goranch.publicapis.ui.food.repository.FoodDataRepositoryImpl

import dagger.Module
import dagger.Provides

@Module
class FoodModule(private val mSearchRecipeView: SearchRecipeView) {

    @Provides
    @FoodScope
    internal fun provideSearchRecipeView(): SearchRecipeView {
        return mSearchRecipeView
    }

    @Provides
    @FoodScope
    internal fun provideFoodDataRepo(foodService: FoodService, nutritionService: NutritionService): FoodDataRepositoryImpl {
        return FoodDataRepositoryImpl(foodService, nutritionService)
    }

    //    @Provides
    //    @FoodScope
    //    FoodViewModel provideFoodViewModel(IDataRepository repository, SearchRecipeView view) {
    //        return ViewModelProviders.of((LifecycleFragment) view, new FoodViewModel.Factory(repository, view)).get(FoodViewModel.class);
    //    }

}
