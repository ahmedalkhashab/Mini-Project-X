package io.android.projectx.presentation.di

import com.nhaarman.mockitokotlin2.mock
import dagger.Module
import dagger.Provides
import io.android.projectx.domain.repository.RecipesRepository
import javax.inject.Singleton

@Module
object TestDataModule {

    @Provides
    @JvmStatic
    @Singleton
    fun provideDataRepository(): RecipesRepository {
        return mock()
    }

}