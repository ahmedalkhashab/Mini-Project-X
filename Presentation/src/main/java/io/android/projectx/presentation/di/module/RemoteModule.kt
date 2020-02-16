package io.android.projectx.presentation.di.module

import dagger.Binds
import dagger.Module
import dagger.Provides
import io.android.projectx.data.features.recipes.repository.RecipesRemote
import io.android.projectx.data.features.restaurants.repository.RestaurantsRemote
import io.android.projectx.presentation.BuildConfig
import io.android.projectx.remote.features.recipes.RecipesRemoteImpl
import io.android.projectx.remote.features.RemoteServiceFactory
import io.android.projectx.remote.features.recipes.service.RecipesService
import io.android.projectx.remote.features.restaurants.RestaurantsRemoteImpl
import io.android.projectx.remote.features.restaurants.service.RestaurantsService

@Module
abstract class RemoteModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun provideRemoteServiceFactory(): RemoteServiceFactory {
            return RemoteServiceFactory(BuildConfig.API_BASE_URL, BuildConfig.DEBUG)
        }

        @Provides
        @JvmStatic
        fun provideRecipesService(remoteServiceFactory: RemoteServiceFactory): RecipesService {
            return remoteServiceFactory.recipesService
        }

        @Provides
        @JvmStatic
        fun provideRestaurantsService(remoteServiceFactory: RemoteServiceFactory): RestaurantsService {
            return remoteServiceFactory.restaurantsService
        }
    }

    @Binds
    abstract fun bindRecipesRemote(recipesRemote: RecipesRemoteImpl): RecipesRemote

    @Binds
    abstract fun bindRestaurantsRemote(restaurantsRemote: RestaurantsRemoteImpl): RestaurantsRemote
}