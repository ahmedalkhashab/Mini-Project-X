package io.android.projectx.presentation.features.usermanagement

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import io.android.projectx.presentation.R
import io.android.projectx.presentation.base.BaseActivity
import io.android.projectx.presentation.features.HostViewModel

class UserManagementHostActivity : BaseActivity(R.layout.user_management_host_activity) {

    private val viewModel by appViewModels<HostViewModel>()
    private lateinit var mainNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainNavController = findNavController(R.id.nav_host_fragment)
    }

    override fun onSupportNavigateUp(): Boolean = mainNavController.navigateUp()

}