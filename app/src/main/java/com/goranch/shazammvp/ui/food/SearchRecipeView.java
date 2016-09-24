package com.goranch.shazammvp.ui.food;


import com.goranch.shazammvp.api.model.food.Recipe;

import java.util.ArrayList;

/**
 * Created by goranch on 30/03/16.
 */
interface SearchRecipeView {

    void onDataUpdated(ArrayList<Recipe> data);

    void showProgress();

    void hideProgress();

    void openDetailsFragment(Recipe mItem);
}
