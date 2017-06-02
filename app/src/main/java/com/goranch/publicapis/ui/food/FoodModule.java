package com.goranch.publicapis.ui.food;

import com.goranch.publicapis.api.FoodService;
import com.goranch.publicapis.di.scopes.FoodScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by goran on 21/09/2016.
 */

@Module
public class FoodModule {
    private final SearchRecipeView mSearchRecipeView;

    public FoodModule(SearchRecipeView searchRecipeView) {
        mSearchRecipeView = searchRecipeView;
    }

    @Provides
    @FoodScope
    SearchRecipeView provideSearchRecipeView() {
        return mSearchRecipeView;
    }

    @Provides
    @FoodScope
    FoodDataRepositoryImpl provideFoodDataRepo(FoodService foodService) {
        return new FoodDataRepositoryImpl(foodService);
    }

    @Provides
    @FoodScope
    RecipeListPresenter provideRecipePresenter(SearchRecipeView searchRecipeView) {
        return new RecipeListPresenterImpl(searchRecipeView);
    }

}
