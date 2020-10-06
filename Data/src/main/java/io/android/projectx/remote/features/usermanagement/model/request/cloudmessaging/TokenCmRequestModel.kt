package io.android.projectx.remote.features.usermanagement.model.request.cloudmessaging

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class TokenCmRequestModel(@Json(name = "device_token") val deviceTokenCloudMessaging: String) {
    @Json(name = "token_type")
    val tokenType: String = "ANDROID"
}