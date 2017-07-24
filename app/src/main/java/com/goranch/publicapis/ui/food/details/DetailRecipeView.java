package com.goranch.publicapis.ui.food.details;

import com.goranch.publicapis.api.model.food.nutrition.Food;

public interface DetailRecipeView {

    void showProgress();

    void hideProgress();

    void openWebView(String url);

    void getSingleRecipe(String recipeId);

    void onItemClicked(Food item);

    String getNutritionText(Food food);

    void showHTTPError();
}
