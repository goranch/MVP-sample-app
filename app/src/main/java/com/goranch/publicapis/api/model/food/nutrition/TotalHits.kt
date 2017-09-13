package com.goranch.publicapis.api.model.food.nutrition

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TotalHits {

    @SerializedName("total_hits")
    @Expose
    var totalHits: Int? = null
    @SerializedName("max_score")
    @Expose
    var maxScore: Double? = null
    @SerializedName("hits")
    @Expose
    var hits: List<Hit>? = null

}
