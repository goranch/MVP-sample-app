package com.goranch.publicapis.data

import android.arch.persistence.db.SupportSQLiteOpenHelper
import android.arch.persistence.room.*
import android.content.Context
import com.goranch.publicapis.api.model.food.recipe.Recipe
import com.goranch.publicapis.data.model.food.RecipeDao

@Database(entities = arrayOf(Recipe::class), version = 1)
abstract class PublicApiDatabase : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao

    override fun createOpenHelper(config: DatabaseConfiguration): SupportSQLiteOpenHelper? {
        return null
    }

    override fun createInvalidationTracker(): InvalidationTracker? {
        return null
    }

    companion object {

        var DB_NAME = "public_api_db"
        /**
         * The only instance
         */
        private var sInstance: PublicApiDatabase? = null

        /**
         * Gets the singleton instance of SampleDatabase.

         * @param context The context.
         * *
         * @return The singleton instance of SampleDatabase.
         */
        @Synchronized fun getInstance(context: Context): PublicApiDatabase {
            if (sInstance == null) {
                sInstance = Room
                        .databaseBuilder(context.applicationContext, PublicApiDatabase::class.java, "ex")
                        .build()
            }
            return sInstance!!
        }
    }
}
