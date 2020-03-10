package io.android.projectx.remote.base.interceptor

import android.app.Application
import io.android.projectx.androidextensions.isNetworkConnected
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * This interceptor will be called both if the network is available and if the network is not available
 */
class OfflineCacheInterceptor @Inject constructor(private val application: Application) :
    Interceptor {

    companion object {
        private const val HEADER_PRAGMA = "Pragma"
        private const val HEADER_CACHE_CONTROL = "Cache-Control"
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        // prevent caching when network is on. For that we use the "networkInterceptor"
        if (!isNetworkConnected(application)) {
            val cacheControl = CacheControl.Builder()
                .maxAge(10, TimeUnit.MINUTES).build()
            // offlineRequest
            request = request.newBuilder()
                .removeHeader(HEADER_PRAGMA)
                .removeHeader(HEADER_CACHE_CONTROL)
                .cacheControl(cacheControl)
                .build()
        }
        return chain.proceed(request)
    }

}