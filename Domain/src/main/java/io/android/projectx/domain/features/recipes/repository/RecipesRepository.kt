package io.android.projectx.domain.features.recipes.repository

import io.android.projectx.domain.features.recipes.model.Recipe
import io.reactivex.Completable
import io.reactivex.Observable

interface RecipesRepository {

    fun getRecipes(fromIndex: Int, pageSize: Int): Observable<List<Recipe>>

    fun bookmarkRecipe(recipeId: Long): Completable

    fun unBookmarkRecipe(recipeId: Long): Completable

    fun getBookmarkedRecipes(): Observable<List<Recipe>>
}