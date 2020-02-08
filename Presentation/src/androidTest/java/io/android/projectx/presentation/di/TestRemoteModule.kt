package io.android.projectx.presentation.di

import com.nhaarman.mockitokotlin2.mock
import dagger.Module
import dagger.Provides
import io.android.projectx.data.repository.RecipesRemote
import io.android.projectx.remote.service.RecipesService

@Module
object TestRemoteModule {

    @Provides
    @JvmStatic
    fun provideRecipesService(): RecipesService {
        return mock()
    }

    @Provides
    @JvmStatic
    fun provideRecipesRemote(): RecipesRemote {
        return mock()
    }

}