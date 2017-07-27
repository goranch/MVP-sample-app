package com.goranch.publicapis.api.model.food.nutrition

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Foods {

    @SerializedName("foods")
    @Expose
    var foods: List<Food>? = null

}
