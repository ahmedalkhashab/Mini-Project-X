package io.android.projectx.data.repository

import io.android.projectx.data.model.RecipeEntity
import io.reactivex.Completable
import io.reactivex.Flowable

interface RecipesDataStore {

    fun getRecipes(): Flowable<List<RecipeEntity>>

    fun clearRecipes(): Completable

    fun saveRecipes(recipes: List<RecipeEntity>): Completable

    fun setRecipeAsBookmarked(recipeId: Long): Completable

    fun setRecipeAsNotBookmarked(recipeId: Long): Completable

    fun getBookmarkedRecipes(): Flowable<List<RecipeEntity>>

}