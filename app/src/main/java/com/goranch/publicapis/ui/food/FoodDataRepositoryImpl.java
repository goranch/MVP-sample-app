package com.goranch.publicapis.ui.food;

import android.util.Log;

import com.goranch.publicapis.api.FoodService;
import com.goranch.publicapis.api.NutritionService;
import com.goranch.publicapis.api.model.food.nutrition.Food;
import com.goranch.publicapis.api.model.food.nutrition.Foods;
import com.goranch.publicapis.api.model.food.recipe.ApiResult;
import com.goranch.publicapis.api.model.food.recipe.Recipe;
import com.goranch.publicapis.api.model.food.recipe.RecipeContainer;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmObject;

public class FoodDataRepositoryImpl implements IFoodRepository {
    private static final String TAG = FoodDataRepositoryImpl.class.getSimpleName();
    private final FoodService foodService;
    private final NutritionService nutritionService;
    private Realm realm;

    public FoodDataRepositoryImpl(FoodService foodService, NutritionService nutritionService) {
        this.foodService = foodService;
        this.nutritionService = nutritionService;

// Get a Realm instance for this thread
        realm = Realm.getDefaultInstance();
    }

    @Override
    public Observable<List<Recipe>> searchRecipes(final String apiKey, String searchQuery) {

        return foodService.searchRecipes(apiKey, searchQuery)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(ApiResult::getRecipes)
                .onErrorReturn(throwable -> {
                    Log.e("http error", throwable.toString());
                    return null;
                });
    }

    @Override
    public Observable<Recipe> getRecipe(String apiKey, String recipeId) {

        return foodService.getRecipe(apiKey, recipeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(RecipeContainer::getRecipe)
                .onErrorReturn(throwable -> {
                    Log.e("http error", throwable.toString());
                    return null;
                });

    }

    @Override
    public Observable<List<Food>> getNaturalLanguageNutritionInfo(String query) {
        HTTPRequestBody body = new HTTPRequestBody();
        body.setQuery(query);
        return nutritionService.searchNutrients(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(foods -> {
                    saveFoods(foods);
                    return foods;
                })
                .map(Foods::getFoods)
                .onErrorReturn(throwable -> {
                    Log.e("http error", throwable.toString());
                    return null;
                });
    }

    @Override
    public void saveRecipe(Recipe recipe) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(recipe);
        realm.commitTransaction();
    }

    @Override
    public void saveSearchQuery(RealmObject query) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(query);
        realm.commitTransaction();
    }

    @Override
    public void saveFoodForRecipe(String recipeName, List<Food> food) {

    }

    @Override
    public List<Recipe> getAllSavedRecipes() {
        return null;
    }

    @Override
    public List<RealmObject> getAllSearchQueries() {
        return null;
    }

    @Override
    public List<Recipe> getAllFavouriteRecipes() {
        return null;
    }

    @Override
    public void saveFoods(Foods foods) {
        realm.beginTransaction();
        for (Food f : foods.getFoods()) {
            realm.copyToRealmOrUpdate(f);
        }
        realm.commitTransaction();
    }

    public class HTTPRequestBody {
        String query;

        void setQuery(String query) {
            this.query = query;
        }
    }
}

