package io.android.projectx.presentation.features.profile

import android.os.Bundle
import android.view.View.GONE
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import io.android.projectx.presentation.R
import io.android.projectx.presentation.base.BaseFragment
import io.android.projectx.presentation.base.model.UserView
import io.android.projectx.presentation.base.state.AuthResource
import io.android.projectx.presentation.base.state.Resource
import io.android.projectx.presentation.base.state.Resource.Status.AuthStatus
import io.android.projectx.presentation.extensions.updateVisibility
import kotlinx.android.synthetic.main.profile_fragment.*

class ProfileFragment : BaseFragment(R.layout.profile_fragment) {

    private val viewModel: ProfileViewModel by appViewModels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        subscribeObservers()
        viewModel.getUser()
    }

    private fun subscribeObservers() {
        viewModel.profile.observe(viewLifecycleOwner, Observer {
            progressbar.updateVisibility(it.status)
            when (it.status) {
                Resource.Status.ERROR -> handleError(it.throwable)
                AuthStatus.AUTHENTICATED -> handleData(it)
                AuthStatus.ANONYMOUS -> {
                }
            }
        })
    }

    private fun handleData(it: AuthResource<UserView?>) {
        val user = it.data
        if (user != null) {
            // user.fullName
            if (user.email.isNotBlank()) valueName.text = user.email
            else {
                titleName.visibility = GONE
                valueName.visibility = GONE
            }
            // user.mobile
            if (user.mobile.isNotBlank()) valueMobile.text = user.mobile
            else {
                titleMobile.visibility = GONE
                valueMobile.visibility = GONE
            }
            // user.roleName
            val roleName = ""
            if (roleName.isNotBlank()) valueRole.text = roleName
            else {
                titleRole.visibility = GONE
                valueRole.visibility = GONE
            }
            // user.companyName
            val companyName = ""
            if (companyName.isNotBlank()) valueCompany.text = companyName
            else {
                titleCompany.visibility = GONE
                valueCompany.visibility = GONE
            }
        }
    }

}