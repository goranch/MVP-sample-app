package com.goranch.publicapis.ui.food;

import com.goranch.publicapis.api.FoodService;
import com.goranch.publicapis.api.model.food.Recipe;

import java.util.ArrayList;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by goranch on 30/03/16.
 */
public class FoodDataRepositoryImpl implements IDataRepository {
    private final FoodService mFoodService;

    public FoodDataRepositoryImpl(FoodService foodService) {
        mFoodService = foodService;
    }

    @Override
    public void searchRecipes(final String apiKey, String searchQuery, Callback<ArrayList<Recipe>> callback) {

        final Observable<ArrayList<Recipe>> recipeListObservable = mFoodService.searchRecipes(apiKey, searchQuery)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(recipes -> {
                    final ArrayList<Recipe> recipeArrayList = recipes.getRecipes();
                    callback.onDataUpdated(recipeArrayList);
                    return recipeArrayList;
                });

        // handle http error
        recipeListObservable.subscribe(recipes -> {
        }, callback::onError);
    }

    @Override
    public void getRecipe(String apiKey, String recipeId, Callback<Recipe> recipeCallback) {

        final Observable<Recipe> recipeObservable = mFoodService.getRecipe(apiKey, recipeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(recipe -> recipe);

        recipeObservable.subscribe(recipe -> {
        }, recipeCallback::onError);
    }
}
