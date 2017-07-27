package com.goranch.publicapis.api

import com.goranch.publicapis.api.model.food.recipe.ApiResult
import com.goranch.publicapis.api.model.food.recipe.RecipeContainer

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodService {

    @GET("/api/search")
    fun searchRecipes(
            @Query("key") apiKey: String,
            @Query("q") searchQuery: String
    ): Observable<ApiResult>

    @GET("/api/get")
    fun getRecipe(
            @Query("key") apiKey: String,
            @Query("rId") recipeId: String
    ): Observable<RecipeContainer>
}
