package io.android.projectx.cache.features.recipes.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.android.projectx.cache.features.recipes.db.RecipeConstants

@Entity(tableName = RecipeConstants.TABLE_NAME)
data class CachedRecipe(
    @PrimaryKey
    @ColumnInfo(name = RecipeConstants.COLUMN_RECIPE_ID)
    val id: Long,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    @ColumnInfo(name = RecipeConstants.COLUMN_IS_BOOKMARKED)
    var isBookmarked: Boolean
)