package com.goranch.publicapis.ui.food.viewmodel;

import com.goranch.publicapis.api.model.food.recipe.Recipe;

interface IFoodViewModel {
    void getRecipes(String query);

    void getSingleRecipe(String recipeId);

    void getNaturalLanguageNutritionInfo(String ingredients);

    void onItemClicked(Recipe item);
}
