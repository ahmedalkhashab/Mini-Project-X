package io.android.projectx.remote.features.restaurants.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import io.android.projectx.remote.features.restaurants.model.RestaurantModel

@JsonClass(generateAdapter = true)
class RestaurantsResponseModel(@Json(name = "content") val items: List<RestaurantModel>? = null)