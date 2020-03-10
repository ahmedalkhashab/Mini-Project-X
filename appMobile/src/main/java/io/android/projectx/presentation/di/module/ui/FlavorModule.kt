package io.android.projectx.presentation.di.module.ui

import android.app.Application
import dagger.Module
import dagger.Provides
import io.android.projectx.presentation.BuildConfig
import java.io.File
import javax.inject.Named

@Module
object FlavorModule {

    @Provides
    @JvmStatic
    @Named("signing.configs.debug")
    fun provideDebugState(): Boolean {
        return BuildConfig.DEBUG
    }

    @Provides
    @JvmStatic
    @Named("http.cache.parent.directory")
    fun provideHttpCacheDir(application: Application): File {
        return application.cacheDir
    }

    @Provides
    @JvmStatic
    @Named("http.cache.size")
    fun provideHttpCacheSize(): Long {
        return 10 * 1024 * 1024 // 10 MB
    }

    @Provides
    @JvmStatic
    @Named("base.url.user.management")
    fun provideUserManagementBaseURL(): String {
        return BuildConfig.API_BASE_URL
    }

    @Provides
    @JvmStatic
    @Named("base.url.recipes")
    fun provideRecipesBaseURL(): String {
        return BuildConfig.API_BASE_URL
    }

    @Provides
    @JvmStatic
    @Named("base.url.restaurants")
    fun provideRestaurantsBaseURL(): String {
        return BuildConfig.API_BASE_URL
    }

}