package io.android.projectx.presentation.base

import android.app.Activity
import android.app.Application
import android.app.Application.ActivityLifecycleCallbacks
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import io.android.projectx.presentation.R
import timber.log.Timber
import java.lang.ref.WeakReference
import java.util.*

class AppUpdateManager(application: Application) : LifecycleObserver {

    companion object {
        private const val KEY_UPDATE_REQUIRED = "android_update_required"
        private const val KEY_STORE_VERSION_CODE = "android_version_code"
        private const val KEY_STORE_VERSION_NAME = "android_version_name"
        private const val KEY_STORE_URL = "android_store_url"
    }

    private var activityWeakReference: WeakReference<Activity?>? = null

    init {
        application.registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, bundle: Bundle?) {}
            override fun onActivityStarted(activity: Activity) {
                activityWeakReference = WeakReference(activity)
            }

            override fun onActivityResumed(activity: Activity) {}
            override fun onActivityPaused(activity: Activity) {}
            override fun onActivityStopped(activity: Activity) {}
            override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) {}
            override fun onActivityDestroyed(activity: Activity) {}
        })
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun appStarted() = checkForceUpdateNeeded()

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun appStopped() = activityWeakReference?.clear()

    private val currentActivity: Activity?
        get() = if (activityWeakReference != null && activityWeakReference!!.get() != null) activityWeakReference!!.get() else null


    private fun checkForceUpdateNeeded() {
        return// todo - remove return and connect with firebase project
        val remoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600 // 1 hour
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        // set in-app defaults
        val defaults: MutableMap<String, Any?> = HashMap<String, Any?>()
        defaults[KEY_UPDATE_REQUIRED] = false
        defaults[KEY_STORE_VERSION_CODE] = 1L
        defaults[KEY_STORE_VERSION_NAME] = "1.0.0"
        remoteConfig.setDefaultsAsync(defaults)
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) Timber.d("Config params updated: ${task.result}")
                else Timber.d("Fetch failed")
                // check app update
                val storeVersionCode = remoteConfig.getLong(KEY_STORE_VERSION_CODE)
                val appVersion = getAppVersion()
                if (storeVersionCode > appVersion) {
                    val isForceUpdate = remoteConfig.getBoolean(KEY_UPDATE_REQUIRED)
                    val storeVersionName = remoteConfig.getString(KEY_STORE_VERSION_NAME)
                    val storeURL = remoteConfig.getString(KEY_STORE_URL)
                    onUpdateNeeded(isForceUpdate, storeVersionName, storeURL)
                }
            }
    }

    @Suppress("DEPRECATION")
    private fun getAppVersion(): Long {
        val context = currentActivity!!
        val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        return if (Build.VERSION.SDK_INT >= 28) packageInfo.longVersionCode
        else packageInfo.versionCode.toLong()
    }

    private fun onUpdateNeeded(isForceUpdate: Boolean, versionName: String, url: String) {
        if (currentActivity != null) {
            val activity = currentActivity!!
            // prepare text
            val textTitle =
                String.format(activity.getString(R.string.app_update_title), versionName)
            val textDescription = activity.getString(R.string.app_update_description)
            val textPositive = activity.getString(R.string.app_update_positive)
            val textNegative = if (isForceUpdate) activity.getString(R.string.app_update_exit)
            else activity.getString(R.string.app_update_cancel)
            // build dialog
            val dialog =
                AlertDialog.Builder(activity)
                    .setTitle(textTitle)
                    .setMessage(textDescription)
                    .setPositiveButton(textPositive) { _, _ -> redirectStore(activity, url) }
                    .setCancelable(!isForceUpdate)
                    .setNegativeButton(textNegative) { _, _ -> if (isForceUpdate) activity.finishAffinity() }
                    .create()
            dialog.show()
        }
    }

    private fun redirectStore(context: Context, url: String) {
        //val updateUrl = Uri.parse("market://details?id=" + context.packageName)
        val updateUrl = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, updateUrl)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

}