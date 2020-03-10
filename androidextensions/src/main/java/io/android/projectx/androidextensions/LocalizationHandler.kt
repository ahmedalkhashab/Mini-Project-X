package io.android.projectx.androidextensions

import android.content.Context
import android.content.res.Configuration
import androidx.preference.PreferenceManager
import java.util.*

object LocalizationHandler {

    const val LOCALE_ARABIC = "ar"
    const val LOCALE_ENGLISH = "en"
    private const val PREF_LOCALE = "PREF_LOCALE"

    val supportedLocales: List<String> = arrayListOf(LOCALE_ARABIC, LOCALE_ENGLISH)

    fun onAttach(newBase: Context): Context = setNewLocale(newBase, getCurrentLocale(newBase))

    fun getCurrentLocale(context: Context) =
        PreferenceManager.getDefaultSharedPreferences(context)
            .getString(PREF_LOCALE, LOCALE_ENGLISH) ?: LOCALE_ENGLISH

    fun setNewLocale(context: Context, langCode: String): Context {
        persistLocale(context, langCode)
        return updateResources(context, langCode)
    }

    private fun persistLocale(context: Context, langCode: String) =
        PreferenceManager.getDefaultSharedPreferences(context).edit()
            .putString(PREF_LOCALE, langCode).apply()

    private fun updateResources(context: Context, langCode: String): Context {
        val locale = Locale(langCode)
        Locale.setDefault(locale)
        val configuration = Configuration(context.resources.configuration)
        configuration.setLocale(locale)
        configuration.setLayoutDirection(locale)
        return context.createConfigurationContext(configuration)
    }

}