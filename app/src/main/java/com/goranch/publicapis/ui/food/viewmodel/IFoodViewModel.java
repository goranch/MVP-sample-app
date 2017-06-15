package com.goranch.publicapis.ui.food.viewmodel;

import com.goranch.publicapis.api.model.food.Recipe;

/**
 * Created by goran on 30/05/2017.
 */

interface IFoodViewModel {
    void getRecipes(String query);

    void onItemClicked(Recipe item);
}
