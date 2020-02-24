package io.android.projectx.data.features.recipes.store

import io.android.projectx.data.features.recipes.repository.RecipesCache
import io.android.projectx.data.features.recipes.repository.RecipesDataStore
import io.android.projectx.data.features.recipes.repository.RecipesRemote
import javax.inject.Inject

open class RecipesDataStoreFactory @Inject constructor(
    private val recipesCacheDateStore: RecipesCacheDateStore,
    private val recipesRemoteDataStore: RecipesRemoteDataStore
) {

    open fun getDataStore(recipesCached: Boolean, cacheExpired: Boolean): RecipesDataStore {
        return if (recipesCached && !cacheExpired) recipesCacheDateStore
        else recipesRemoteDataStore
    }

    open fun getCacheDataStore(): RecipesCache {
        return recipesCacheDateStore
    }

    open fun getRemoteDataStore(): RecipesRemote {
        return recipesRemoteDataStore
    }

}