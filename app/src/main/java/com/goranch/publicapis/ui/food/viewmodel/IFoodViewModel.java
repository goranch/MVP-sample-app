package com.goranch.publicapis.ui.food.viewmodel;

import android.arch.lifecycle.LiveData;

import com.goranch.publicapis.api.model.food.Recipe;

import java.util.List;

/**
 * Created by goran on 30/05/2017.
 */

interface IFoodViewModel {
    LiveData<List<Recipe>> getRecipes(String query);
}
