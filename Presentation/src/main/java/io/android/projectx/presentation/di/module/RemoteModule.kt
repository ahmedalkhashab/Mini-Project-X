package io.android.projectx.presentation.di.module

import dagger.Binds
import dagger.Module
import dagger.Provides
import io.android.projectx.data.repository.RecipesRemote
import io.android.projectx.presentation.BuildConfig
import io.android.projectx.remote.RecipesRemoteImpl
import io.android.projectx.remote.service.RecipesService
import io.android.projectx.remote.service.RecipesServiceFactory

@Module
abstract class RemoteModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideRecipesService(): RecipesService {
            return RecipesServiceFactory.makeRecipesService(
                BuildConfig.API_BASE_URL,
                BuildConfig.DEBUG
            )
        }
    }

    @Binds
    abstract fun bindRecipesRemote(recipesRemote: RecipesRemoteImpl): RecipesRemote
}