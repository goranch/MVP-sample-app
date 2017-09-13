package com.goranch.publicapis.api.model.food.nutrition

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Photo {

    @SerializedName("thumb")
    @Expose
    var thumb: String? = null
    @SerializedName("highres")
    @Expose
    var highres: String? = null

}
