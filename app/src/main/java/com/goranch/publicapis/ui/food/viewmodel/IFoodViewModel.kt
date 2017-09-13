package com.goranch.publicapis.ui.food.viewmodel

import com.goranch.publicapis.api.model.food.recipe.Recipe

internal interface IFoodViewModel {
    fun getRecipes(query: String)

    fun getSingleRecipe(recipeId: String)

    fun getNaturalLanguageNutritionInfo(ingredients: String)

    fun onItemClicked(item: Recipe)
}
