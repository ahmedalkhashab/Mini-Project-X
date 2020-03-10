package io.android.projectx.presentation.features.host

import androidx.navigation.findNavController
import io.android.projectx.presentation.R
import io.android.projectx.presentation.base.BaseActivity

class MainHostActivity : BaseActivity(R.layout.main_activity) {

    override fun onSupportNavigateUp() = findNavController(R.id.nav_host_fragment).navigateUp()

}