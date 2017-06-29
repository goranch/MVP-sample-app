
package com.goranch.publicapis.api.model.food.nutrition;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Foods {

    @SerializedName("foods")
    @Expose
    private List<Food> foods = null;

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

}
