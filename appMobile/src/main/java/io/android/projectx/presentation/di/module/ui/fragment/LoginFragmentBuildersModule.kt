package io.android.projectx.presentation.di.module.ui.fragment

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.android.projectx.presentation.di.module.ui.viewmodel.LoginViewModelModule
import io.android.projectx.presentation.features.recipes.bookmarked.BookmarkedFragment
import io.android.projectx.presentation.features.recipes.browse.BrowseFragment
import io.android.projectx.presentation.features.restaurants.RestaurantsFragment
import io.android.projectx.presentation.features.usermanagement.login.LoginFragment
import io.android.projectx.presentation.features.splash.SplashFragment
import io.android.projectx.presentation.features.usermanagement.otp.OtpFragment

@Module
abstract class LoginFragmentBuildersModule {

    @ContributesAndroidInjector(modules = [LoginViewModelModule::class])
    abstract fun contributesSplashFragment(): SplashFragment

    @ContributesAndroidInjector(modules = [LoginViewModelModule::class])
    abstract fun contributesLoginFragment(): LoginFragment

    @ContributesAndroidInjector(modules = [LoginViewModelModule::class])
    abstract fun contributesOtpFragment(): OtpFragment

    @ContributesAndroidInjector(modules = [LoginViewModelModule::class])
    abstract fun contributesBrowseFragment(): BrowseFragment

    @ContributesAndroidInjector(modules = [LoginViewModelModule::class])
    abstract fun contributesBookmarkedFragment(): BookmarkedFragment

    @ContributesAndroidInjector(modules = [LoginViewModelModule::class])
    abstract fun contributesRestaurantsFragment(): RestaurantsFragment

}