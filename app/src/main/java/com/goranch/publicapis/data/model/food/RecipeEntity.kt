package com.goranch.publicapis.data.model.food

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.provider.BaseColumns

@Entity(tableName = RecipeEntity.TABLE_NAME)
class RecipeEntity(
        /**
         * The unique ID of the recipe.
         */
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(index = true, name = COLUMN_ID)
        var id: Long,
        /**
         * The name of the recipe.
         */
        @ColumnInfo(name = TITLE)
        var title: String, @ColumnInfo(name = PUBLISHER)
        var publisher: String, @ColumnInfo(name = F2F_URL)
        var f2fUrl: String, @ColumnInfo(name = SOURCE_URL)
        var sourceUrl: String,
        @ColumnInfo(name = RECIPE_ID)
        var recipeId: String, @ColumnInfo(name = IMAGE_URL)
        var imageUrl: String, @ColumnInfo(name = SOCIAL_RANK)
        var socialRank: Double?, @ColumnInfo(name = PUBLISHER_URL)
        var publisherUrl: String,
        @ColumnInfo(name = INGREDIENTS_LIST)
        var ingredientsList: String, @ColumnInfo(name = FAVOURITE)
        var favourite: Int) {

    companion object {
        const val TABLE_NAME = "recipe"

        /**
         * The name of the ID column.
         */
        const val COLUMN_ID = BaseColumns._ID

        /**
         * The name of the name column.
         */
        const private val TITLE = "recipe_title"
        const private val PUBLISHER = "publisher"
        const private val F2F_URL = "f2f_url"
        const private val SOURCE_URL = "source_url"
        const private val RECIPE_ID = "recipe_id"
        const private val IMAGE_URL = "image_url"
        const private val SOCIAL_RANK = "social_rank"
        const private val PUBLISHER_URL = "publisher_url"
        const private val INGREDIENTS_LIST = "ingredients_list"
        const private val FAVOURITE = "favourite"
    }
}
