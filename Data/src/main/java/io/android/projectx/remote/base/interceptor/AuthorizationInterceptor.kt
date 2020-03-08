package io.android.projectx.remote.base.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthorizationInterceptor @Inject constructor(
    private val requestHeaders: RequestHeaders
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequestBuilder = chain.request().newBuilder()
        if (requestHeaders.hasShortTermToken()) {
            val name = requestHeaders.getShortTermTokenName()
            val value = requestHeaders.getShortTermTokenValue()
            if (value.isNotBlank()) newRequestBuilder.header(name, value)
        }
        return chain.proceed(newRequestBuilder.build())
    }

}