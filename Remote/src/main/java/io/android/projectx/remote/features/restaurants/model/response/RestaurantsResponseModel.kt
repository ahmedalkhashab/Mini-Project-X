package io.android.projectx.remote.features.restaurants.model.response

import com.google.gson.annotations.SerializedName
import io.android.projectx.remote.features.restaurants.model.RestaurantModel

class RestaurantsResponseModel(@SerializedName("content") val items: List<RestaurantModel>)