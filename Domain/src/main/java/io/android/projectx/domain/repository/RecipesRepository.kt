package io.android.projectx.domain.repository

import io.android.projectx.domain.model.Recipe
import io.reactivex.Completable
import io.reactivex.Observable

interface RecipesRepository {

    fun getRecipes(): Observable<List<Recipe>>

    fun bookmarkRecipe(recipeId: Long): Completable

    fun unBookmarkRecipe(recipeId: Long): Completable

    fun getBookmarkedRecipes(): Observable<List<Recipe>>
}