package io.android.projectx.data.store

import io.android.projectx.data.model.RecipeEntity
import io.android.projectx.data.repository.RecipesCache
import io.android.projectx.data.repository.RecipesDataStore
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class RecipesCacheDateStore @Inject constructor(
    private val recipesCache: RecipesCache
) : RecipesDataStore {

    override fun getRecipes(): Observable<List<RecipeEntity>> {
        return recipesCache.getRecipes()
    }

    override fun clearRecipes(): Completable {
        return recipesCache.clearRecipes()
    }

    override fun saveRecipes(recipes: List<RecipeEntity>): Completable {
        return recipesCache.saveRecipes(recipes)
            .andThen(recipesCache.setLastCacheTime(System.currentTimeMillis()))
    }

    override fun setRecipeAsBookmarked(recipeId: Long): Completable {
        return recipesCache.setRecipeAsBookmarked(recipeId)
    }

    override fun setRecipeAsNotBookmarked(recipeId: Long): Completable {
        return recipesCache.setRecipeAsNotBookmarked(recipeId)
    }

    override fun getBookmarkedRecipes(): Observable<List<RecipeEntity>> {
        return recipesCache.getBookmarkedRecipes()
    }

}