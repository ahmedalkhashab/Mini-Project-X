package io.android.projectx.presentation.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import io.android.projectx.domain.repository.RecipesRepository
import io.android.projectx.presentation.di.module.PresentationModule
import io.android.projectx.presentation.di.module.UiModule
import io.android.projectx.presentation.test.TestApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        TestApplicationModule::class,
        TestCacheModule::class,
        TestDataModule::class,
        PresentationModule::class,
        UiModule::class,
        TestRemoteModule::class
    ]
)
interface TestAppComponent : AndroidInjector<TestApplication> {

    fun recipesRepository(): RecipesRepository

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): TestAppComponent
    }

}