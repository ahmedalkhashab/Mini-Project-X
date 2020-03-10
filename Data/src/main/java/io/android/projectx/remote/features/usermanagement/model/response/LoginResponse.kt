package io.android.projectx.remote.features.usermanagement.model.response

import com.google.gson.annotations.SerializedName
import io.android.projectx.remote.features.usermanagement.model.UserModel

data class LoginResponse(
    @SerializedName("user")
    val user: UserModel,
    @SerializedName("access_token")
    val accessToken: String
)