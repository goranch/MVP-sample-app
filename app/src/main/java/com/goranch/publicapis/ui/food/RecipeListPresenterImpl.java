package com.goranch.publicapis.ui.food;

import com.goranch.publicapis.api.model.food.Recipe;

/**
 * Created by goranch on 30/03/16.
 */
public class RecipeListPresenterImpl implements RecipeListPresenter {
    private final String TAG = getClass().getSimpleName();
    private SearchRecipeView view;

    public RecipeListPresenterImpl(SearchRecipeView view) {
        this.view = view;
    }

    @Override
    public void onItemClicked(Recipe mItem) {

        view.openDetailsFragment(mItem);

    }
}
