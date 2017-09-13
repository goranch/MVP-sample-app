package com.goranch.publicapis.api.model.food.nutrition

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class FullNutrient {

    @SerializedName("attr_id")
    @Expose
    var attrId: Int? = null
    @SerializedName("value")
    @Expose
    var value: Double? = null

}
