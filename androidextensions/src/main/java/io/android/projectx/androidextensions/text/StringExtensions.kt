package io.android.projectx.androidextensions.text

import android.content.Context
import io.android.projectx.androidextensions.LocalizationHandler
import java.util.regex.Matcher
import java.util.regex.Pattern

fun MutableMap<String, String>.toString(context: Context?): String {
    if (context == null) return ""
    val value = this[LocalizationHandler.getCurrentLocale(context)]
    return if (value.isNullOrEmpty()) ""
    else value
}

fun String.isEmailValid(): Boolean {
    val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
    val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
    val matcher: Matcher = pattern.matcher(this)
    return matcher.matches()
}