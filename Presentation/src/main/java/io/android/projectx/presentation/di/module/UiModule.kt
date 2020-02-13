package io.android.projectx.presentation.di.module

import dagger.Binds
import dagger.Module
import io.android.projectx.domain.base.executor.PostExecutionThread
import io.android.projectx.presentation.UiThread

@Module(includes = [ActivityBuildersModule::class])
abstract class UiModule {

    @Binds
    abstract fun bindPostExecutionThread(uiThread: UiThread): PostExecutionThread
}