package com.goranch.publicapis.api.model.food.nutrition

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AltMeasure {

    @SerializedName("serving_weight")
    @Expose
    var servingWeight: Double? = null
    @SerializedName("measure")
    @Expose
    var measure: String? = null
    @SerializedName("seq")
    @Expose
    var seq: Int? = null
    @SerializedName("qty")
    @Expose
    var qty: Double? = null

}
