package io.android.projectx.presentation.base

import android.content.Context
import android.content.res.Configuration
import androidx.annotation.LayoutRes
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import io.android.projectx.androidextensions.LocalizationHandler
import javax.inject.Inject

abstract class BaseFragment(@LayoutRes private val layoutRest: Int = 0) : DaggerFragment(layoutRest) {

    @Inject
    lateinit var baseViewModelFactory: ViewModelProvider.Factory

    override fun onAttach(context: Context) {
        super.onAttach(LocalizationHandler.onAttach(context))
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        requireActivity().let { LocalizationHandler.onAttach(it) }
    }

    inline fun <reified VM : ViewModel> appViewModels() = viewModels<VM> { baseViewModelFactory }
    inline fun <reified VM : ViewModel> appActivityViewModels() = activityViewModels<VM> { baseViewModelFactory }

}