package io.android.projectx.data.di.module.data

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.android.projectx.cache.AppDatabase
import io.android.projectx.data.features.recipes.repository.RecipesCache
import io.android.projectx.data.features.recipes.store.RecipesCacheDateStore
import io.android.projectx.data.features.restaurants.repository.RestaurantsCache
import io.android.projectx.data.features.restaurants.store.RestaurantsCacheDateStore
import io.android.projectx.data.features.usermanagement.repository.UserManagementCache
import io.android.projectx.data.features.usermanagement.store.UserManagementCacheDataStore

@Module
abstract class CacheModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun providesDataBase(application: Application): AppDatabase {
            return AppDatabase.getInstance(application)
        }
    }

    @Binds
    abstract fun bindUserManagementCache(userManagementCacheImpl: UserManagementCacheDataStore): UserManagementCache

    @Binds
    abstract fun bindRecipesCache(recipesCacheImpl: RecipesCacheDateStore): RecipesCache

    @Binds
    abstract fun bindRestaurantsCache(restaurantsCacheImpl: RestaurantsCacheDateStore): RestaurantsCache
}