package io.android.projectx.presentation.extensions

import android.app.Activity
import android.view.View
import android.widget.ProgressBar
import io.android.projectx.androidextensions.disableUserInteraction
import io.android.projectx.androidextensions.enableUserInteraction
import io.android.projectx.presentation.base.state.Resource

/*
 * Progress Bar related extension functions
 */
fun ProgressBar.updateVisibility(visible: Boolean, disableUIInteraction: Boolean = true) {
    val activity = context as Activity?
    when (visible) {
        true -> {
            if (disableUIInteraction) activity.disableUserInteraction()
            this.visibility = View.VISIBLE
        }
        else -> {
            activity.enableUserInteraction()
            this.visibility = View.INVISIBLE
        }
    }
}

fun ProgressBar.updateVisibility(status: Resource.Status, data: Any? = null) {
    updateVisibility(status == Resource.Status.LOADING && data == null)
}