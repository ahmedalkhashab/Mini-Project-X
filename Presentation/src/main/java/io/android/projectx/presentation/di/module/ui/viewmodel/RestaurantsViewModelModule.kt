package io.android.projectx.presentation.di.module.ui.viewmodel

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.android.projectx.presentation.di.ViewModelKey
import io.android.projectx.presentation.features.restaurants.RestaurantsViewModel

@Module
abstract class RestaurantsViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(RestaurantsViewModel::class)
    abstract fun bindRestaurantsViewModel(viewModel: RestaurantsViewModel): ViewModel
}