package io.android.projectx.presentation.di.module.ui.viewmodel

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.android.projectx.presentation.di.ViewModelKey
import io.android.projectx.presentation.features.bookmarked.BookmarkedRecipesViewModel

@Module
abstract class BookmarkedViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(BookmarkedRecipesViewModel::class)
    abstract fun bindBookmarkedRecipesViewModel(viewModel: BookmarkedRecipesViewModel): ViewModel
}