package io.android.projectx.presentation.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.android.projectx.presentation.features.bookmarked.BookmarkedActivity
import io.android.projectx.presentation.features.browse.BrowseActivity

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [BrowseViewModelsModule::class])
    abstract fun contributesBrowseActivity(): BrowseActivity

    @ContributesAndroidInjector(modules = [BookmarkedViewModelsModule::class])
    abstract fun contributesBookmarkedActivity(): BookmarkedActivity
}