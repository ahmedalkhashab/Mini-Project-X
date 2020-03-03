package io.android.projectx.remote.features.usermanagement.model

import com.google.gson.annotations.SerializedName

class UserModel(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("mobile")
    val mobile: String,
    val userStatus: UserStatusModel = UserStatusModel.Registered
)