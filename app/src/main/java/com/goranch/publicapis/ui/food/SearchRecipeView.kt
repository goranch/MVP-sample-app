package com.goranch.publicapis.ui.food


import com.goranch.publicapis.api.model.food.recipe.Recipe

interface SearchRecipeView {

    fun loadData(recipeList: List<Recipe>)

    fun showProgress()

    fun hideProgress()

    fun openDetailsFragment()

    fun onItemClicked(mItem: Recipe)

    fun showHTTPError()
}
