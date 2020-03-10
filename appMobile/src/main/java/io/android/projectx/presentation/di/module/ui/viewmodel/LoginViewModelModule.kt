package io.android.projectx.presentation.di.module.ui.viewmodel

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.android.projectx.presentation.di.ViewModelKey
import io.android.projectx.presentation.features.usermanagement.login.LoginViewModel
import io.android.projectx.presentation.features.usermanagement.splash.SplashViewModel

@Module
abstract class LoginViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindSplashViewModel(viewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

}