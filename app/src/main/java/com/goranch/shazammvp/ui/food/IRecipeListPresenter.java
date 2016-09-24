package com.goranch.shazammvp.ui.food;

import com.goranch.shazammvp.api.model.food.Recipe;

import java.util.ArrayList;

/**
 * Created by goranch on 30/03/16.
 */
interface IRecipeListPresenter {
    void onAdapterCreated(RecipeRecyclerAdapter adapter);

    void onItemClicked(Recipe mItem);

    void onSearchRequest(String query);

    void onBindViewHolder(RecipeRecyclerAdapter.ViewHolder holder, int position, ArrayList<Recipe> recipes);
}
