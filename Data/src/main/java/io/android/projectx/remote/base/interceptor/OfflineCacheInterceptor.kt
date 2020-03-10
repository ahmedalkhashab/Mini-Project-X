package io.android.projectx.remote.base.interceptor

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * This interceptor will be called both if the network is available and if the network is not available
 */
class OfflineCacheInterceptor @Inject constructor() : Interceptor {

    companion object {
        private const val HEADER_PRAGMA = "Pragma"
        private const val HEADER_CACHE_CONTROL = "Cache-Control"
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        // prevent caching when network is on. For that we use the "networkInterceptor"
        val request = chain.request()
        return try {
            chain.proceed(request)
        } catch (e: Exception) {
            val cacheControl = CacheControl.Builder()
                .onlyIfCached()
                .maxAge(10, TimeUnit.MINUTES).build()
            val offlineRequest: Request = request.newBuilder()
                .removeHeader(HEADER_PRAGMA)
                .cacheControl(cacheControl)
                .build()
            chain.proceed(offlineRequest)
        }
    }

}