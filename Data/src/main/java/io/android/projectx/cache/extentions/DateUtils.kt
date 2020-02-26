package io.android.projectx.cache.extentions

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

private const val DATE_ISO_8601_FORMAT = "yyyy-MM-dd'T'hh:mm:ss'Z'"

fun String.getDate(): Date? {
    return if (this.isDateInISO8601()) {
        val format = SimpleDateFormat(DATE_ISO_8601_FORMAT, Locale.getDefault())
        format.timeZone = TimeZone.getTimeZone("GMT")
        format.parse(this)
    } else {
        null
    }
}

fun Date.getOffsetDate(): String {
    val format = SimpleDateFormat(DATE_ISO_8601_FORMAT, Locale.getDefault())
    format.timeZone = TimeZone.getTimeZone("GMT")
    return format.format(this)
}

private fun String.isDateInISO8601(): Boolean {
    return try {
        val format = SimpleDateFormat(DATE_ISO_8601_FORMAT, Locale.getDefault())
        format.timeZone = TimeZone.getTimeZone("GMT")
        format.parse(this)
        true
    } catch (e: ParseException) {
        false
    }
}