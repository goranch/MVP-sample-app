package com.goranch.publicapis.ui.food;

import android.util.Log;

import com.goranch.publicapis.api.FoodService;
import com.goranch.publicapis.api.model.food.ApiResult;
import com.goranch.publicapis.api.model.food.Recipe;
import com.goranch.publicapis.api.model.food.RecipeContainer;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class FoodDataRepositoryImpl implements IDataRepository {
    private static final String TAG = FoodDataRepositoryImpl.class.getSimpleName();
    private final FoodService mFoodService;

    public FoodDataRepositoryImpl(FoodService foodService) {
        mFoodService = foodService;
    }

    @Override
    public Observable<List<Recipe>> searchRecipes(final String apiKey, String searchQuery) {

        return mFoodService.searchRecipes(apiKey, searchQuery)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(ApiResult::getRecipes)
                .doOnError(throwable -> Log.e("http error", throwable.toString()));
    }

    @Override
    public Observable<Recipe> getRecipe(String apiKey, String recipeId) {

        return mFoodService.getRecipe(apiKey, recipeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(RecipeContainer::getRecipe)
                .doOnError(throwable -> Log.e("http error", throwable.toString()));

    }
}
