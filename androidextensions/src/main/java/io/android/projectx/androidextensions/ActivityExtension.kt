package io.android.projectx.androidextensions


import android.app.Activity
import android.view.Window
import android.view.WindowManager
import androidx.annotation.StringRes
import com.tapadoo.alerter.Alerter


fun Activity?.showErrorAlerter(message: String?) {
    this ?: return
    message?.let {
        Alerter.create(this).setTitle("")
            .setText(it)
            .setBackgroundColorRes(android.R.color.darker_gray)
            .setDuration(3000)
            .show()
    }
}

fun Activity?.showErrorAlerter(@StringRes message: Int) {
    this ?: return
    showErrorAlerter(getString(message))
}

fun Activity?.showAlerter(message: String?) {
    this ?: return
    message?.let {
        Alerter.create(this).setTitle("")
            .setText(it)
            .setBackgroundColorRes(android.R.color.holo_green_dark)
            .setDuration(3000)
            .show()
    }
}

fun Activity?.showAlerter(@StringRes message: Int) {
    this ?: return
    showAlerter(getString(message))
}

fun Activity?.disableUserInteraction() {
    this?.window.disableUserInteraction()
}

fun Activity?.enableUserInteraction() {
    this?.window.enableUserInteraction()
}

fun Window?.disableUserInteraction() {
    this?.setFlags(
        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
    )
}

fun Window?.enableUserInteraction() {
    this?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
}