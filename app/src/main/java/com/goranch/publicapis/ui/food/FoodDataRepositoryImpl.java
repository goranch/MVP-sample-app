package com.goranch.publicapis.ui.food;

import android.util.Log;

import com.goranch.publicapis.api.FoodService;
import com.goranch.publicapis.api.NutritionService;
import com.goranch.publicapis.api.model.food.nutrition.Hit;
import com.goranch.publicapis.api.model.food.nutrition.TotalHits;
import com.goranch.publicapis.api.model.food.recipe.ApiResult;
import com.goranch.publicapis.api.model.food.recipe.Recipe;
import com.goranch.publicapis.api.model.food.recipe.RecipeContainer;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class FoodDataRepositoryImpl implements IDataRepository {
    private static final String TAG = FoodDataRepositoryImpl.class.getSimpleName();
    private final FoodService foodService;
    private final NutritionService nutritionService;

    public FoodDataRepositoryImpl(FoodService foodService, NutritionService nutritionService) {
        this.foodService = foodService;
        this.nutritionService = nutritionService;
    }

    @Override
    public Observable<List<Recipe>> searchRecipes(final String apiKey, String searchQuery) {

        return foodService.searchRecipes(apiKey, searchQuery)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(ApiResult::getRecipes)
                .doOnError(throwable -> Log.e("http error", throwable.toString()));
    }

    @Override
    public Observable<Recipe> getRecipe(String apiKey, String recipeId) {

        return foodService.getRecipe(apiKey, recipeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(RecipeContainer::getRecipe)
                .doOnError(throwable -> Log.e("http error", throwable.toString()));

    }

    @Override
    public Observable<List<Hit>> getNaturalLanguageNutritionInfo(String query) {
        return nutritionService.searchNutritions("{query:" + query + "}")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(TotalHits::getHits)
                .onErrorReturn(throwable -> {
                    Log.e("http error", throwable.toString());
                    return new ArrayList<>();
                });
    }
}
