package io.android.projectx.presentation.di

import android.app.Application
import com.nhaarman.mockitokotlin2.mock
import dagger.Module
import dagger.Provides
import io.android.projectx.cache.recipes.db.RecipesDatabase
import io.android.projectx.data.repository.RecipesCache

@Module
object TestCacheModule {

    @Provides
    @JvmStatic
    fun provideDatabase(application: Application): RecipesDatabase {
        return RecipesDatabase.getInstance(application)
    }

    @Provides
    @JvmStatic
    fun provideRecipesCache(): RecipesCache {
        return mock()
    }

}