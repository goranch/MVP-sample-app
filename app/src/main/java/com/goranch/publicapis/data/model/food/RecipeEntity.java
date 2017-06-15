package com.goranch.publicapis.data.model.food;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.provider.BaseColumns;

@Entity(tableName = RecipeEntity.TABLE_NAME)
public class RecipeEntity {

    public static final String TABLE_NAME = "recipe";

    /**
     * The name of the ID column.
     */
    public static final String COLUMN_ID = BaseColumns._ID;

    /**
     * The name of the name column.
     */
    private static final String TITLE = "recipe_title";
    private static final String PUBLISHER = "publisher";
    private static final String F2F_URL = "f2f_url";
    private static final String SOURCE_URL = "source_url";
    private static final String RECIPE_ID = "recipe_id";
    private static final String IMAGE_URL = "image_url";
    private static final String SOCIAL_RANK = "social_rank";
    private static final String PUBLISHER_URL = "publisher_url";
    private static final String INGREDIENTS_LIST = "ingredients_list";
    private static final String FAVOURITE = "favourite";

    /**
     * The unique ID of the recipe.
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = COLUMN_ID)
    public long id;

    /**
     * The name of the recipe.
     */
    @ColumnInfo(name = TITLE)
    public String title;

    @ColumnInfo(name = PUBLISHER)
    public String publisher;

    @ColumnInfo(name = F2F_URL)
    public String f2fUrl;

    @ColumnInfo(name = SOURCE_URL)
    public String sourceUrl;

    @ColumnInfo(name = RECIPE_ID)
    public String recipeId;

    @ColumnInfo(name = IMAGE_URL)
    public String imageUrl;

    @ColumnInfo(name = SOCIAL_RANK)
    public Double socialRank;

    @ColumnInfo(name = PUBLISHER_URL)
    public String publisherUrl;

    @ColumnInfo(name = INGREDIENTS_LIST)
    public String ingredientsList;

    @ColumnInfo(name = FAVOURITE)
    public int favourite;


    public RecipeEntity(long id, String title, String publisher, String f2fUrl, String sourceUrl,
                        String recipeId, String imageUrl, Double socialRank, String publisherUrl,
                        String ingredientsList, int favourite) {
        this.id = id;
        this.title = title;
        this.publisher = publisher;
        this.f2fUrl = f2fUrl;
        this.sourceUrl = sourceUrl;
        this.recipeId = recipeId;
        this.imageUrl = imageUrl;
        this.socialRank = socialRank;
        this.publisherUrl = publisherUrl;
        this.ingredientsList = ingredientsList;
        this.favourite = favourite;
    }
}
