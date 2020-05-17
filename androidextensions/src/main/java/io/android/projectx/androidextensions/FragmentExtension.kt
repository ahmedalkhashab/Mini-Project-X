package io.android.projectx.androidextensions

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

fun Fragment?.showErrorAlerter(message: String?) {
    this ?: return
    activity.showErrorAlerter(message)
}

fun Fragment?.showErrorAlerter(@StringRes message: Int) {
    this ?: return
    activity.showErrorAlerter(message)
}