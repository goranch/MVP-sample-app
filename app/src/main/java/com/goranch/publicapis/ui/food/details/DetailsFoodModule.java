package com.goranch.publicapis.ui.food.details;

import com.goranch.publicapis.api.FoodService;
import com.goranch.publicapis.di.scopes.FoodDetailScope;
import com.goranch.publicapis.ui.food.FoodDataRepositoryImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by goran on 06/04/2017.
 */

@Module
public class DetailsFoodModule {
    private final DetailRecipeView mDetailRecipeView;

    public DetailsFoodModule(DetailRecipeView detailRecipeView) {
        mDetailRecipeView = detailRecipeView;
    }

    @Provides
    @FoodDetailScope
    DetailRecipeView provideDetailRecipeView() {
        return mDetailRecipeView;
    }

    @Provides
    @FoodDetailScope
    FoodDataRepositoryImpl provideFoodDataRepo(FoodService foodService) {
        return new FoodDataRepositoryImpl(foodService);
    }
}
