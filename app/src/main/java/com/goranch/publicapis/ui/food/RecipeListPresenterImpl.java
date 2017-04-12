package com.goranch.publicapis.ui.food;

import android.net.Uri;
import android.util.Log;

import com.goranch.publicapis.api.ApiModule;
import com.goranch.publicapis.api.model.food.Recipe;

import java.util.ArrayList;

/**
 * Created by goranch on 30/03/16.
 */
public class RecipeListPresenterImpl implements RecipeListPresenter {
    private final String TAG = getClass().getSimpleName();
    private SearchRecipeView view;
    private FoodDataRepositoryImpl repository;

    public RecipeListPresenterImpl(SearchRecipeView view, FoodDataRepositoryImpl repository) {
        this.repository = repository;
        this.view = view;
    }

    @Override
    public void onAdapterCreated(RecipeRecyclerAdapter adapter) {


    }

    @Override
    public void onItemClicked(Recipe mItem) {

        view.openDetailsFragment(mItem);

    }

    @Override
    public void onSearchRequest(String query) {

        view.showProgress();

        repository.searchRecipes(ApiModule.API_KEY, query, new IDataRepository.Callback<ArrayList<Recipe>>() {
            @Override
            public void onDataUpdated(ArrayList<Recipe> data) {

                view.hideProgress();

                view.onDataUpdated(data);

            }

            @Override
            public void onError(Throwable throwable) {
                view.hideProgress();
                Log.e(TAG, throwable.toString());
            }
        });
    }

    @Override
    public void onBindViewHolder(RecipeRecyclerAdapter.ViewHolder holder, int position, ArrayList<Recipe> recipes) {

        final Recipe recipe = recipes.get(position);

        holder.mItem = recipe;
        holder.title.setText(recipe.getTitle());
        holder.image.setImageURI(Uri.parse(recipe.getImageUrl()));

    }
}
