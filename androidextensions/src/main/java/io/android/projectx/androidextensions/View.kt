package io.android.projectx.androidextensions

import android.view.View

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide(keepSpace: Boolean = false) {
    visibility = if (keepSpace) View.INVISIBLE else View.GONE
}