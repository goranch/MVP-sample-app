package com.goranch.publicapis.ui.food.repository

import android.util.Log
import com.goranch.publicapis.api.FoodService
import com.goranch.publicapis.api.NutritionService
import com.goranch.publicapis.api.model.food.nutrition.Food
import com.goranch.publicapis.api.model.food.recipe.Recipe
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class FoodDataRepositoryImpl(private val foodService: FoodService, private val nutritionService: NutritionService) : IDataRepository {

    override fun searchRecipes(apiKey: String, searchQuery: String): Observable<List<Recipe>> {

        return foodService.searchRecipes(apiKey, searchQuery)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { it -> it.recipes }
                .onErrorReturn { throwable ->
                    Log.e("http error", throwable.toString())
                    ArrayList<Recipe>()
                }
    }

    override fun getRecipe(apiKey: String, recipeId: String): Observable<Recipe> {

        return foodService.getRecipe(apiKey, recipeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { it -> it.recipe }
                .onErrorReturn { throwable ->
                    Log.e("http error", throwable.toString())
                    Recipe()
                }

    }

    override fun getNaturalLanguageNutritionInfo(query: String): Observable<List<Food>> {
        val body = HTTPRequestBody()
        body.query = query
        return nutritionService.searchNutrients(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { it -> it.foods }
                .onErrorReturn { throwable ->
                    Log.e("http error", throwable.toString())
                    ArrayList<Food>()
                }
    }

    class HTTPRequestBody {
        internal var query: String = ""
    }

    companion object {
        private val TAG = FoodDataRepositoryImpl::class.java.simpleName
    }
}

