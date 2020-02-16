package io.android.projectx.presentation.di.module.activity

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.android.projectx.presentation.di.module.viewmodel.BookmarkedViewModelModule
import io.android.projectx.presentation.di.module.viewmodel.BrowseViewModelModule
import io.android.projectx.presentation.di.module.viewmodel.RestaurantsViewModelModule
import io.android.projectx.presentation.features.bookmarked.BookmarkedActivity
import io.android.projectx.presentation.features.browse.BrowseActivity
import io.android.projectx.presentation.features.restaurants.RestaurantsActivity

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [BrowseViewModelModule::class])
    abstract fun contributesBrowseActivity(): BrowseActivity

    @ContributesAndroidInjector(modules = [BookmarkedViewModelModule::class])
    abstract fun contributesBookmarkedActivity(): BookmarkedActivity

    @ContributesAndroidInjector(modules = [RestaurantsViewModelModule::class])
    abstract fun contributesRestaurantsActivity(): RestaurantsActivity

}