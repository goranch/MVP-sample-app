package com.goranch.publicapis.api.model.food.recipe

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RecipeContainer {

    @SerializedName("recipe")
    @Expose
    var recipe: Recipe = Recipe()

}
