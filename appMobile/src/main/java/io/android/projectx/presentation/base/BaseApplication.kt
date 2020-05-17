package io.android.projectx.presentation.base

import android.content.Context
import android.content.res.Configuration
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ProcessLifecycleOwner
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.android.projectx.androidextensions.LocalizationHandler
import io.android.projectx.presentation.di.DaggerAppComponent
import timber.log.Timber

class BaseApplication : DaggerApplication(), LifecycleObserver {

    private var appUpdateManager: AppUpdateManager? = null

    override fun onCreate() {
        super.onCreate()
        initAppUpdateManager()
        ProcessLifecycleOwner.get().lifecycle.addObserver(this);
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

    private fun initAppUpdateManager() {
        if (appUpdateManager == null) appUpdateManager = AppUpdateManager(this)
    }

    private fun setupTimber() {
        Timber.plant(Timber.DebugTree())
    }

}