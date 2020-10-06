package io.android.projectx.remote.features.usermanagement.model.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MobileNumber(
    @Json(name = "countryCode")
    var countryCode: String,
    @Json(name = "number")
    var number: String
)