package com.goranch.shazammvp.ui.food;

import com.goranch.shazammvp.api.model.food.Recipe;

import java.util.ArrayList;

/**
 * Created by goranch on 30/03/16.
 */
interface IDataRepository {
    void searchRecipes(String callback, String apiKey, Callback<ArrayList<Recipe>> searchQuery);

    interface Callback<T> {
        void onDataUpdated(T data);

        void onError(Throwable throwable);
    }
}
