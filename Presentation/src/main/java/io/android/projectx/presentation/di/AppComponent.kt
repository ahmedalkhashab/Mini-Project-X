package io.android.projectx.presentation.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import io.android.projectx.data.di.module.data.CacheModule
import io.android.projectx.data.di.module.data.DataModule
import io.android.projectx.data.di.module.data.RemoteModule
import io.android.projectx.presentation.base.BaseApplication
import io.android.projectx.presentation.di.module.ui.ApplicationModule
import io.android.projectx.presentation.di.module.ui.PresentationModule
import io.android.projectx.presentation.di.module.ui.UiModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        UiModule::class,
        PresentationModule::class,
        DataModule::class,
        CacheModule::class,
        RemoteModule::class]
)
interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}