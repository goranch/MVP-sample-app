package com.goranch.publicapis.ui.food.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

import com.goranch.publicapis.api.ApiModule
import com.goranch.publicapis.api.model.food.nutrition.Food
import com.goranch.publicapis.api.model.food.recipe.Recipe
import com.goranch.publicapis.ui.food.repository.FoodDataRepositoryImpl
import com.goranch.publicapis.ui.food.repository.IDataRepository

class FoodViewModel private constructor(private val repository: IDataRepository) : ViewModel(), IFoodViewModel {
    val observableRecipe = MutableLiveData<Recipe>()
    val observableRecipeList = MutableLiveData<List<Recipe>>()
    val nutritionList = MutableLiveData<List<Food>>()
    val recipeId = MutableLiveData<String>()

    override fun getRecipes(query: String) {
        repository.searchRecipes(ApiModule.RECIPE_API_KEY, query)
                .subscribe({ observableRecipeList.setValue(it) })
    }

    override fun getSingleRecipe(recipeId: String) {
        repository.getRecipe(ApiModule.RECIPE_API_KEY, recipeId)
                .subscribe({ observableRecipe.setValue(it) })
    }

    override fun getNaturalLanguageNutritionInfo(ingredients: String) {
        repository.getNaturalLanguageNutritionInfo(ingredients)
                .subscribe({ nutritionList.setValue(it) })
    }

    override fun onItemClicked(item: Recipe) {
        observableRecipe.value = item
        recipeId.value = item.recipeId
    }

    fun resetNutritionList() {
        nutritionList.value = null
    }

    //Inject dependencies. It was the preferred way in the example app by Google
    class Factory : ViewModelProvider.NewInstanceFactory {

        private val repository: IDataRepository

        constructor(repository: IDataRepository) {
            this.repository = repository
        }

        constructor(repository: FoodDataRepositoryImpl) {
            this.repository = repository
        }

        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            return FoodViewModel(repository) as T
        }
    }
}
