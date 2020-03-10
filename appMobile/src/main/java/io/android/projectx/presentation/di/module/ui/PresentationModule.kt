package io.android.projectx.presentation.di.module.ui

import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import io.android.projectx.presentation.di.ViewModelProviderFactory

@Module
class PresentationModule {

    @Provides
    fun provideViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory = factory

}