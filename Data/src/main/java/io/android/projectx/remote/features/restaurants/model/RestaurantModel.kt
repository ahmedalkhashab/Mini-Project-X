package io.android.projectx.remote.features.restaurants.model

import com.google.gson.annotations.SerializedName
import java.util.*

class RestaurantModel(
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("method_description") val description: String,
    @SerializedName("url") val url: String,
    @SerializedName("url_to_image") val urlToImage: String
)