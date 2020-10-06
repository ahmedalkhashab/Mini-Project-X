package io.android.projectx.presentation.features

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import io.android.projectx.androidextensions.isRTL
import io.android.projectx.presentation.R
import io.android.projectx.presentation.features.cloudmessaging.CaseStatus
import io.android.projectx.presentation.features.recipes.RecipesHostActivity
import io.android.projectx.presentation.features.splash.SplashHostActivity
import io.android.projectx.presentation.features.usermanagement.UserManagementHostActivity
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Navigator @Inject constructor() {

    private fun navOptions(isRTL: Boolean): NavOptions = NavOptions.Builder()
        //.setLaunchSingleTop(true)
        .setPopUpTo(R.id.splashFragment, true)
        .setEnterAnim(if (isRTL) R.anim.slide_in_screen_start else R.anim.slide_in_screen_end)
        .setExitAnim(if (isRTL) R.anim.slide_out_screen_end else R.anim.slide_out_screen_start)
        .setPopEnterAnim(if (isRTL) R.anim.slide_in_screen_end else R.anim.slide_in_screen_start)
        .setPopExitAnim(if (isRTL) R.anim.slide_out_screen_start else R.anim.slide_out_screen_end)
        .build()

    private fun menuNavOptions(): NavOptions = NavOptions.Builder()
        .setEnterAnim(R.anim.slide_in_bottom)
        .setExitAnim(R.anim.slide_out_top)
        // appear directly // no animation
        //.setPopEnterAnim(R.anim.slide_in_top)
        .setPopExitAnim(R.anim.slide_out_bottom)
        .build()

    private val navOptionsNoAnimations = NavOptions.Builder()
        .setPopUpTo(R.id.splashFragment, true)
        .build()

    fun popBackStack(navController: NavController?) = navController?.popBackStack()

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
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        activity.startActivity(intent, activityOptions(activity).toBundle())
        finishWithOverridePendingTransition(activity)
    }

    fun toLoginScreen(fragment: Fragment) {
        val intent = Intent(fragment.requireContext(), UserManagementHostActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        fragment.startActivity(intent, activityOptions(fragment.requireActivity()).toBundle())
        finishWithOverridePendingTransition(fragment.requireActivity())
    }

    fun toBrowseRecipesScreen(activity: Activity) {
        if (activity is RecipesHostActivity) return
        val intent = Intent(activity, RecipesHostActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        activity.startActivity(intent, activityOptions(activity).toBundle())
        finishWithOverridePendingTransition(activity)
    }

    fun toBookmarkedRecipesScreen(navController: NavController?, isRTL: Boolean) {
        val uri = "app://navigation/bookmarked_recipes"
        val deepLink = Uri.parse(uri)
        navController?.navigate(deepLink, navOptions(isRTL))
    }

    fun toMenuScreen(navController: NavController?) {
        val uri = "app://navigation/menu"
        val deepLink = Uri.parse(uri)
        navController?.navigate(deepLink, menuNavOptions())
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
        activity.startActivity(intent, activityOptions(activity).toBundle())
        finishWithOverridePendingTransition(activity)
    }

    private fun activityOptions(activity: Activity): ActivityOptions {
        val isRTL = activity.isRTL(R.bool.is_rtl)
        val enterAnim = if (isRTL) R.anim.slide_in_screen_start else R.anim.slide_in_screen_end
        val exitAnim = if (isRTL) R.anim.slide_out_screen_end else R.anim.slide_out_screen_start
        return ActivityOptions.makeCustomAnimation(activity, enterAnim, exitAnim)
    }

    private fun finishWithOverridePendingTransition(activity: Activity) {
        val isRTL = activity.isRTL(R.bool.is_rtl)
        val enterAnim = if (isRTL) R.anim.slide_in_screen_end else R.anim.slide_in_screen_start
        val exitAnim = if (isRTL) R.anim.slide_out_screen_start else R.anim.slide_out_screen_end
        activity.finish()
        activity.overridePendingTransition(enterAnim, exitAnim)
    }

}