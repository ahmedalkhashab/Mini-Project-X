package io.android.projectx.androidextensions.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import io.android.projectx.extensions.fromJson
import io.android.projectx.extensions.toJson
import java.util.*


fun Context.putPreferenceWithValue(key: String, value: String) {
    getEncryptedPreferences(getMasterKeyAlias()).edit().apply {
        putString(key.toLowerCase(Locale.getDefault()), value)
    }.apply()
}

fun Context.clearPreference(key: String) {
    getEncryptedPreferences(getMasterKeyAlias()).edit().apply {
        remove(key)
    }.apply()
}

inline fun <reified T> Context.putPreference(key: String, value: T) {
    getEncryptedPreferences(getMasterKeyAlias()).edit().apply {
        putString(key.toLowerCase(Locale.getDefault()), value.toJson())
    }.apply()
}

inline fun <reified T> Context.putPreference(map: MutableMap<String, T>) {
    getEncryptedPreferences(getMasterKeyAlias()).edit().apply {
        map.forEach { putString(it.key.toLowerCase(Locale.getDefault()), it.value.toJson()) }
    }.apply()
}

inline fun <reified T> Context.getPreference(key: String): T? {
    val json: String? = getEncryptedPreferences(getMasterKeyAlias()).getString(
        key.toLowerCase(Locale.getDefault()),
        ""
    )
    return if (json.isNullOrBlank()) null else fromJson(json)
}

fun Context.getPreferenceWithValue(key: String): String {
    val json: String? = getEncryptedPreferences(getMasterKeyAlias()).getString(
        key.toLowerCase(Locale.getDefault()),
        ""
    )
    return if (json.isNullOrBlank()) "" else json
}

fun Context.getEncryptedPreferences(masterKeyAlias: String): SharedPreferences {
    return EncryptedSharedPreferences.create(
        "encrypted_preferences", // fileName
        masterKeyAlias, // masterKeyAlias
        this, // context (we use application context)
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV, // prefKeyEncryptionScheme
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM // prefValueEncryptionScheme
    )
}

fun getMasterKeyAlias() = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)