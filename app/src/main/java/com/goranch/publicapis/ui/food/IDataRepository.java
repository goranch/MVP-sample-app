package com.goranch.publicapis.ui.food;

import com.goranch.publicapis.api.model.food.nutrition.Hit;
import com.goranch.publicapis.api.model.food.recipe.Recipe;

import java.util.List;

import io.reactivex.Observable;


public interface IDataRepository {
    Observable<List<Recipe>> searchRecipes(String searchQuery, String apiKey);

    Observable<Recipe> getRecipe(String recipeId, String apiKey);

    Observable<List<Hit>> getNaturalLanguageNutritionInfo(String query);
}
