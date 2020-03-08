package io.android.projectx.presentation.di.module

import dagger.Binds
import dagger.Module
import dagger.Provides
import io.android.projectx.data.features.recipes.repository.RecipesRemote
import io.android.projectx.data.features.recipes.store.RecipesRemoteDataStore
import io.android.projectx.data.features.restaurants.repository.RestaurantsRemote
import io.android.projectx.data.features.restaurants.store.RestaurantsRemoteDataStore
import io.android.projectx.data.features.usermanagement.UserManagementDataRepository
import io.android.projectx.data.features.usermanagement.repository.UserManagementRemote
import io.android.projectx.data.features.usermanagement.store.UserManagementCacheDataStore
import io.android.projectx.data.features.usermanagement.store.UserManagementRemoteDataStore
import io.android.projectx.presentation.BuildConfig
import io.android.projectx.remote.base.interceptor.Authenticator
import io.android.projectx.remote.base.interceptor.AuthorizationInterceptor
import io.android.projectx.remote.base.interceptor.RequestHeaders
import io.android.projectx.remote.features.RemoteServiceFactory
import io.android.projectx.remote.features.recipes.service.RecipesService
import io.android.projectx.remote.features.restaurants.service.RestaurantsService
import io.android.projectx.remote.features.usermanagement.service.UserManagementService

@Module
abstract class RemoteModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun provideRequestHeaders(userManagementCache: UserManagementCacheDataStore): RequestHeaders {
            return RequestHeaders(userManagementCache)
        }

        @Provides
        @JvmStatic
        fun provideAuthenticator(
            requestHeaders: RequestHeaders,
            userManagementRepository: dagger.Lazy<UserManagementDataRepository>,
            userManagementRemote: dagger.Lazy<UserManagementRemoteDataStore>
        ): Authenticator {
            return Authenticator(requestHeaders, userManagementRepository, userManagementRemote)
        }

        @Provides
        @JvmStatic
        fun provideAuthorizationInterceptor(requestHeaders: RequestHeaders): AuthorizationInterceptor {
            return AuthorizationInterceptor(requestHeaders)
        }

        @Provides
        @JvmStatic
        fun provideUserManagementService(
            authorizationInterceptor: AuthorizationInterceptor,
            authenticator: Authenticator
        ): UserManagementService {
            return RemoteServiceFactory.provideUserManagementService(
                BuildConfig.API_BASE_URL,
                BuildConfig.DEBUG, authorizationInterceptor, authenticator
            )
        }


        @Provides
        @JvmStatic
        fun provideRecipesService(
            authorizationInterceptor: AuthorizationInterceptor,
            authenticator: Authenticator
        ): RecipesService {
            return RemoteServiceFactory.provideRecipesService(
                BuildConfig.API_BASE_URL,
                BuildConfig.DEBUG, authorizationInterceptor, authenticator
            )
        }

        @Provides
        @JvmStatic
        fun provideRestaurantsService(
            authorizationInterceptor: AuthorizationInterceptor,
            authenticator: Authenticator
        ): RestaurantsService {
            return RemoteServiceFactory.provideRestaurantsService(
                BuildConfig.API_BASE_URL,
                BuildConfig.DEBUG, authorizationInterceptor, authenticator
            )
        }

    }

    @Binds
    abstract fun bindUserManagementRemote(userManagementRemoteImpl: UserManagementRemoteDataStore): UserManagementRemote

    @Binds
    abstract fun bindRecipesRemote(recipesRemoteImpl: RecipesRemoteDataStore): RecipesRemote

    @Binds
    abstract fun bindRestaurantsRemote(restaurantsRemoteImpl: RestaurantsRemoteDataStore): RestaurantsRemote
}