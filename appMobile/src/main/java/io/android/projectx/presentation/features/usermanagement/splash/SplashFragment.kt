package io.android.projectx.presentation.features.usermanagement.splash

import android.os.Bundle
import androidx.navigation.findNavController
import io.android.projectx.presentation.R
import io.android.projectx.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.splash_fragment.*

class SplashFragment : BaseFragment(R.layout.splash_fragment) {

    private val viewModel: SplashViewModel by appViewModels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        login.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_splashFragment_to_loginFragment)
        }
    }

}