package io.android.projectx.presentation.di.module.ui.viewmodel

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.android.projectx.presentation.di.ViewModelKey
import io.android.projectx.presentation.features.bookmarked.BookmarkedRecipesViewModel
import io.android.projectx.presentation.features.browse.BrowseRecipesViewModel
import io.android.projectx.presentation.features.host.HostViewModel
import io.android.projectx.presentation.features.restaurants.RestaurantsViewModel

@Module
abstract class HostViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HostViewModel::class)
    abstract fun bindHostViewModel(viewModel: HostViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BrowseRecipesViewModel::class)
    abstract fun bindBrowseRecipesViewModel(viewModel: BrowseRecipesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BookmarkedRecipesViewModel::class)
    abstract fun bindBookmarkedRecipesViewModel(viewModel: BookmarkedRecipesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RestaurantsViewModel::class)
    abstract fun bindRestaurantsViewModel(viewModel: RestaurantsViewModel): ViewModel

}