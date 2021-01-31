package io.android.projectx.presentation.base

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.android.projectx.androidextensions.LocalizationHandler
import io.android.projectx.presentation.base.dagger.DaggerBottomSheetDialogFragment
import io.android.projectx.presentation.extensions.handleError
import io.android.projectx.presentation.features.Navigator
import javax.inject.Inject

abstract class BaseBottomSheetFragment(@LayoutRes private val layoutRest: Int = 0) : DaggerBottomSheetDialogFragment() {

    @Inject
    lateinit var baseViewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var navigator: Navigator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(layoutRest, container, false)

    override fun onAttach(context: Context) {
        super.onAttach(LocalizationHandler.onAttach(context))
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        requireActivity().let { LocalizationHandler.onAttach(it) }
    }

    fun handleError(throwable: Throwable?)
            = requireActivity().handleError(throwable, navigator)

    inline fun <reified VM : ViewModel> appViewModels() = viewModels<VM> { baseViewModelFactory }
    inline fun <reified VM : ViewModel> appActivityViewModels() = activityViewModels<VM> { baseViewModelFactory }

}