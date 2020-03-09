package io.android.projectx.presentation.di.module.ui

import dagger.Binds
import dagger.Module
import io.android.projectx.domain.base.executor.PostExecutionThread
import io.android.projectx.presentation.base.UiThread
import io.android.projectx.presentation.di.module.ui.activity.ActivityBuildersModule

@Module(includes = [ActivityBuildersModule::class])
abstract class UiModule {

    @Binds
    abstract fun bindPostExecutionThread(uiThread: UiThread): PostExecutionThread
}