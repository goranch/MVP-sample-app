package com.goranch.publicapis.ui.food.repository

import com.goranch.publicapis.api.model.food.nutrition.Food
import com.goranch.publicapis.api.model.food.recipe.Recipe

import io.reactivex.Observable


interface IDataRepository {
    fun searchRecipes(searchQuery: String, apiKey: String): Observable<List<Recipe>>

    fun getRecipe(recipeId: String, apiKey: String): Observable<Recipe>

    fun getNaturalLanguageNutritionInfo(query: String): Observable<List<Food>>
}
