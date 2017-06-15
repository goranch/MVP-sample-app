package com.goranch.publicapis.ui.food;


import com.goranch.publicapis.api.model.food.Recipe;

public interface SearchRecipeView {

    void showProgress();

    void hideProgress();

    void openDetailsFragment(Recipe mItem);
}
