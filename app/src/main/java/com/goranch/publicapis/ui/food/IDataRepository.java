package com.goranch.publicapis.ui.food;

import com.goranch.publicapis.api.model.food.Recipe;

import java.util.List;

import rx.Observable;

/**
 * Created by goranch on 30/03/16.
 */
public interface IDataRepository {
    Observable<List<Recipe>> searchRecipes(String searchQuery, String apiKey);

    Observable<Recipe> getRecipe(String recipeId, String apiKey);
}
