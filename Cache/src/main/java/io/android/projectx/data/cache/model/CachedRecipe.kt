package io.android.projectx.data.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.android.projectx.data.cache.db.RecipeConstants
import java.util.*

@Entity(tableName = RecipeConstants.TABLE_NAME)
data class CachedRecipe(
    @PrimaryKey
    @ColumnInfo(name = RecipeConstants.COLUMN_RECIPE_ID)
    val id: Long,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: Date,
    val content: String,
    @ColumnInfo(name = RecipeConstants.COLUMN_IS_BOOKMARKED)
    var isBookmarked: Boolean
)