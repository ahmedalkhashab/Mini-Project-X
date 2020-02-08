package io.android.projectx.presentation.test

import android.app.Activity
import android.app.Application
import androidx.test.platform.app.InstrumentationRegistry
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.android.projectx.presentation.di.TestApplicationComponent
import javax.inject.Inject

class TestApplication: Application(), HasActivityInjector {

    @Inject
    lateinit var injector: DispatchingAndroidInjector<Activity>
    private lateinit var appComponent: TestApplicationComponent

    companion object {
        fun appComponent(): TestApplicationComponent {
            return (InstrumentationRegistry.getInstrumentation().targetContext
                    as TestApplication).appComponent
        }
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerTestApplicationComponent.builder().application(this).build()
        appComponent.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return injector
    }
}