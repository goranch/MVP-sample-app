package com.goranch.publicapis.ui.food.details;

import com.goranch.publicapis.api.ApiModule;
import com.goranch.publicapis.api.model.food.Recipe;
import com.goranch.publicapis.ui.food.FoodDataRepositoryImpl;
import com.goranch.publicapis.ui.food.IDataRepository;

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
    public void onLoad(String recipeId) {
        view.showProgress();
        repository.getRecipe(ApiModule.API_KEY, recipeId, new IDataRepository.Callback<Recipe>() {
            @Override
            public void onDataUpdated(Recipe data) {
                view.hideProgress();
                view.onDataUpdated(data);
            }

            @Override
            public void onError(Throwable throwable) {

                view.onError(throwable);

            }
        });
    }
}
