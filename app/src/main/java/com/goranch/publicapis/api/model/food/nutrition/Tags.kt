package com.goranch.publicapis.api.model.food.nutrition

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Tags {

    @SerializedName("item")
    @Expose
    var item: String? = null
    @SerializedName("measure")
    @Expose
    var measure: Any? = null
    @SerializedName("quantity")
    @Expose
    var quantity: String? = null
    @SerializedName("tag_id")
    @Expose
    var tagId: Int? = null

}
