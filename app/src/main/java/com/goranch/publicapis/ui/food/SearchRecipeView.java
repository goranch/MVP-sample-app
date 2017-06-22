package com.goranch.publicapis.ui.food;


import com.goranch.publicapis.api.model.food.Recipe;

import java.util.List;

public interface SearchRecipeView {

    void loadData(List<Recipe> recipeList);

    void showProgress();

    void hideProgress();

    void openDetailsFragment();
}
