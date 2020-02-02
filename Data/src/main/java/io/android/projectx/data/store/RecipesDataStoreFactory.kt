package io.android.projectx.data.store

import io.android.projectx.data.repository.RecipesDataStore
import javax.inject.Inject

open class RecipesDataStoreFactory @Inject constructor(
    private val recipesCacheDateStore: RecipesCacheDateStore,
    private val recipesRemoteDataStore: RecipesRemoteDataStore
) {

    open fun getDataStore(recipesCached: Boolean, cacheExpired: Boolean): RecipesDataStore {
        return if (recipesCached && !cacheExpired) recipesCacheDateStore
        else recipesRemoteDataStore
    }

    open fun getCacheDataStore(): RecipesDataStore {
        return recipesCacheDateStore
    }

}