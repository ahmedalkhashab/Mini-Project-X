package io.android.projectx.presentation.di.module.ui.activity

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.android.projectx.presentation.di.module.ui.fragment.LoginFragmentBuildersModule
import io.android.projectx.presentation.di.module.ui.viewmodel.HostViewModelModule
import io.android.projectx.presentation.features.bookmarked.BookmarkedActivity
import io.android.projectx.presentation.features.browse.BrowseActivity
import io.android.projectx.presentation.features.host.MainHostActivity
import io.android.projectx.presentation.features.restaurants.RestaurantsActivity

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [HostViewModelModule::class, LoginFragmentBuildersModule::class])
    abstract fun contributesMainActivity(): MainHostActivity

    @ContributesAndroidInjector(modules = [HostViewModelModule::class])
    abstract fun contributesBrowseActivity(): BrowseActivity

    @ContributesAndroidInjector(modules = [HostViewModelModule::class])
    abstract fun contributesBookmarkedActivity(): BookmarkedActivity

    @ContributesAndroidInjector(modules = [HostViewModelModule::class])
    abstract fun contributesRestaurantsActivity(): RestaurantsActivity

}