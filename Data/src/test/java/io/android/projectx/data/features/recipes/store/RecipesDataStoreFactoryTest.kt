package io.android.projectx.data.features.recipes.store

import com.nhaarman.mockitokotlin2.mock
import io.android.projectx.data.features.recipes.store.RecipesCacheDateStore
import io.android.projectx.data.features.recipes.store.RecipesDataStoreFactory
import io.android.projectx.data.features.recipes.store.RecipesRemoteDataStore
import org.junit.Test
import kotlin.test.assertEquals

class RecipesDataStoreFactoryTest {

    private val cacheStore = mock<RecipesCacheDateStore>()
    private val remoteStore = mock<RecipesRemoteDataStore>()
    private val factory =
        RecipesDataStoreFactory(
            cacheStore,
            remoteStore
        )

    @Test
    fun getDataStoreReturnsRemoteStoreWhenCacheExpired() {
        assertEquals(remoteStore, factory.getDataStore(true, true))
    }

    @Test
    fun getDataStoreReturnsRemoteStoreWhenRecipesNotCached() {
        assertEquals(remoteStore, factory.getDataStore(false, false))
    }

    @Test
    fun getDataStoreReturnsCacheStore() {
        assertEquals(cacheStore, factory.getDataStore(true, false))
    }

    @Test
    fun getCacheDataStoreReturnsCacheStore() {
        assertEquals(cacheStore, factory.getCacheDataStore())
    }

}