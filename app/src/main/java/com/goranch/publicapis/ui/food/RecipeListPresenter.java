package com.goranch.publicapis.ui.food;

import com.goranch.publicapis.api.model.food.Recipe;

/**
 * Created by goranch on 30/03/16.
 */
public interface RecipeListPresenter {
    void onItemClicked(Recipe mItem);
}
