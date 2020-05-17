package io.android.projectx.presentation.extensions

import android.app.Activity
import android.content.Context
import android.text.TextUtils
import androidx.navigation.NavController
import io.android.projectx.androidextensions.showErrorAlerter
import io.android.projectx.presentation.R
import io.android.projectx.presentation.features.Navigator
import io.android.projectx.remote.base.model.RemoteException
import io.android.projectx.remote.base.model.RemoteException.Kind

fun Activity.handleError(
    throwable: Throwable?,
    navController: NavController? = null,
    navigator: Navigator
) {
    if (navController != null && throwable.shouldLogout())
        navigator.toLoginScreen(this)
    showErrorAlerter(throwable.getError(this))
}

fun Throwable?.shouldLogout(): Boolean {
    return this != null && this is RemoteException && this.kind == Kind.AUTHENTICATION
}

fun Throwable?.getError(context: Context): String {
    return if (this != null && this is RemoteException) {
        when (this.kind) {
            Kind.HTTP,
            Kind.HTTP422WithDATA,
            Kind.AUTHENTICATION -> this.errorData?.message.takeIf { !TextUtils.isEmpty(it) }
                ?: context.getString(R.string.error_unexpected)
            Kind.NETWORK -> context.getString(R.string.error_network)
            Kind.TIMEOUT -> context.getString(R.string.error_timeout)
            Kind.UNEXPECTED -> context.getString(R.string.error_unexpected)
        }
    } else return context.getString(R.string.error_unexpected)
}