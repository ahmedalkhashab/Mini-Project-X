package io.android.projectx.data.repository

import io.android.projectx.data.model.RecipeEntity
import io.reactivex.Observable

interface RecipesRemote {

    fun getRecipes(): Observable<List<RecipeEntity>>

}