package io.android.projectx.remote.features.usermanagement.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import io.android.projectx.remote.features.usermanagement.model.UserModel

@JsonClass(generateAdapter = true)
data class LoginWrapper(
    @Json(name = "user")
    val user: UserModel,
    @Json(name = "access_token")
    val accessToken: String
)