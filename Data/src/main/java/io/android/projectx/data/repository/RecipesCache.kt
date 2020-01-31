package io.android.projectx.data.repository

import io.android.projectx.data.model.RecipeEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface RecipesCache {

    fun clearRecipes(): Completable

    fun saveRecipes(recipes: List<RecipeEntity>): Completable

    fun getRecipes(): Observable<List<RecipeEntity>>

    fun getBookmarkedRecipes(): Observable<List<RecipeEntity>>

    fun setRecipeAsBookmarked(recipeId: Long): Completable

    fun setRecipeAsNotBookmarked(recipeId: Long): Completable

    fun areRecipesCached(): Single<Boolean>

    fun setLastCacheTime(lastCache: Long): Completable

    fun isRecipesCacheExpired(): Single<Boolean>

}