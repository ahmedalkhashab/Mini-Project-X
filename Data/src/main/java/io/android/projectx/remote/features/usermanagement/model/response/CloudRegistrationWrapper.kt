package io.android.projectx.remote.features.usermanagement.model.response

import com.google.gson.annotations.SerializedName

data class CloudRegistrationWrapper(
    @SerializedName("id")
    val id: Int,
    @SerializedName("user_id")
    val userId: String,
    @SerializedName("device_token")
    val deviceToken: String,
    @SerializedName("token_type")
    val tokenType: String
)