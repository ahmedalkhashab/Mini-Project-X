package io.android.projectx.remote.features.usermanagement.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CloudRegistrationWrapper(
    @Json(name = "id")
    val id: Int,
    @Json(name = "user_id")
    val userId: String,
    @Json(name = "device_token")
    val deviceToken: String,
    @Json(name = "token_type")
    val tokenType: String
)