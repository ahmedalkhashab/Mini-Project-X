package io.android.projectx.data.store

import io.android.projectx.data.repository.RecipesDataStore
import javax.inject.Inject

open class RecipesDataStoreFactory @Inject constructor(
    private val recipesCacheDateStore: RecipesCacheDateStore,
    private val recipesRemoteDateStore: RecipesRemoteDateStore
) {

    open fun getDataStore(recipesCached: Boolean, cacheExpired: Boolean): RecipesDataStore {
        return if (recipesCached && !cacheExpired) recipesCacheDateStore
        else recipesRemoteDateStore
    }

    open fun getCacheDataStore(): RecipesDataStore {
        return recipesCacheDateStore
    }

}