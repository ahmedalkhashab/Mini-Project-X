package io.android.projectx.remote.features

import io.android.projectx.remote.base.RemoteFactory
import io.android.projectx.remote.base.interceptor.Authenticator
import io.android.projectx.remote.base.interceptor.AuthorizationInterceptor
import io.android.projectx.remote.features.recipes.service.RecipesService
import io.android.projectx.remote.features.usermanagement.service.UserManagementService
import io.android.projectx.remote.features.restaurants.service.RestaurantsService

object RemoteServiceFactory {

    fun provideUserManagementService(
        baseUrl: String,
        isDebug: Boolean,
        authorizationInterceptor: AuthorizationInterceptor,
        authenticator: Authenticator
    ): UserManagementService {
        val okHttpClient = RemoteFactory.provideOkHttpClient(isDebug, authorizationInterceptor, authenticator)
        val retrofit = RemoteFactory.provideRetrofit(baseUrl, okHttpClient, RemoteFactory.provideGson())
        return retrofit.create(UserManagementService::class.java)
    }

    fun provideRecipesService(
        baseUrl: String,
        isDebug: Boolean,
        authorizationInterceptor: AuthorizationInterceptor,
        authenticator: Authenticator
    ): RecipesService {
        val okHttpClient = RemoteFactory.provideOkHttpClient(isDebug, authorizationInterceptor, authenticator)
        val retrofit = RemoteFactory.provideRetrofit(baseUrl, okHttpClient, RemoteFactory.provideGson())
        return retrofit.create(RecipesService::class.java)
    }

    fun provideRestaurantsService(
        baseUrl: String,
        isDebug: Boolean,
        authorizationInterceptor: AuthorizationInterceptor,
        authenticator: Authenticator
    ): RestaurantsService {
        val okHttpClient = RemoteFactory.provideOkHttpClient(isDebug, authorizationInterceptor, authenticator)
        val retrofit = RemoteFactory.provideRetrofit(baseUrl, okHttpClient, RemoteFactory.provideGson())
        return retrofit.create(RestaurantsService::class.java)
    }

}