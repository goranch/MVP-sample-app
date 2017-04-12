package com.goranch.publicapis.ui.food;

import com.goranch.publicapis.api.model.food.Recipe;

import java.util.ArrayList;

/**
 * Created by goranch on 30/03/16.
 */
interface IDataRepository {
    void searchRecipes(String searchQuery, String apiKey, Callback<ArrayList<Recipe>> recipes);

    void getRecipe(String recipeId, String apikey, Callback<Recipe> recipeCallback);

    interface Callback<T> {
        void onDataUpdated(T data);

        void onError(Throwable throwable);
    }
}
