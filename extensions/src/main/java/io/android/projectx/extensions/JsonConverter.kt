package io.android.projectx.extensions

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

@FromJson
inline fun <reified T> fromJson(json:String) : T{
    val moshi: Moshi = Moshi.Builder().build()
    val adapter: JsonAdapter<T> = moshi.adapter(T::class.java)
    return adapter.fromJson(json)!!
}

inline fun <reified T> T.toJson(): String {
    val moshi = Moshi.Builder().build()
    val adapter : JsonAdapter<T> = moshi.adapter(T::class.java)
    return adapter.toJson(this)
}