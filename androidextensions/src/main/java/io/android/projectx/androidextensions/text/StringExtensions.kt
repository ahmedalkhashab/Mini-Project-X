package io.android.projectx.androidextensions.text

import android.content.Context
import io.android.projectx.androidextensions.LocalizationHandler

fun MutableMap<String, String>.toString(context: Context?): String {
    if (context == null) return ""
    val value = this[LocalizationHandler.getCurrentLocale(context)]
    return if (value.isNullOrEmpty()) ""
    else value
}