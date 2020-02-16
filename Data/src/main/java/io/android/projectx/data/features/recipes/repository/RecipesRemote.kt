package io.android.projectx.data.features.recipes.repository

import io.android.projectx.data.features.recipes.model.RecipeEntity
import io.reactivex.Flowable

interface RecipesRemote {

    fun getRecipes(): Flowable<List<RecipeEntity>>

}