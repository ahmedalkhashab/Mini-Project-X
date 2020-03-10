package io.android.projectx.presentation.base

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.LayoutRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import io.android.projectx.androidextensions.LocalizationHandler
import io.android.projectx.presentation.base.state.AuthResource.AuthStatus
import timber.log.Timber
import javax.inject.Inject

abstract class BaseActivity(@LayoutRes private val layoutRes: Int = 0) : DaggerAppCompatActivity(layoutRes) {

    @Inject
    lateinit var baseViewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var sessionManager: SessionManager

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

    private fun subscribeObservers() {
        sessionManager.cachedUser.observe(this,
            Observer { userResource ->
                when (userResource.status) {
                    AuthStatus.LOADING -> {
                        //showProgressBar(true)
                        Timber.d("onChanged: User LOADING")
                    }
                    AuthStatus.ERROR -> {
                        //showProgressBar(false)
                        Timber.d("onChanged: User ERROR")
                    }
                    AuthStatus.AUTHENTICATED -> {
                        //showProgressBar(false)
                        Timber.d("onChanged: AUTHENTICATED: %s", userResource.data?.mobile)
                    }
                    AuthStatus.NOT_AUTHENTICATED -> {
                        //showProgressBar(false)
                        Timber.d("onChanged: NOT_AUTHENTICATED: %s", userResource.data?.userStatus)
                        navLoginScreenIfNeed()
                    }
                }
            })
    }

    private fun navLoginScreenIfNeed() {
        // todo navigate to Login Screen If Need
    }

    inline fun <reified VM : ViewModel> appViewModels() = viewModels<VM> { baseViewModelFactory }

}