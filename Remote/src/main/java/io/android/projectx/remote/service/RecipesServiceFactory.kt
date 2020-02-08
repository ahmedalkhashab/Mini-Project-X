package io.android.projectx.remote.service

import com.google.gson.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

object RecipesServiceFactory {

    fun makeRecipesService(baseUrl: String, isDebug: Boolean): RecipesService {
        val okHttpClient = makeOkHttpClient(makeLoggingInterceptor((isDebug)))
        return makeRecipesService(baseUrl, okHttpClient, makeGson())
    }

    private fun makeRecipesService(
        baseUrl: String,
        okHttpClient: OkHttpClient,
        gson: Gson
    ): RecipesService {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        return retrofit.create(RecipesService::class.java)
    }

    private fun makeOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    private fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (isDebug) HttpLoggingInterceptor.Level.BODY
        else HttpLoggingInterceptor.Level.NONE
        return logging
    }

    /**
     * //Ref. https://gist.github.com/cmelchior/1a97377df0c49cd4fca9
     */
    private fun makeGson(): Gson {
        return GsonBuilder().setExclusionStrategies(object : ExclusionStrategy {
            override fun shouldSkipField(f: FieldAttributes): Boolean {
                return false// f.getDeclaringClass().equals(RealmObject.class);
            }

            override fun shouldSkipClass(clazz: Class<*>): Boolean {
                return false
            }
        }).registerTypeAdapter(
            Date::class.java,
            JsonDeserializer<Date> { json, _, _ -> json?.asString?.getDate() })
            .registerTypeAdapter(Date::class.java,
                JsonSerializer<Date> { date, _, _ -> JsonPrimitive(date.getOffsetDate())})
            .serializeNulls()
            .create()
    }

    private const val DATE_ISO_8601_FORMAT = "yyyy-MM-dd'T'hh:mm:ss"

    fun String.getDate(): Date? {
        val format = SimpleDateFormat(DATE_ISO_8601_FORMAT, Locale.getDefault())
        format.timeZone = TimeZone.getTimeZone("GMT")
        return format.parse(this)
    }

    fun Date.getOffsetDate(): String {
        val format = SimpleDateFormat(DATE_ISO_8601_FORMAT, Locale.getDefault())
        format.timeZone = TimeZone.getTimeZone("GMT")
        return format.format(this)
    }

}