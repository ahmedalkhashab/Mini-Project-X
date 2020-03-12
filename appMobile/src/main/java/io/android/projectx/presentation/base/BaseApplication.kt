package io.android.projectx.presentation.base

import android.content.Context
import android.content.res.Configuration
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.android.projectx.androidextensions.LocalizationHandler
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

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base?.let { LocalizationHandler.onAttach(it) })
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LocalizationHandler.onAttach(this);
    }

    private fun setupTimber() {
        Timber.plant(Timber.DebugTree())
    }

}