package com.goranch.publicapis.ui.food;

import com.goranch.publicapis.api.FoodService;
import com.goranch.publicapis.di.scopes.FoodScope;

import dagger.Module;
import dagger.Provides;

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

//    @Provides
//    @FoodScope
//    FoodViewModel provideFoodViewModel(IDataRepository repository, SearchRecipeView view) {
//        return ViewModelProviders.of((LifecycleFragment) view, new FoodViewModel.Factory(repository, view)).get(FoodViewModel.class);
//    }

}
