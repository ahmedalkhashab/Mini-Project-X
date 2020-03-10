package io.android.projectx.presentation.di.module.ui.fragment

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.android.projectx.presentation.di.module.ui.viewmodel.LoginViewModelModule
import io.android.projectx.presentation.features.usermanagement.login.LoginFragment
import io.android.projectx.presentation.features.usermanagement.splash.SplashFragment

@Module
abstract class LoginFragmentBuildersModule {

    @ContributesAndroidInjector(modules = [LoginViewModelModule::class])
    abstract fun contributesSplashFragment(): SplashFragment

    @ContributesAndroidInjector(modules = [LoginViewModelModule::class])
    abstract fun contributesLoginFragment(): LoginFragment
}