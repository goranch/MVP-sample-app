package com.goranch.publicapis.ui.food;

import com.goranch.publicapis.api.model.food.nutrition.Food;
import com.goranch.publicapis.api.model.food.nutrition.Foods;
import com.goranch.publicapis.api.model.food.recipe.Recipe;

import java.util.List;

import io.reactivex.Observable;
import io.realm.RealmObject;


public interface IFoodRepository {
    Observable<List<Recipe>> searchRecipes(String searchQuery, String apiKey);

    Observable<Recipe> getRecipe(String recipeId, String apiKey);

    Observable<List<Food>> getNaturalLanguageNutritionInfo(String query);

    void saveRecipe(Recipe recipe);

    void saveSearchQuery(RealmObject query);

    // Every recipeName is associated with a Recipe
    void saveFoodForRecipe(String recipeName, List<Food> food);

    // returns recipes with foods
    List<Recipe> getAllSavedRecipes();

    List<RealmObject> getAllSearchQueries();

    //TODO add a favourites star for every recipe
    List<Recipe> getAllFavouriteRecipes();

    void saveFoods(Foods foods);
}
