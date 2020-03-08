package io.android.projectx.data.di.module.data

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import io.android.projectx.remote.base.RemoteFactory.provideRetrofit
import io.android.projectx.remote.features.recipes.service.RecipesService
import io.android.projectx.remote.features.restaurants.service.RestaurantsService
import io.android.projectx.remote.features.usermanagement.service.UserManagementService
import okhttp3.OkHttpClient
import javax.inject.Named

@Module
object ApiModule {

    @Provides
    @JvmStatic
    fun provideUserManagementService(
        @Named("base.url.user.management") baseUrl: String,
        okHttpClient: OkHttpClient,
        gson: Gson
    ): UserManagementService = provideRetrofit(baseUrl, okHttpClient, gson)
        .create(UserManagementService::class.java)

    @Provides
    @JvmStatic
    fun provideRecipesService(
        @Named("base.url.recipes") baseUrl: String,
        okHttpClient: OkHttpClient,
        gson: Gson
    ): RecipesService = provideRetrofit(baseUrl, okHttpClient, gson)
        .create(RecipesService::class.java)

    @Provides
    @JvmStatic
    fun provideRestaurantsService(
        @Named("base.url.restaurants") baseUrl: String,
        okHttpClient: OkHttpClient,
        gson: Gson
    ): RestaurantsService = provideRetrofit(baseUrl, okHttpClient, gson)
        .create(RestaurantsService::class.java)

}