package com.goranch.publicapis.ui.food.details

import com.goranch.publicapis.api.model.food.nutrition.Food

interface DetailRecipeView {

    fun showProgress()

    fun hideProgress()

    fun openWebView(url: String)

    fun getSingleRecipe(recipeId: String)

    fun onItemClicked(item: Food)

    fun getNutritionText(food: Food): String

    fun showHTTPError()
}
