package io.android.projectx.remote.features.usermanagement.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class UserModel(
    @Json(name = "id")
    val id: Long,
    @Json(name = "name")
    val name: String,
    @Json(name = "email")
    val email: String,
    @Json(name = "mobile")
    val mobile: String,
    val userStatus: UserStatusModel = UserStatusModel.Registered
)