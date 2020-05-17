package io.android.projectx.presentation.features

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import io.android.projectx.presentation.R
import io.android.projectx.presentation.features.cloudmessaging.CaseStatus
import io.android.projectx.presentation.features.recipes.RecipesHostActivity
import io.android.projectx.presentation.features.splash.SplashHostActivity
import io.android.projectx.presentation.features.usermanagement.UserManagementHostActivity
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Navigator @Inject constructor() {

    private val navOptions = NavOptions.Builder()
        //.setLaunchSingleTop(true)
        .setPopUpTo(R.id.splashFragment, true)
        .setEnterAnim(R.anim.slide_in_right)
        .setExitAnim(R.anim.slide_out_left)
        .setPopEnterAnim(R.anim.slide_in_left)
        .setPopExitAnim(R.anim.slide_out_right)
        .build()

    private val menuNavOptions = NavOptions.Builder()
        .setEnterAnim(R.anim.slide_in_bottom)
        .setExitAnim(R.anim.slide_out_top)
        //.setPopEnterAnim(R.anim.slide_in_top)
        .setPopExitAnim(R.anim.slide_out_bottom)
        .build()

    private val navOptionsNoAnimations = NavOptions.Builder()
        .setPopUpTo(R.id.splashFragment, true)
        .build()

    fun popBackStack(navController: NavController?) {
        navController?.popBackStack()
    }

    fun updatePerStatus(activity: Activity, status: CaseStatus?) {
        when (status) {
            CaseStatus.Status1 -> {
                if (activity !is SplashHostActivity) toSplashScreen(activity)
            }
            CaseStatus.Status2 ->
                if (activity !is UserManagementHostActivity) toLoginScreen(activity)
            null -> {
                // Kindly, no action here
            }
        }
    }

    fun toLoginScreen(activity: Activity) {
        if (activity is UserManagementHostActivity) return
        val intent = Intent(activity, UserManagementHostActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(
            intent/*,
            ActivityOptions.makeSceneTransitionAnimation(activity).toBundle()*/
        )
        activity.finish()
    }

    fun toLoginScreen(fragment: Fragment) {
        val intent = Intent(fragment.requireContext(), UserManagementHostActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        fragment.startActivity(
            intent/*,
            ActivityOptions.makeSceneTransitionAnimation(activity).toBundle()*/
        )
        fragment.requireActivity().finish()
    }

    fun toBrowseRecipesScreen(activity: Activity) {
        if (activity is RecipesHostActivity) return
        val intent = Intent(activity, RecipesHostActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(
            intent/*,
            ActivityOptions.makeSceneTransitionAnimation(activity).toBundle()*/
        )
        activity.finish()
    }

    fun toBookmarkedRecipesScreen(navController: NavController?) {
        val uri = "app://navigation/bookmarked_recipes"
        val deepLink = Uri.parse(uri)
        navController?.navigate(deepLink, navOptions)
    }

    fun toMenuScreen(navController: NavController?) {
        val uri = "app://navigation/menu"
        val deepLink = Uri.parse(uri)
        navController?.navigate(deepLink, menuNavOptions)
    }

    fun toProfileScreen(navController: NavController?) {
        navController?.navigate(R.id.action_menuFragment_to_profileFragment)
    }

    fun toLanguageScreen(navController: NavController?) {
        navController?.navigate(R.id.action_menuFragment_to_languageFragment)
    }

    fun toSplashScreen(activity: Activity) {
        val intent = Intent(activity, SplashHostActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        activity.startActivity(
            intent/*,
            ActivityOptions.makeSceneTransitionAnimation(activity).toBundle()*/
        )
        activity.finish()
    }

}