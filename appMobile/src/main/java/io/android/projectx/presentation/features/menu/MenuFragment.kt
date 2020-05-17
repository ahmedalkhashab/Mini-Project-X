package io.android.projectx.presentation.features.menu

import android.os.Bundle
import androidx.navigation.findNavController
import io.android.projectx.presentation.R
import io.android.projectx.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.menu_fragment.*

class MenuFragment : BaseFragment(R.layout.menu_fragment) {

    private val viewModel by appViewModels<MenuViewModel>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        registerListeners()
    }

    private fun registerListeners() {
        profile.setOnClickListener {
            navigator.toProfileScreen(view?.findNavController())
        }
        language.setOnClickListener {
            navigator.toLanguageScreen(view?.findNavController())
        }
        logout.setOnClickListener {
            viewModel.logOut()
            navigator.toLoginScreen(requireActivity())
        }
        /*close.setOnClickListener {
            navigator.popBackStack(view?.findNavController())
        }*/
    }

}