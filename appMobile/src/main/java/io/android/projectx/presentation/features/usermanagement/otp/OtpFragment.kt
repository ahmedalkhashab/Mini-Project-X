package io.android.projectx.presentation.features.usermanagement.otp

import android.os.Bundle
import io.android.projectx.presentation.R
import io.android.projectx.presentation.base.BaseFragment
import io.android.projectx.presentation.features.usermanagement.UserManagementViewModel

class OtpFragment : BaseFragment(R.layout.otp_fragment) {

    private val viewModel: UserManagementViewModel by appViewModels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        subscribeObservers()
    }

    private fun subscribeObservers() {}

}