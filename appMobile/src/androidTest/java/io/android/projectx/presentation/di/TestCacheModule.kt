package io.android.projectx.presentation.di

import android.app.Application
import com.nhaarman.mockitokotlin2.mock
import dagger.Module
import dagger.Provides
import io.android.projectx.cache.AppDatabase
import io.android.projectx.data.features.recipes.repository.RecipesCache

@Module
object TestCacheModule {

    @Provides
    @JvmStatic
    fun provideDatabase(application: Application): AppDatabase {
        return AppDatabase.getInstance(application)
    }

    @Provides
    @JvmStatic
    fun provideRecipesCache(): RecipesCache {
        return mock()
    }

}