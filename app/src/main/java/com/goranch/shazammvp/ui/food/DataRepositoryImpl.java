package com.goranch.shazammvp.ui.food;

import com.goranch.shazammvp.api.FoodService;
import com.goranch.shazammvp.api.model.food.Recipe;

import java.util.ArrayList;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by goranch on 30/03/16.
 */
public class DataRepositoryImpl implements IDataRepository {
    private final FoodService mFoodService;

    public DataRepositoryImpl(FoodService foodService) {
        mFoodService = foodService;
    }

    @Override
    public void searchRecipes(final String searchQuery, String apiKey, Callback<ArrayList<Recipe>> callback) {

        final Observable<ArrayList<Recipe>> recipeObservable = mFoodService.searchRecipes(searchQuery, apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(recipes -> {
                    final ArrayList<Recipe> recipeArrayList = recipes.getRecipes();
                    callback.onDataUpdated(recipeArrayList);
                    return recipeArrayList;
                });

        // handle http error
        recipeObservable.subscribe(recipes -> {
        }, callback::onError);
    }
}
