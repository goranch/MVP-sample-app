package com.goranch.shazammvp.ui.food;

import android.net.Uri;
import android.util.Log;

import com.goranch.shazammvp.api.model.food.Recipe;

import java.util.ArrayList;

/**
 * Created by goranch on 30/03/16.
 */
class RecipeListPresenterImpl implements IRecipeListPresenter {
    private static final String API_KEY = "b549c4c96152e677eb90de4604ca61a2";
    private final String TAG = getClass().getSimpleName();
    private SearchRecipeView view;
    private DataRepositoryImpl repository;

    public RecipeListPresenterImpl(SearchRecipeView view, DataRepositoryImpl repository) {
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

        repository.searchRecipes(API_KEY, query, new IDataRepository.Callback<ArrayList<Recipe>>() {
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
