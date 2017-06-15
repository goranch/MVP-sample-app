package com.goranch.publicapis.ui.food.viewmodel;

import com.goranch.publicapis.api.model.food.Recipe;

interface IFoodViewModel {
    void getRecipes(String query);

    void onItemClicked(Recipe item);
}
