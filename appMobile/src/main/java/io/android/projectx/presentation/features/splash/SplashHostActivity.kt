package io.android.projectx.presentation.features.splash

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import io.android.projectx.presentation.R
import io.android.projectx.presentation.base.BaseActivity
import io.android.projectx.presentation.features.HostViewModel

class SplashHostActivity : BaseActivity(R.layout.splash_host_activity) {

    private val viewModel by appViewModels<HostViewModel>()
    private lateinit var mainNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainNavController = findNavController(R.id.nav_host_fragment)
    }

    override fun onSupportNavigateUp(): Boolean = mainNavController.navigateUp()

    /*override fun onBackPressed() {
   when (mainNavController.currentDestination?.id) {
       R.id.splashFragment, R.id.loginFragment,
       R.id.serviceAreasFragment, R.id.statusFragment,
       R.id.requestNextAreaFragment -> {
           //  kill app and open splash when open app again
           navigator.toSplashScreen(mainNavController, false)
           finish()
       }
       else -> {
           super.onBackPressed()
       }
   }
}*/

}