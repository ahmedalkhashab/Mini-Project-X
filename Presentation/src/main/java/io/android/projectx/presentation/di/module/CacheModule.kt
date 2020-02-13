package io.android.projectx.presentation.di.module

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.android.projectx.cache.recipes.RecipesCacheImpl
import io.android.projectx.cache.recipes.db.RecipesDatabase
import io.android.projectx.data.repository.RecipesCache

@Module
abstract class CacheModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun providesDataBase(application: Application): RecipesDatabase {
            return RecipesDatabase.getInstance(application)
        }
    }

    @Binds
    abstract fun bindRecipesCache(recipesCache: RecipesCacheImpl): RecipesCache
}