package io.android.projectx.presentation.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.android.projectx.presentation.features.browse.BrowseRecipesViewModel

@Module
abstract class BrowseViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(BrowseRecipesViewModel::class)
    abstract fun bindBrowseRecipesViewModel(viewModel: BrowseRecipesViewModel): ViewModel
}