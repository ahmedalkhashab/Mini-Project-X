package io.android.projectx.remote.features.usermanagement.model.request.cloudmessaging

import com.google.gson.annotations.SerializedName

class TokenCmRequestModel(@SerializedName("device_token") val deviceTokenCloudMessaging: String) {
    @SerializedName("token_type")
    val tokenType: String = "ANDROID"
}