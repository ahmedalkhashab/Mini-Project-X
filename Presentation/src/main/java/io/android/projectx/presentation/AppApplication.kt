package io.android.projectx.presentation

import android.app.Application
import timber.log.Timber

class AppApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        setupTimber()
    }

    private fun setupTimber() {
        Timber.plant(Timber.DebugTree())
    }

}