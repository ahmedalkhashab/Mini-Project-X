package io.android.projectx.presentation.di.module.ui.activity

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.android.projectx.presentation.di.module.ui.fragment.AuthFragmentBuildersModule
import io.android.projectx.presentation.di.module.ui.fragment.LoginFragmentBuildersModule
import io.android.projectx.presentation.di.module.ui.viewmodel.HostViewModelModule
import io.android.projectx.presentation.features.recipes.bookmarked.BookmarkedFragment
import io.android.projectx.presentation.features.recipes.browse.BrowseFragment
import io.android.projectx.presentation.features.recipes.RecipesHostActivity
import io.android.projectx.presentation.features.restaurants.RestaurantsFragment
import io.android.projectx.presentation.features.splash.SplashHostActivity
import io.android.projectx.presentation.features.usermanagement.UserManagementHostActivity

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
        modules = [HostViewModelModule::class,
            LoginFragmentBuildersModule::class,
            AuthFragmentBuildersModule::class]
    )
    abstract fun contributesRecipesHostActivity(): RecipesHostActivity

    @ContributesAndroidInjector(
        modules = [HostViewModelModule::class,
            LoginFragmentBuildersModule::class,
            AuthFragmentBuildersModule::class]
    )
    abstract fun contributesSplashHostActivity(): SplashHostActivity

    @ContributesAndroidInjector(modules = [HostViewModelModule::class, LoginFragmentBuildersModule::class])
    abstract fun contributesUserManagementHostActivity(): UserManagementHostActivity

}