package com.goranch.publicapis.api;

import com.goranch.publicapis.api.model.food.ApiResult;
import com.goranch.publicapis.api.model.food.RecipeContainer;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by goran on 15/09/2016.
 */
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
