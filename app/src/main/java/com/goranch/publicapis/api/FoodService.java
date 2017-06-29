package com.goranch.publicapis.api;

import com.goranch.publicapis.api.model.food.recipe.ApiResult;
import com.goranch.publicapis.api.model.food.recipe.RecipeContainer;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FoodService {

    @GET("/api/search")
    Observable<ApiResult> searchRecipes(
            @Query("key") String apiKey,
            @Query("q") String searchQuery
    );

    @GET("/api/get")
    Observable<RecipeContainer> getRecipe(
            @Query("key") String apiKey,
            @Query("rId") String recipeId
    );
}
