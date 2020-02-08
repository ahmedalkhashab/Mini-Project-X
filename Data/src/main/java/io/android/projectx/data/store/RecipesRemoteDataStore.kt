package io.android.projectx.data.store

import io.android.projectx.data.model.RecipeEntity
import io.android.projectx.data.repository.RecipesDataStore
import io.android.projectx.data.repository.RecipesRemote
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

open class RecipesRemoteDataStore @Inject constructor(
    private val recipesRemote: RecipesRemote
) : RecipesDataStore {

    override fun getRecipes(): Flowable<List<RecipeEntity>> {
        return recipesRemote.getRecipes()
    }

    override fun clearRecipes(): Completable {
        throw UnsupportedOperationException("Clearing Recipes isn't supported here...")
    }

    override fun saveRecipes(recipes: List<RecipeEntity>): Completable {
        throw UnsupportedOperationException("Saving Recipes isn't supported here...")
    }

    override fun setRecipeAsBookmarked(recipeId: Long): Completable {
        throw UnsupportedOperationException("Setting bookmarks isn't supported here...")
    }

    override fun setRecipeAsNotBookmarked(recipeId: Long): Completable {
        throw UnsupportedOperationException("Setting bookmarks isn't supported here...")
    }

    override fun getBookmarkedRecipes(): Flowable<List<RecipeEntity>> {
        throw UnsupportedOperationException("Getting Bookmarked Recipes isn't supported here...")
    }

}