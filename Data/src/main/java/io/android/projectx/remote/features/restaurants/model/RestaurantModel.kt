package io.android.projectx.remote.features.restaurants.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class RestaurantModel(
    @Json(name = "id") val id: Long,
    @Json(name = "title") val title: String,
    @Json(name = "method_description") val description: String? = null,
    @Json(name = "url") val url: String? = null,
    @Json(name = "url_to_image") val urlToImage: String? = null
)