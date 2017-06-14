package com.goranch.publicapis.ui.food;


import com.goranch.publicapis.api.model.food.Recipe;

import java.util.ArrayList;

/**
 * Created by goranch on 30/03/16.
 */
public interface SearchRecipeView {

    void onDataUpdated(ArrayList<Recipe> data);

    void showProgress();

    void hideProgress();

    void openDetailsFragment(Recipe mItem);
}
