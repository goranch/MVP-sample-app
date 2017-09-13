package com.goranch.publicapis.api.model.food.nutrition

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Food {

    @SerializedName("food_name")
    @Expose
    var foodName: String? = null
    @SerializedName("brand_name")
    @Expose
    var brandName: Any? = null
    @SerializedName("serving_qty")
    @Expose
    var servingQty: Double? = null
    @SerializedName("serving_unit")
    @Expose
    var servingUnit: String? = null
    @SerializedName("serving_weight_grams")
    @Expose
    var servingWeightGrams: Double? = null
    @SerializedName("nf_calories")
    @Expose
    var nfCalories: Double? = null
    @SerializedName("nf_total_fat")
    @Expose
    var nfTotalFat: Double? = null
    @SerializedName("nf_saturated_fat")
    @Expose
    var nfSaturatedFat: Double? = null
    @SerializedName("nf_cholesterol")
    @Expose
    var nfCholesterol: Double? = null
    @SerializedName("nf_sodium")
    @Expose
    var nfSodium: Double? = null
    @SerializedName("nf_total_carbohydrate")
    @Expose
    var nfTotalCarbohydrate: Double? = null
    @SerializedName("nf_dietary_fiber")
    @Expose
    var nfDietaryFiber: Double? = null
    @SerializedName("nf_sugars")
    @Expose
    var nfSugars: Double? = null
    @SerializedName("nf_protein")
    @Expose
    var nfProtein: Double? = null
    @SerializedName("nf_potassium")
    @Expose
    var nfPotassium: Double? = null
    @SerializedName("nf_p")
    @Expose
    var nfP: Double? = null
    @SerializedName("full_nutrients")
    @Expose
    var fullNutrients: List<FullNutrient>? = null
    @SerializedName("nix_brand_name")
    @Expose
    var nixBrandName: Any? = null
    @SerializedName("nix_brand_id")
    @Expose
    var nixBrandId: Any? = null
    @SerializedName("nix_item_name")
    @Expose
    var nixItemName: Any? = null
    @SerializedName("nix_item_id")
    @Expose
    var nixItemId: Any? = null
    @SerializedName("upc")
    @Expose
    var upc: Any? = null
    @SerializedName("consumed_at")
    @Expose
    var consumedAt: String? = null
    @SerializedName("metadata")
    @Expose
    var metadata: Metadata? = null
    @SerializedName("source")
    @Expose
    var source: Int? = null
    @SerializedName("ndb_no")
    @Expose
    var ndbNo: Int? = null
    @SerializedName("tags")
    @Expose
    var tags: Tags? = null
    @SerializedName("alt_measures")
    @Expose
    var altMeasures: List<AltMeasure>? = null
    @SerializedName("lat")
    @Expose
    var lat: Any? = null
    @SerializedName("lng")
    @Expose
    var lng: Any? = null
    @SerializedName("meal_type")
    @Expose
    var mealType: Int? = null
    @SerializedName("photo")
    @Expose
    var photo: Photo? = null

}
