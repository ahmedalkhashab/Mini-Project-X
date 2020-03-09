package io.android.projectx.presentation.test

import androidx.test.platform.app.InstrumentationRegistry
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.android.projectx.presentation.di.AppComponent
import io.android.projectx.presentation.di.DaggerAppComponent

class TestApplication: DaggerApplication() {

    private lateinit var appComponent: AppComponent

    companion object {
        fun appComponent(): AppComponent {
            return (InstrumentationRegistry.getInstrumentation().targetContext
                    as TestApplication).appComponent
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        appComponent =  DaggerAppComponent.builder().application(this).build()

        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}