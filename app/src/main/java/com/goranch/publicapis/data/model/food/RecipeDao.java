package com.goranch.publicapis.data.model.food;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.goranch.publicapis.api.model.food.Recipe;

import java.util.List;

/**
 * Created by goran on 19/05/2017.
 */

@Dao
public interface RecipeDao {

    @Query("SELECT * FROM recipe")
    List<Recipe> getAllBrowsedRecipes();

    @Query("SELECT * FROM recipe WHERE favourite=1")
    List<Recipe> getFavouriteRecipes();

    @Insert
    void insertSearchResults(Recipe... recipes);

    @Delete
    void delete(Recipe recipe);

}
