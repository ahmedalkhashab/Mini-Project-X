package io.android.projectx.presentation.base

import android.content.Context
import android.content.res.Configuration
import dagger.android.support.DaggerFragment
import io.android.projectx.androidextensions.LocalizationHandler

abstract class BaseFragment : DaggerFragment() {

    override fun onAttach(context: Context) {
        super.onAttach(LocalizationHandler.onAttach(context))
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        activity?.let { LocalizationHandler.onAttach(it) }
    }

}