package io.android.projectx.presentation.di.module.ui.viewmodel

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.android.projectx.presentation.di.ViewModelKey
import io.android.projectx.presentation.features.usermanagement.UserManagementViewModel

@Module
abstract class LoginViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(UserManagementViewModel::class)
    abstract fun bindUserManagementViewModel(viewModel: UserManagementViewModel): ViewModel
}