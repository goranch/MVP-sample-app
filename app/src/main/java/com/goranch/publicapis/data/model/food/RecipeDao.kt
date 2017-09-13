package com.goranch.publicapis.data.model.food

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

import com.goranch.publicapis.api.model.food.recipe.Recipe

@Dao
interface RecipeDao {

    @get:Query("SELECT * FROM recipe")
    val allBrowsedRecipes: List<Recipe>

    @get:Query("SELECT * FROM recipe WHERE favourite=1")
    val favouriteRecipes: List<Recipe>

    @Insert
    fun insertSearchResults(vararg recipes: Recipe)

    @Delete
    fun delete(recipe: Recipe)

}
