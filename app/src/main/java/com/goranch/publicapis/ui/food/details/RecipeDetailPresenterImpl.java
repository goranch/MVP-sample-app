package com.goranch.publicapis.ui.food.details;

import com.goranch.publicapis.ui.food.FoodDataRepositoryImpl;

/**
 * Created by goran on 06/04/2017.
 */

public class RecipeDetailPresenterImpl implements RecipeDetailPresenter {
    private final String TAG = getClass().getSimpleName();
    private DetailRecipeView view;
    private FoodDataRepositoryImpl repository;

    public RecipeDetailPresenterImpl(DetailRecipeView view, FoodDataRepositoryImpl repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void onLoad() {

    }
}
