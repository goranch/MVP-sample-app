package com.goranch.publicapis.api.model.food.nutrition

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Hit {

    @SerializedName("_id")
    @Expose
    var id: String? = null
    @SerializedName("fields")
    @Expose
    var fields: Fields? = null

}
