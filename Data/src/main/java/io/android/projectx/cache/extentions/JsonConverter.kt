package io.android.projectx.cache.extentions

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> fromJson(json: String): T =
    Gson().fromJson<T>(json, object : TypeToken<T>() {}.type)

fun <T> T.toJson(): String = Gson().toJson(this)