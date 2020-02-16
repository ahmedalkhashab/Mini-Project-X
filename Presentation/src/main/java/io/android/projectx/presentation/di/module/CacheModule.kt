package io.android.projectx.presentation.di.module

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.android.projectx.cache.features.recipes.RecipesCacheImpl
import io.android.projectx.cache.AppDatabase
import io.android.projectx.cache.features.restaurants.RestaurantsCacheImpl
import io.android.projectx.data.features.recipes.repository.RecipesCache
import io.android.projectx.data.features.restaurants.repository.RestaurantsCache

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
    abstract fun bindRecipesCache(recipesCache: RecipesCacheImpl): RecipesCache

    @Binds
    abstract fun bindRestaurantsCache(restaurantsCache: RestaurantsCacheImpl): RestaurantsCache
}