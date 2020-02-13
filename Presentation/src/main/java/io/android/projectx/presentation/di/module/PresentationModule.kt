package io.android.projectx.presentation.di.module

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import io.android.projectx.presentation.di.ViewModelProviderFactory

@Module
abstract class PresentationModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory
}