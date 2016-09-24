
package com.goranch.shazammvp.api.model.food;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class ApiResult {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("recipes")
    @Expose
    private ArrayList<Recipe> recipes = new ArrayList<Recipe>();

    /**
     * @return The count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * @param count The count
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * @return The recipes
     */
    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    /**
     * @param recipes The recipes
     */
    public void setRecipes(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }

}
