package com.goranch.publicapis.api.model.food.nutrition

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Fields {

    @SerializedName("item_name")
    @Expose
    var itemName: String? = null
    @SerializedName("brand_name")
    @Expose
    var brandName: String? = null
    @SerializedName("nf_calories")
    @Expose
    var nfCalories: Double? = null
    @SerializedName("nf_total_fat")
    @Expose
    var nfTotalFat: Double? = null

}
