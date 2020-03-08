package io.android.projectx.remote.base

import com.google.gson.*
import io.android.projectx.cache.extentions.getDate
import io.android.projectx.cache.extentions.getOffsetDate
import io.android.projectx.remote.base.interceptor.Authenticator
import io.android.projectx.remote.base.interceptor.AuthorizationInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

object RemoteFactory {

    internal fun provideRetrofit(baseUrl: String, okHttpClient: OkHttpClient, gson: Gson) =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    internal fun provideOkHttpClient(
        isDebug: Boolean,
        authorizationInterceptor: AuthorizationInterceptor,
        authenticator: Authenticator
    ): OkHttpClient {
        val loggingInterceptor = provideLoggingInterceptor(isDebug)
        return provideOkHttpClient(
            loggingInterceptor,
            authorizationInterceptor,
            authenticator
        )
    }

    internal fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        authorizationInterceptor: AuthorizationInterceptor,
        authenticator: Authenticator
    ) =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(authorizationInterceptor)
            .authenticator(authenticator)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()

    /**
     * //Ref. https://gist.github.com/cmelchior/1a97377df0c49cd4fca9
     */
    internal fun provideGson(): Gson {
        return GsonBuilder().setExclusionStrategies(object : ExclusionStrategy {
            override fun shouldSkipField(f: FieldAttributes): Boolean {
                return false// f.getDeclaringClass().equals(RealmObject.class);
            }

            override fun shouldSkipClass(clazz: Class<*>): Boolean {
                return false
            }
        })
            .registerTypeAdapter(
                Date::class.java,
                JsonDeserializer<Date> { json, _, _ -> json?.asString?.getDate() })
            .registerTypeAdapter(
                Date::class.java,
                JsonSerializer<Date> { date, _, _ -> JsonPrimitive(date.getOffsetDate()) })
            .serializeNulls()
            .create()
    }

    private fun provideLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (isDebug) HttpLoggingInterceptor.Level.BODY
        else HttpLoggingInterceptor.Level.NONE
        return logging
    }

}