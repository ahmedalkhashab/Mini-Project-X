package io.android.projectx.androidextensions

import android.text.Html
import android.widget.TextView

fun TextView.setHtml(content: String) {
    text = Html.fromHtml(content, Html.FROM_HTML_MODE_COMPACT)
}