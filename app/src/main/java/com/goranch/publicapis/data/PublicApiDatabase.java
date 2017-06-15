package com.goranch.publicapis.data;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.goranch.publicapis.api.model.food.Recipe;
import com.goranch.publicapis.data.model.food.RecipeDao;

@Database(entities = {Recipe.class}, version = 1)
public abstract class PublicApiDatabase extends RoomDatabase {

    public static String DB_NAME = "public_api_db";
    /**
     * The only instance
     */
    private static PublicApiDatabase sInstance;

    /**
     * Gets the singleton instance of SampleDatabase.
     *
     * @param context The context.
     * @return The singleton instance of SampleDatabase.
     */
    public static synchronized PublicApiDatabase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = Room
                    .databaseBuilder(context.getApplicationContext(), PublicApiDatabase.class, "ex")
                    .build();
        }
        return sInstance;
    }

    public abstract RecipeDao recipeDao();

    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }
}
