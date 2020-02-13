package io.android.projectx.presentation

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.android.projectx.presentation.di.DaggerAppComponent
import timber.log.Timber

class BaseApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        setupTimber()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

    private fun setupTimber() {
        Timber.plant(Timber.DebugTree())
    }

}