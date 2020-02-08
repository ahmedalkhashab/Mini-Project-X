package io.android.projectx.presentation.di.module

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.android.projectx.domain.executor.PostExecutionThread
import io.android.projectx.presentation.UiThread
import io.android.projectx.presentation.features.bookmarked.BookmarkedActivity
import io.android.projectx.presentation.features.browse.BrowseActivity

@Module
abstract class UiModule {

    @Binds
    abstract fun bindPostExecutionThread(uiThread: UiThread): PostExecutionThread

    @ContributesAndroidInjector
    abstract fun contributesBrowseActivity(): BrowseActivity

    @ContributesAndroidInjector
    abstract fun contributesBookmarkedActivity(): BookmarkedActivity
}