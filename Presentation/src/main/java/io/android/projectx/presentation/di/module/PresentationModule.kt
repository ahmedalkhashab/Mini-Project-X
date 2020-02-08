package io.android.projectx.presentation.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import io.android.projectx.presentation.di.ViewModelFactory
import io.android.projectx.presentation.features.bookmarked.BrowseBookmarkedRecipesViewModel
import io.android.projectx.presentation.features.browse.BrowseRecipesViewModel
import kotlin.reflect.KClass

@Module
abstract class PresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(BrowseRecipesViewModel::class)
    abstract fun bindBrowseRecipesViewModel(viewModel: BrowseRecipesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BrowseBookmarkedRecipesViewModel::class)
    abstract fun bindBrowseBookmarkedRecipesViewModel(viewModel: BrowseBookmarkedRecipesViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)