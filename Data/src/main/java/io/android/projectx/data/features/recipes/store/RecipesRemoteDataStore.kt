package io.android.projectx.data.features.recipes.store

import io.android.projectx.data.features.recipes.model.RecipeEntity
import io.android.projectx.data.features.recipes.repository.RecipesDataStore
import io.android.projectx.data.features.recipes.repository.RecipesRemote
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

open class RecipesRemoteDataStore @Inject constructor(
    private val recipesRemote: RecipesRemote
) : RecipesRemote {

    override fun getRecipes(): Flowable<List<RecipeEntity>> {
        return recipesRemote.getRecipes()
    }

}