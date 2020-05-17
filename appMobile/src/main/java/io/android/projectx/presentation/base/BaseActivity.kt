package io.android.projectx.presentation.base

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.LayoutRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import dagger.android.support.DaggerAppCompatActivity
import io.android.projectx.androidextensions.LocalizationHandler
import io.android.projectx.presentation.extensions.handleError
import io.android.projectx.presentation.features.Navigator
import io.android.projectx.presentation.features.cloudmessaging.StatusManager
import javax.inject.Inject

abstract class BaseActivity(@LayoutRes private val layoutRes: Int = 0) : DaggerAppCompatActivity(layoutRes) {

    @Inject
    lateinit var baseViewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var navigator: Navigator
    @Inject
    lateinit var statusManager: StatusManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeObservers()
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocalizationHandler.onAttach(newBase))
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LocalizationHandler.onAttach(this)
    }

    fun handleError(throwable: Throwable?, navController: NavController? = null) {
        handleError(throwable, navController, navigator)
    }

    inline fun <reified VM : ViewModel> appViewModels() = viewModels<VM> { baseViewModelFactory }

    private fun subscribeObservers() {
        statusManager.status.removeObservers(this)
        statusManager.status
            .observe(this, Observer { navigator.updatePerStatus(this, it.data) })
    }

}