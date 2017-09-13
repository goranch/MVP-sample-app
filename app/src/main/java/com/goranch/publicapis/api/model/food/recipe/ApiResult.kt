package com.goranch.publicapis.api.model.food.recipe

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*
import javax.annotation.Generated

@Generated("org.jsonschema2pojo")
class ApiResult {

    @SerializedName("count")
    @Expose
    var count: Int? = null

    @SerializedName("recipes")
    @Expose
    var recipes: List<Recipe> = ArrayList()

}
