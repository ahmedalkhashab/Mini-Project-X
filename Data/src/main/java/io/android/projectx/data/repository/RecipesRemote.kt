package io.android.projectx.data.repository

import io.android.projectx.data.model.RecipeEntity
import io.reactivex.Flowable

interface RecipesRemote {

    fun getRecipes(): Flowable<List<RecipeEntity>>

}