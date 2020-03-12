package io.android.projectx.presentation.base

import android.content.Context
import android.content.res.Configuration
import dagger.android.support.DaggerAppCompatActivity
import io.android.projectx.androidextensions.LocalizationHandler

abstract class BaseActivity : DaggerAppCompatActivity() {

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocalizationHandler.onAttach(newBase))
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LocalizationHandler.onAttach(this)
    }

}