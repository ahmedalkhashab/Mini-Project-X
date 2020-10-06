package io.android.projectx.presentation.features.splash

import android.os.Bundle
import android.os.Handler
import io.android.projectx.presentation.R
import io.android.projectx.presentation.base.BaseFragment
import io.android.projectx.presentation.features.usermanagement.UserManagementViewModel

class SplashFragment : BaseFragment(R.layout.splash_fragment) {

    companion object {
        private const val SPLASH_TIME_OUT: Long = 1500
    }

    private val viewModel: UserManagementViewModel by appViewModels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        navigate(Runnable { navigator.toBrowseRecipesScreen(requireActivity()) })
    }

    private fun navigate(r: Runnable) = Handler().postDelayed(r, SPLASH_TIME_OUT)

}