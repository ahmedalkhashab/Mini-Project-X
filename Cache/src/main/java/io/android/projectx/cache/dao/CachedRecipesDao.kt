package io.android.projectx.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.android.projectx.cache.db.RecipeConstants.DELETE_RECIPES
import io.android.projectx.cache.db.RecipeConstants.QUERY_BOOKMARKED_RECIPES
import io.android.projectx.cache.db.RecipeConstants.QUERY_RECIPES
import io.android.projectx.cache.db.RecipeConstants.QUERY_UPDATE_BOOKMARK_STATUS
import io.android.projectx.cache.model.CachedRecipe
import io.reactivex.Flowable

@Dao
abstract class CachedRecipesDao {

    @Query(QUERY_RECIPES)
    @JvmSuppressWildcards
    abstract fun getRecipes(): Flowable<List<CachedRecipe>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    abstract fun insertRecipes(recipes: List<CachedRecipe>)

    @Query(DELETE_RECIPES)
    abstract fun deleteRecipes()

    @Query(QUERY_BOOKMARKED_RECIPES)
    abstract fun getBookmarkedRecipes(): Flowable<List<CachedRecipe>>

    @Query(QUERY_UPDATE_BOOKMARK_STATUS)
    abstract fun setBookmarkStatus(
        isBookmarked: Boolean,
        recipeId: Long
    )

}